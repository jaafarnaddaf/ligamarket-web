package com.livelightlabs.livelightgames.ligamarket.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Competition;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.CompetitionLevel;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.CompetitionType;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Season;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Season.Pk;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Standing;
import com.livelightlabs.livelightgames.ligamarket.entity.country.Country;
import com.livelightlabs.livelightgames.ligamarket.helper.CollectionHelper;
import com.livelightlabs.livelightgames.ligamarket.helper.EntityHelper;
import com.livelightlabs.livelightgames.ligamarket.model.team.Team;
import com.livelightlabs.livelightgames.ligamarket.repository.competition.SeasonRepository;
import com.livelightlabs.livelightgames.ligamarket.repository.competition.StandingRepository;
import com.livelightlabs.livelightgames.ligamarket.rest.team.TeamClient;
import com.livelightlabs.livelightgames.ligamarket.service.competition.CompetitionService;
import com.livelightlabs.livelightgames.ligamarket.service.country.CountryService;
import com.livelightlabs.livelightgames.ligamarket.service.team.TeamService;

@Component
public class InitialData {

	@Autowired
	private TeamClient teamClient;

	@Autowired
	private CompetitionService competitionService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private StandingRepository standingRepository;

	@Autowired
	private SeasonRepository seasonRepository;

//	@PostConstruct
	public void init() throws Exception {
		if (competitionService.count() == 0) {
			createTeams(createCompetitions());
			createLeagueStandings();
			createExtraSeasons();
			createInternationalExtraSeasons();
			createContinentalExtraSeasons();
		}
		calculateCoefficients();
	}

	private void createContinentalExtraSeasons() {
		Competition libertadores = competitionService.findById(10l).get();
		Competition caf = competitionService.findById(16l).get();
		Competition afc = competitionService.findById(18l).get();
		List<Season> seasons = new ArrayList<>();
		for (int i = 2015; i < 2020; i++) {
			seasons.add(new Season(libertadores, i, 32));
			seasons.add(new Season(caf, i + 1, 16));
			seasons.add(new Season(afc, i, 32));
		}
		seasonRepository.saveAll(seasons);
	}

	private void createExtraSeasons() {
		Competition el = competitionService.findById(6L).get();
		Competition cl = competitionService.findById(7L).get();
		for (int i = 2016; i < 2021; i++) {
			Season se = new Season(el, i, 32);
			Season sc = new Season(cl, i, 32);
			seasonRepository.save(se);
			seasonRepository.save(sc);
		}
	}

	private void createInternationalExtraSeasons() {
		Competition wc = competitionService.findById(1l).get();
		Competition euro = competitionService.findById(4l).get();
		Competition cmbl = competitionService.findById(9l).get();
		Competition cnf = competitionService.findById(12l).get();
		Competition caf = competitionService.findById(15l).get();
		Competition afc = competitionService.findById(17l).get();
		Competition ofc = competitionService.findById(20l).get();

		List<Season> seasons = new ArrayList<>();

		seasons.add(new Season(wc, 2002, 32));
		seasons.add(new Season(wc, 2006, 32));
		seasons.add(new Season(wc, 2010, 32));
		seasons.add(new Season(wc, 2014, 32));
		seasons.add(new Season(wc, 2018, 32));

		seasons.add(new Season(euro, 2000, 16));
		seasons.add(new Season(euro, 2004, 16));
		seasons.add(new Season(euro, 2008, 16));
		seasons.add(new Season(euro, 2012, 16));
		seasons.add(new Season(euro, 2016, 24));

		seasons.add(new Season(cmbl, 2007, 12));
		seasons.add(new Season(cmbl, 2011, 12));
		seasons.add(new Season(cmbl, 2015, 12));
		seasons.add(new Season(cmbl, 2016, 16));
		seasons.add(new Season(cmbl, 2019, 12));

		seasons.add(new Season(cnf, 2011, 12));
		seasons.add(new Season(cnf, 2013, 12));
		seasons.add(new Season(cnf, 2015, 12));
		seasons.add(new Season(cnf, 2017, 12));
		seasons.add(new Season(cnf, 2019, 16));

		seasons.add(new Season(caf, 2012, 16));
		seasons.add(new Season(caf, 2013, 16));
		seasons.add(new Season(caf, 2015, 16));
		seasons.add(new Season(caf, 2017, 16));
		seasons.add(new Season(caf, 2019, 24));

		seasons.add(new Season(afc, 2004, 16));
		seasons.add(new Season(afc, 2007, 16));
		seasons.add(new Season(afc, 2011, 16));
		seasons.add(new Season(afc, 2015, 16));
		seasons.add(new Season(afc, 2019, 24));

		seasons.add(new Season(ofc, 2002, 8));
		seasons.add(new Season(ofc, 2004, 6));
		seasons.add(new Season(ofc, 2008, 4));
		seasons.add(new Season(ofc, 2012, 8));
		seasons.add(new Season(ofc, 2016, 8));

		seasonRepository.saveAll(seasons);
	}

