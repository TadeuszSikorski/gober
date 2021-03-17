package io.github.tadeuszsikorski.gober.logic.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.github.tadeuszsikorski.gober.config.authorization.jwt.JwtUtils;
import io.github.tadeuszsikorski.gober.config.authorization.userdetails.StandardUserDetails;
import io.github.tadeuszsikorski.gober.controller.dto.request.LoginRequest;
import io.github.tadeuszsikorski.gober.controller.dto.request.SignupRequest;
import io.github.tadeuszsikorski.gober.controller.dto.response.JwtResponse;
import io.github.tadeuszsikorski.gober.controller.dto.response.MessageResponse;
import io.github.tadeuszsikorski.gober.data.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.github.tadeuszsikorski.gober.data.entity.user.Role;
import io.github.tadeuszsikorski.gober.data.entity.user.User;
import io.github.tadeuszsikorski.gober.data.entity.user.Role.Roles;
import io.github.tadeuszsikorski.gober.data.repository.user.UserRepository;

@Service
public class AuthService {
    
    @Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        StandardUserDetails userDetails = (StandardUserDetails) authentication.getPrincipal();
        
        List<String> roles = userDetails.getAuthorities()
            .stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return new JwtResponse(jwt, 
            userDetails.getId(), 
            userDetails.getUsername(), 
            userDetails.getEmail(), 
            roles);
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new MessageResponse("Error: Username is already taken!");
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new MessageResponse("Error: Email is already in use!");
		}

		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Roles.USER);
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				if (role == "admin") {
					Role adminRole = roleRepository.findByName(Roles.ADMIN);
					roles.add(adminRole);
				}

				if (role == "user") {
					Role userRole = roleRepository.findByName(Roles.USER);
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return new MessageResponse("User registered successfully!");
	}
}
