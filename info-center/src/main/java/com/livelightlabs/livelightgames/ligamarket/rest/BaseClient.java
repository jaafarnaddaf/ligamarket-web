package com.livelightlabs.livelightgames.ligamarket.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseClient {

	@Autowired
	protected RestTemplate restTemplate;

	public final static String API_KEY = "4013017";
	public final static String BASE = "https://www.thesportsdb.com/api/v1/json/";
	public final static String SPORTS = BASE + API_KEY + "/all_sports.php";
	public final static String LEAGUES = BASE + API_KEY + "/all_leagues.php";
	public final static String LEAGUES_SEARCH = BASE + API_KEY + "/search_all_leagues.php";
	public final static String COUNTRIES = BASE + API_KEY + "/all_countries.php";
	public final static String TEAMS = BASE + API_KEY + "/lookup_all_teams.php";
	public final static String TEAMS_ID = BASE + API_KEY + "/lookupteam.php";
	public final static String TEAMS_STANDINGS = BASE + API_KEY + "/lookuptable.php";
	public final static String TEAMS_SEARCH = BASE + API_KEY + "/search_all_teams.php";
}