package com.cwc.mapmyindia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cwc.mapmyindia.model.ApiResponse;
import com.cwc.mapmyindia.model.ResponseToken;
import com.cwc.mapmyindia.service.GenerateTokenService;

@Service
@PropertySource("apikey.properties")
public class GenerateTokenServiceImpl implements GenerateTokenService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClient;

	@Value("${api.grant_type}")
	private String grant_type;
	@Value("${api.client_id}")
	private String client_id;
	@Value("${api.client_secret}")
	private String client_secret;
	@Value("${api.key}")
	private String default_key;

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
	public List<ApiResponse> getAddressDetails(String region, String address, int itemCount, String token) {
		// "https://atlas.mappls.com/api/places/geocode?region=ind&address=sikar&itemCount=10&bias=0&bound=TAVI5S";
		String api = "https://atlas.mappls.com/api/places/geocode?region=" + region + "&address=" + address
				+ "&itemCount=" + itemCount;
		List<ApiResponse> blockList = webClient.build().get().uri(api)
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + token).retrieve().bodyToFlux(ApiResponse.class)
				.collectList().block();

//		for (AddressResponse addressResponse : blockList) {
//			List<AddressRequest> request = addressResponse.getAddressRequest();
//			System.out.println(request);
//		}

		// List<List<AddressRequest>> list =
		// blockList.stream().map((map)->map.getAddressRequest()).collect(Collectors.toList());
		// System.out.println(list);

		return blockList;
	}

	@Override
	public List<ApiResponse> getReverseGeoCodeAPI(String lat, String lng, String region, String lang) {
		String api = "https://apis.mappls.com/advancedmaps/v1/" + default_key + "/rev_geocode?lat=" + lat + "&lng="
				+ lng + "&region=" + region + "&lang=" + lang;
		List<ApiResponse> list = webClient.build().get().uri(api).retrieve().bodyToFlux(ApiResponse.class).collectList()
				.block();
		return list;
	}

	@Override
	public byte[] getMapImage(String val) {
		String api = "https://apis.mapmyindia.com/advancedmaps/v1/" + default_key + "/still_image?center=" + val
				+ "&zoom=15&size=400x400&ssf=1&markers=28.5959394,77.2255611";
		// "https://apis.mappls.com/advancedmaps/v1/"+default_key+"/still_image?center="+val+"&zoom=15&size=400x400&ssf=1&markers=27.365327012166205%2C75.40318317711353&markers_icon=https%3A%2F%2Fimg.icons8.com%2Fmaterial-outlined%2F96%2F000000%2Ftruck.png";
//		List<byte[]> block = webClient.build().get().uri(api).retrieve().bodyToFlux(byte[].class).collectList().block();		
		
		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Collections.singletonList(MediaType.IMAGE_PNG));
//		headers.add("Content-Type", "image/png");
//		ResponseEntity<String> responseEntity = this.restTemplate.exchange(api,HttpMethod.GET,new HttpEntity<>(headers),String.class);
//		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
//		headers.setAccept(Arrays.asList(MediaType.IMAGE_JPEG, MediaType.IMAGE_GIF, MediaType.IMAGE_PNG));
		
//		headers.setContentType(new MediaType("image/png","image/png",StandardCharsets.UTF_8));
//		Charset utf8 = Charset.forName("UTF-8");
		ResponseEntity<byte[]> response = restTemplate.exchange(api, HttpMethod.GET, new HttpEntity<>(headers),
				byte[].class);
		if (response.getStatusCode().equals(HttpStatus.OK)) {
			HttpStatusCode statusCode = response.getStatusCode();
			byte[] body = response.getBody();
			
			System.out.println(body +","+ statusCode);
			return body;
		}

		return null;
	}

}
