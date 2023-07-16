package com.cwc.mapmyindia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cwc.mapmyindia.model.ResponseToken;
import com.cwc.mapmyindia.service.GenerateTokenService;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/api/v1/mapmyindia")
public class ResponseTokenController {

	@Autowired
	private GenerateTokenService generateTokenService;
	

	
	@PostMapping("/token")
	public ResponseEntity<List<ResponseToken>> getToken(
			@PathParam ("grant_type") String grant_type,
			@PathParam("client_id") String client_id,
			@PathParam("client_secret") String client_secret) {
		
		List<ResponseToken> token = this.generateTokenService.generateToken(grant_type, client_id, client_secret);
		return new ResponseEntity<List<ResponseToken>>(token,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/tokenbyproperties")
	public ResponseEntity<List<ResponseToken>> getTokenByProperties() {
		
		List<ResponseToken> token = this.generateTokenService.generateTokensByPropertiesFile();
		return new ResponseEntity<List<ResponseToken>>(token,HttpStatus.CREATED);
	}
	
}
