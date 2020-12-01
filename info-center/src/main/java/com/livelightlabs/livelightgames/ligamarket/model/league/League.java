package com.livelightlabs.livelightgames.ligamarket.model.league;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "idLeague", "strLeague", "strSport", "strLeagueAlternate" })
@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class League {

	@JsonProperty("idLeague")
	private String id;
	@JsonProperty("strLeague")
	private String name;
	@JsonProperty("strSport")
	private String sport;
	@JsonProperty("strLeagueAlternate")
	private String alternativeName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}