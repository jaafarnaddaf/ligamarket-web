package com.livelightlabs.livelightgames.ligamarket.rest.country;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.model.country.Countries;
import com.livelightlabs.livelightgames.ligamarket.model.country.Country;
import com.livelightlabs.livelightgames.ligamarket.rest.BaseClient;

@Service
public class CountryClient extends BaseClient {

	public List<Country> findAll() {
		ResponseEntity<Countries> countries = restTemplate.getForEntity(COUNTRIES, Countries.class);
		if (countries.getBody() != null) {
			return countries.getBody().getCountries();
		}
		return new ArrayList<>();
	}

	public Country findByName(String name) {
		List<Country> countries = findAll();
		if (countries != null) {
			for (Country country : countries) {
				if (country.getName().equals(name)) {
					return country;
				}
			}
		}
		return null;
	}
}