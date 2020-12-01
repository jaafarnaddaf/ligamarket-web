package com.livelightlabs.livelightgames.ligamarket.rest.league;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.model.country.Country;
import com.livelightlabs.livelightgames.ligamarket.model.league.League;
import com.livelightlabs.livelightgames.ligamarket.model.league.Leagues;
import com.livelightlabs.livelightgames.ligamarket.model.sport.Sport;
import com.livelightlabs.livelightgames.ligamarket.rest.BaseClient;

@Service
public class LeagueClient extends BaseClient {

	public List<League> findAll() {
		ResponseEntity<Leagues> leagues = restTemplate.getForEntity(LEAGUES, Leagues.class);
		if (leagues.getBody() != null) {
			return leagues.getBody().getLeagues();
		}
		return new ArrayList<>();
	}

	public List<League> findAllByCountryAndSport(Country country, Sport sport) {
		String url = LEAGUES_SEARCH;
		boolean hasParams = false;
		if (country != null) {
			url += "?c=" + country.getName();
			hasParams = true;
		}

		if (sport != null) {
			if (hasParams) {
				url += "&";
			} else {
				url += "?";
			}
			url += "s=" + sport.getName();
		}
		ResponseEntity<Leagues> leagues = restTemplate.getForEntity(url, Leagues.class);
		if (leagues.getBody() != null) {
			return leagues.getBody().getLeagues();
		}
		return new ArrayList<>();
	}

	public League findByName(String name) {
		List<League> leagues = findAll();
		if (leagues != null) {
			for (League league : leagues) {
				if (league.getName().equals(name)) {
					return league;
				}
			}
		}
		return null;
	}

	public League findById(String id) {
		List<League> leagues = findAll();
		if (leagues != null) {
			for (League league : leagues) {
				if (league.getId().equals(id)) {
					return league;
				}
			}
		}
		return null;
	}

	public List<League> findAllByCountry(Country country) {
		List<League> leagues = findAllByCountryAndSport(country, null);
		return leagues;
	}

	public List<League> findAllByCountryForSoccer(Country country) {
		Sport sport = new Sport();
		sport.setName("Soccer");
		List<League> leagues = findAllByCountryAndSport(country, sport);
		return leagues;
	}

	public List<League> findAllBySport(Sport sport) {
		List<League> matches = new ArrayList<>();

		if (sport == null) {
			return matches;
		}

		List<League> leagues = findAll();
		if (leagues != null) {
			for (League league : leagues) {
				if (league.getSport().equals(sport.getName())) {
					matches.add(league);
				}
			}
		}
		return matches;
	}
}