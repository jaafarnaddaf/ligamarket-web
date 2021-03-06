package com.livelightlabs.livelightgames.ligamarket.service.country;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.entity.country.Country;
import com.livelightlabs.livelightgames.ligamarket.repository.country.CountryRepository;
import com.livelightlabs.livelightgames.ligamarket.service.BaseEntityService;

@Service
public class CountryService extends BaseEntityService<Country> {

	@Autowired
	private CountryRepository repository;

	public Optional<Country> findOneByCode(String code) {
		return repository.findOneByCode(code);
	}
}