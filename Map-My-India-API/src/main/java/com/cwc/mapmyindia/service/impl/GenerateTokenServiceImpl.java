package com.cwc.mapmyindia.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cwc.mapmyindia.model.AddressRequest;
import com.cwc.mapmyindia.model.AddressResponse;
import com.cwc.mapmyindia.model.ResponseToken;
import com.cwc.mapmyindia.service.GenerateTokenService;

@Service
@PropertySource("apikey.properties")
public class GenerateTokenServiceImpl implements GenerateTokenService {

	@Autowired
	private WebClient.Builder webClient;

	@Value("${api.grant_type}")
	private String grant_type;
	@Value("${api.client_id}")
	private String client_id;
	@Value("${api.client_secret}")
	private String client_secret;

	@Override
	public List<ResponseToken> generateToken(String grant_type, String client_id, String client_secret) {

		String url = "https://outpost.mappls.com/api/security/oauth/token?grant_type=" + grant_type + "&client_id="
				+ client_id + "&client_secret=" + client_secret;
		List<ResponseToken> list = webClient.build().post().uri(url).retrieve().bodyToFlux(ResponseToken.class)
				.collectList().block();
		return list;
	}

	@Override
	public List<ResponseToken> generateTokensByPropertiesFile() {
		String url = "https://outpost.mappls.com/api/security/oauth/token?grant_type=" + grant_type + "&client_id="
				+ client_id + "&client_secret=" + client_secret;
		List<ResponseToken> list = webClient.build().post().uri(url).retrieve().bodyToFlux(ResponseToken.class)
				.collectList().block();
		return list;

	}

	@Override
	public List<AddressResponse> getAddressDetails(String region, String address, int itemCount,String token) {
		// "https://atlas.mappls.com/api/places/geocode?region=ind&address=sikar&itemCount=10&bias=0&bound=TAVI5S";
		String api = "https://atlas.mappls.com/api/places/geocode?region=" + region + "&address=" + address
				+ "&itemCount=" + itemCount;
		List<AddressResponse> blockList = webClient.build().get().uri(api)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " +token).retrieve()
				.bodyToFlux(AddressResponse.class).collectList().block();

//		for (AddressResponse addressResponse : blockList) {
//			List<AddressRequest> request = addressResponse.getAddressRequest();
//			System.out.println(request);
//		}
		
		//List<List<AddressRequest>> list = blockList.stream().map((map)->map.getAddressRequest()).collect(Collectors.toList());
		//System.out.println(list);
		return blockList;
	}

}
