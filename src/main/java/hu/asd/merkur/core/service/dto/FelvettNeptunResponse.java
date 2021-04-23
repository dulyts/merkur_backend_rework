package hu.asd.merkur.core.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FelvettNeptunResponse {
	@JsonProperty("Eredmeny")
	private List<FelvettUserData> result;
}