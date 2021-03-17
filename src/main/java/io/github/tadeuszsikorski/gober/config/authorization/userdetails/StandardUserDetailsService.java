package io.github.tadeuszsikorski.gober.config.authorization.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.tadeuszsikorski.gober.data.entity.user.User;
import io.github.tadeuszsikorski.gober.data.repository.user.UserRepository;

@Service
public class StandardUserDetailsService implements UserDetailsService {
    
    @Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		return StandardUserDetails.build(user);
	}

}
