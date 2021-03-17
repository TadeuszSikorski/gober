package io.github.tadeuszsikorski.gober.controller;

import javax.validation.Valid;

import io.github.tadeuszsikorski.gober.controller.dto.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.tadeuszsikorski.gober.controller.dto.request.LoginRequest;
import io.github.tadeuszsikorski.gober.logic.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(this.authService.authenticateUser(loginRequest));
	}

    @PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

		return ResponseEntity.ok(this.authService.registerUser(signUpRequest));
	}
}