	private void calculateCoefficients() {
		teamService.findAll().forEach(t -> {
			double coefficient = 10;
			List<Standing> standings = standingRepository.findAllByPkTeam(t);
			for (Standing standing : standings) {
				Season season = standing.getPk().getSeason();
				Pk pk = season.getPk();
				Competition competition = pk.getCompetition();
				if (competition.getApiId().equals("4480") && standing.getPosition() <= 24
						&& standing.getPosition() > 16) {
					continue;
				}
				double seasonCoefficient = competition.getCoefficient();
				switch (competition.getApiId()) {
				case "4429":
					seasonCoefficient -= ((2016 - pk.getYear()) / 4) * competition.getCoefficient() * 0.125;
					break;
				case "4502":
					seasonCoefficient -= ((2018 - pk.getYear()) / 4) * competition.getCoefficient() * 0.125;
					break;
				case "4499":
					seasonCoefficient -= ((2019 - pk.getYear()) / 3) * competition.getCoefficient() * 0.125;
					break;
				case "4873":
				case "4496":
					seasonCoefficient -= ((2019 - pk.getYear()) / 2) * competition.getCoefficient() * 0.125;
					break;
				case "4866":
					seasonCoefficient -= ((2019 - pk.getYear()) / 4) * competition.getCoefficient() * 0.125;
					break;
				case "4867":
					seasonCoefficient -= ((2016 - pk.getYear()) / 4) * competition.getCoefficient() * 0.125;
					break;
				default:
					seasonCoefficient -= (2020 - pk.getYear()) * competition.getCoefficient() * 0.125;
					break;
				}
				double increment = (1 - (Math.log(standing.getPosition()) / Math.log(season.getSize())))
						* seasonCoefficient;
				switch (competition.getSeasons()) {
				case 1:
					increment *= 3.75;
					break;
				case 2:
					increment *= 2;
					break;
				case 3:
					increment *= 1.43;
					break;
				case 4:
					increment *= 1.15;
					break;
				default:
					break;
				}
				coefficient += increment;
			}
			t.setCoefficient(coefficient);
			teamService.save(t);
		});
		System.out.println("Coefficients calculated");
	}

	private void createLeagueStandings() {
		competitionService.findAllByType(CompetitionType.LEAGUE).forEach(c -> {
			long year = 2020;
			System.out.println(c.getNames());
			int dry = 0;
			int finished = 0;
			while (dry < 5 && finished < 5) {
				try {
					List<Team> teams = teamClient.findStandingsByCompetitionAndSeason(c, (year - 1) + "-" + year);
					Season season = null;
					getStandings(c, year, teams, season);

					List<Team> teamsTwo = teamClient.findStandingsByCompetitionAndSeason(c, (year - 1) + "");
					season = null;
					getStandings(c, year, teamsTwo, season);
					if (!teams.isEmpty() || !teamsTwo.isEmpty()) {
						finished++;
					} else {
						dry++;
					}
					continue;
				} catch (Exception e) {
				} finally {
					year--;
				}
				dry++;
			}
			c.setSeasons(finished);
			competitionService.save(c);
		});
	}

