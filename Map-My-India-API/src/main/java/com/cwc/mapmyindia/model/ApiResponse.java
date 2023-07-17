package com.cwc.mapmyindia.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ApiResponse {
	@JsonProperty("copResults")
	private List<ApiRequest> apiRequest;

	@JsonProperty
	private int responseCode;
	@JsonProperty
	private String version;
	@JsonProperty("results")
    public ArrayList<ApiRequest> results;
}
