package com.cwc.mapmyindia.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiRequest {
	@JsonProperty
	private String houseNumber;
	@JsonProperty
	private String houseName;
	@JsonProperty
	private String poi;
	@JsonProperty
	private String street;
	@JsonProperty
	private String subSubLocality;
	@JsonProperty
	private String subLocality;
	@JsonProperty
	private String locality;
	@JsonProperty
	private String village;
	@JsonProperty
	private String subDistrict;
	@JsonProperty
	private String district;
	@JsonProperty
	private String city;
	@JsonProperty
	private String state;
	@JsonProperty
	private String pincode;
	@JsonProperty
	private String formattedAddress;
	@JsonProperty
	private String eLoc;
	@JsonProperty
	private String geocodeLevel;
	@JsonProperty
	private double confidenceScore;
	@JsonProperty
	public String poi_dist;
	@JsonProperty
	public String street_dist;
	@JsonProperty
	public String lat;
	@JsonProperty
	public String lng;
	@JsonProperty
	public String area;
	@JsonProperty
	public String formatted_address;

}
