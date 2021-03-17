package io.github.tadeuszsikorski.gober.controller.dto.response;

import java.util.List;
import java.util.UUID;

public class JwtResponse {
    
    private String token;

	private String type = "Bearer";

	private UUID id;

	private String username;

	private String email;
    
	private List<String> roles;

	public JwtResponse(String jwt, UUID id, String username, String email, List<String> roles) {
		this.token = jwt;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}
}
