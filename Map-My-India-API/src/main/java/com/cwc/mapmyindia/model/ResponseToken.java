package com.cwc.mapmyindia.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseToken {

	
	private String access_token;
    private String token_type;
    private String expires_in;
    private String scope;
    private String project_code;
    private String client_id;
}
