package hu.asd.merkur.core.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FdlParameter {

	@JsonProperty("SzuroNev")
	private String nev;

	@JsonProperty("Tipus")
	private String tipus;

	@JsonProperty("Ertek")
	private String ertek;

}
