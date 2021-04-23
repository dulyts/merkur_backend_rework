package hu.asd.merkur.core.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FelvettUserData {
	@JsonProperty("F0")
	private String neptun;
	@JsonProperty("F1")
	private String name;
	@JsonProperty("F2")
	private String gender;
	@JsonProperty("F3")
	private String email;
	@JsonProperty("F4")
	private String moveInDateTime;
	@JsonProperty("F5")
	private String moveOutDateTime;
	@JsonProperty("F6")
	private String dormitoryShortName;
	@JsonProperty("F7")
	private String kar;
	@JsonProperty("F8")
	private String szak;
	@JsonProperty("F9")
	private String phoneNumber;
	@JsonProperty("F10")
	private String plannedMoveInDate;
	@JsonProperty("F11")
	private String plannedMoveOutDate;

}