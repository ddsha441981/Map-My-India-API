package com.cwc.mapmyindia.service;

import java.util.List;

import com.cwc.mapmyindia.model.AddressRequest;
import com.cwc.mapmyindia.model.AddressResponse;
import com.cwc.mapmyindia.model.ResponseToken;

public interface GenerateTokenService {
	
	public List<ResponseToken> generateToken(String grant_type,String client_id,String client_secret);
	public List<ResponseToken> generateTokensByPropertiesFile();
	
	public List<AddressResponse> getAddressDetails(String region,String address,int itemCount,String token);

}
