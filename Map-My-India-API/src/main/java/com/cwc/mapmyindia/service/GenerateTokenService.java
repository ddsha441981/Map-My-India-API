package com.cwc.mapmyindia.service;

import java.util.List;

import com.cwc.mapmyindia.model.ApiResponse;
import com.cwc.mapmyindia.model.ResponseToken;

public interface GenerateTokenService {
	
	public List<ResponseToken> generateToken(String grant_type,String client_id,String client_secret);
	public List<ResponseToken> generateTokensByPropertiesFile();
	
	public List<ApiResponse> getAddressDetails(String region,String address,int itemCount,String token);
	public List<ApiResponse> getReverseGeoCodeAPI(String lat,String lng,String region,String lang);
	
	public byte[] getMapImage(String val);
 
}
