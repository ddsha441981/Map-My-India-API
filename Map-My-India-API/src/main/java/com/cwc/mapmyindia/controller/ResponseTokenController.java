package com.cwc.mapmyindia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cwc.mapmyindia.model.ApiResponse;
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
	
	

	@GetMapping("/address")
	public ResponseEntity<List<ApiResponse>> getAddress(
			String region,
			String address,
			int itemCount,
			String token) {
		List<ApiResponse> add = this.generateTokenService.getAddressDetails(region, address, itemCount,token);
		return new ResponseEntity<List<ApiResponse>>(add,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/reversegeocode")
	public ResponseEntity<List<ApiResponse>> getReverseGeocode(
			String lat,
			String lng,
			String region,
			String lang
			) {
		List<ApiResponse> reverseGeoCodeAPI = this.generateTokenService.getReverseGeoCodeAPI(lat,lng,region,lang);
		return new ResponseEntity<List<ApiResponse>>(reverseGeoCodeAPI,HttpStatus.CREATED);
	}
	
	
//	@RequestMapping("/mapimage",method)
	@GetMapping(value = "/mapimage", consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getMapImage(
			String value
			) {
		System.out.println("hit the endpoints...........................");
		byte[] mapImage = this.generateTokenService.getMapImage(value);
		/*
		 * HttpHeaders headers = new HttpHeaders(); headers.add("Content-Type",
		 * "image/png");
		 */
		return new ResponseEntity<byte[]>(mapImage,HttpStatus.OK);
	}
	
	
}
