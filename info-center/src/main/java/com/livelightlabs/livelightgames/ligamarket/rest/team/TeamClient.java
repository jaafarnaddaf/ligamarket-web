package com.livelightlabs.livelightgames.ligamarket.rest.team;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Competition;
import com.livelightlabs.livelightgames.ligamarket.entity.country.Country;
import com.livelightlabs.livelightgames.ligamarket.model.team.Team;
import com.livelightlabs.livelightgames.ligamarket.model.team.Teams;
import com.livelightlabs.livelightgames.ligamarket.rest.BaseClient;

@Service
public class TeamClient extends BaseClient {

	public List<Team> findAllByCompetition(Competition competition) {
		if (competition != null) {
			ResponseEntity<Teams> leagues = restTemplate.getForEntity(TEAMS + "?id=" + competition.getApiId(),
					Teams.class);
			if (leagues.getBody() != null) {
				return leagues.getBody().getTeams();
			}
		}
		return new ArrayList<>();
	}

	public List<Team> findStandingsByCompetitionAndSeason(Competition competition, String season) {
		if (competition != null) {
			ResponseEntity<Teams> teams = restTemplate
					.getForEntity(TEAMS_STANDINGS + "?l=" + competition.getApiId() + "&s=" + season, Teams.class);
			if (teams.getBody() != null) {
				return teams.getBody().getTeams();
			}
		}
		return new ArrayList<>();
	}

	public List<Team> findAllByCountry(Country country) {
		if (country != null) {
			ResponseEntity<Teams> teams = restTemplate
					.getForEntity(TEAMS_SEARCH + "?c=" + country.getNames().get("en") + "&s=Soccer", Teams.class);
			if (teams.getBody() != null) {
				return teams.getBody().getTeams();
			}
		}
		return new ArrayList<>();
	}

	public Team findOneById(String id) {
		ResponseEntity<Teams> leagues = restTemplate.getForEntity(TEAMS + "?id=" + id, Teams.class);
		if (leagues.getBody() != null) {
			List<Team> teams = leagues.getBody().getTeams();
			if (teams != null && !teams.isEmpty()) {
				return teams.get(0);
			}
		}
		return null;
	}
}