	private void getStandings(Competition c, long year, List<Team> teams, Season season) {
		if (!teams.isEmpty()) {
			season = new Season(c, year, teams.size());
			seasonRepository.save(season);
		}

		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			Country country = countryService.findOneByCode(team.getCountry())
					.orElseGet(() -> countryService.save(EntityHelper.toCountry(team.getCountry())));
			com.livelightlabs.livelightgames.ligamarket.entity.team.Team t = teamService.findOneByApiId(team.getId())
					.orElseGet(() -> teamService.save(EntityHelper.toTeam(team, country)));
			Standing standing = new Standing(season, t, i + 1);
			standingRepository.save(standing);
		}
	}

	private void createTeams(List<Competition> competitions) {
		for (Competition competition : competitions) {
			try {
				List<Team> teams = teamClient.findAllByCompetition(competition);
				System.out.println();
				for (Team team : teams) {
					Country country = countryService.findOneByCode(team.getCountry())
							.orElseGet(() -> countryService.save(EntityHelper.toCountry(team.getCountry())));

					com.livelightlabs.livelightgames.ligamarket.entity.team.Team t = teamService
							.findOneByApiId(team.getId())
							.orElseGet(() -> teamService.save(EntityHelper.toTeam(team, country)));
					System.out.print(t.getNames().get("en") + " - ");
				}
				System.out.println();
			} catch (Exception e) {
				System.out.println(competition.getApiId());
			}
		}

		countryService.findAll().forEach(c -> {
			try {
				List<Team> teams = teamClient.findAllByCountry(c);
				System.out.println();
				for (Team team : teams) {
					Country country = countryService.findOneByCode(team.getCountry())
							.orElseGet(() -> countryService.save(EntityHelper.toCountry(team.getCountry())));

					com.livelightlabs.livelightgames.ligamarket.entity.team.Team t = teamService
							.findOneByApiId(team.getId())
							.orElseGet(() -> teamService.save(EntityHelper.toTeam(team, country)));
					System.out.print(t.getNames().get("en") + " - ");
				}
				System.out.println();
			} catch (Exception e) {
				System.out.println(c);
			}
		});
	}

	private List<Competition> createCompetitions() {
		List<Competition> competitions = Arrays.asList(
				new Competition("4429", 100, "FIFA", CompetitionType.CUP, CompetitionLevel.GLOBAL,
						CollectionHelper.mapOf("en", "World Cup"), 0),
				new Competition("4498", 20, "FIFA", CompetitionType.CUP, CompetitionLevel.GLOBAL,
						CollectionHelper.mapOf("en", "Confederations Cup"), 0),
				new Competition("4503", 20, "FIFA", CompetitionType.KNOCKOUT, CompetitionLevel.GLOBAL,
						CollectionHelper.mapOf("en", "Club World Cup"), 0),
				new Competition("4502", 80, "UEFA", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Euro"), 0),
				new Competition("4490", 16, "UEFA", CompetitionType.QUALIFICATIONS, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Nations League"), 0),
				new Competition("4480", 80, "UEFA", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Champions' League"), 0),
				new Competition("4481", 60, "UEFA", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Europa League"), 0),
				new Competition("4512", 7, "UEFA ", CompetitionType.KNOCKOUT, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Super Cup"), 0),
				new Competition("4499", 70, "CONMEBOL", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Copa America"), 0),
				new Competition("4501", 70, "CONMEBOL", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Copa Libertadores"), 0),
				new Competition("4724", 52, "CONMEBOL", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Copa Sudamericana"), 0),
				new Competition("4873", 50, "CONCACAF", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Gold Cup"), 0),
				new Competition("4721", 50, "CONCACAF", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Champions' League"), 0),
				new Competition("4739", 38, "CONCACAF", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "CONCACAF League"), 0),
				new Competition("4496", 60, "CAF", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "African Cup of Nations"), 0),
				new Competition("4720", 60, "CAF", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Champions' League"), 0),
				new Competition("4866", 56, "AFC", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "AFC Asian Cup"), 0),
				new Competition("4719", 56, "AFC", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Champions' League"), 0),
				new Competition("4804", 42, "AFC", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "AFC Cup"), 0),
				new Competition("4867", 32, "OFC", CompetitionType.CUP, CompetitionLevel.CONTINENTAL,
						CollectionHelper.mapOf("en", "Nations Cup"), 0),
				new Competition("4328", 64, "England", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4482", 16, "England", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "FA Cup"), 0),
				new Competition("4570", 16, "England", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "EFL Cup"), 0),
				new Competition("4571", 5, "England", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "FA Community Shield"), 0),
				new Competition("4335", 64, "Spain", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "La Liga"), 0),
				new Competition("4483", 32, "Spain", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Copa del Rey"), 0),
				new Competition("4511", 5, "Spain", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Supercopa"), 0),
				new Competition("4331", 60, "Germany", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Bundesliga"), 0),
				new Competition("4485", 34, "Germany", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "DFB Pokal"), 0),
				new Competition("4332", 60, "Italy", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Serie A"), 0),
				new Competition("4506", 30, "Italy", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Coppa Italia"), 0),
				new Competition("4507", 5, "Italy", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Supercoppa"), 0),
				new Competition("4334", 58, "France", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Ligue 1"), 0),
				new Competition("4504", 16, "France", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Coupe de la Ligue"), 0),
				new Competition("4484", 16, "France", CompetitionType.KNOCKOUT, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Coupe de France"), 0),
				new Competition("4337", 56, "Netherlands", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Eredevisie"), 0),
				new Competition("4344", 56, "Portugal", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Primeira Liga"), 0),
				new Competition("4339", 54, "Turkey", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Super Lig"), 0),
				new Competition("4336", 52, "Greece", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Superleague"), 0),
				new Competition("4338", 52, "Belgium", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "First Division A"), 0),
				new Competition("4355", 54, "Russia", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4675", 52, "Switzerland", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Super League"), 0),
				new Competition("4330", 52, "Scotland", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4351", 56, "Brazil", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Serie A"), 0),
				new Competition("4406", 56, "Argentina", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Primera Division"), 0),
				new Competition("4627", 54, "Chile", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Primera Division"), 0),
				new Competition("4346", 54, "United States", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "MLS"), 0),
				new Competition("4350", 52, "Mexico", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Liga MX"), 0),
				new Competition("4753", 50, "Algeria", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Ligue 1"), 0),
				new Competition("4829", 50, "Egypt", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4828", 50, "Tunisia", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Ligue 1"), 0),
				new Competition("4520", 50, "Morocco", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Botola"), 0),
				new Competition("4356", 50, "Australia", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "A-League"), 0),
				new Competition("4826", 46, "Bahrain", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4359", 50, "China", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Super League"), 0),
				new Competition("4742", 50, "Iran", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Persian Gulf Pro League"), 0),
				new Competition("4633", 52, "Japan", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "J1 League"), 0),
				new Competition("4823", 48, "Kuwait", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Premier League"), 0),
				new Competition("4663", 50, "Qatar", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Stars League"), 0),
				new Competition("4668", 50, "Saudi Arabia", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Pro League"), 0),
				new Competition("4689", 50, "South Korea", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "K League 1"), 0),
				new Competition("4347", 52, "Sweden", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Allsvenskan"), 0),
				new Competition("4678", 50, "United Arab Emirates", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Pro League"), 0),
				new Competition("4497", 54, "Colombia", CompetitionType.LEAGUE, CompetitionLevel.NATIONAL,
						CollectionHelper.mapOf("en", "Categoria Primiera A"), 0));

		return competitionService.saveAll(competitions);
	}
}
