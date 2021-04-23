package hu.asd.merkur.core.service.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class FdlRequest {

	@JsonProperty("FDLNeve")
	private String fdlName;

	@JsonProperty("Parameter")
	private List<FdlParameter> params = new ArrayList<FdlParameter>();

	public FdlRequest fdlName(String name) {
		this.fdlName = name;
		return this;
	}

	public FdlRequest addParam(String nev, String tipus, String ertek) {
		params.add(new FdlParameter(nev, tipus, ertek));
		return this;
	}

}
