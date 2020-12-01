package com.livelightlabs.livelightgames.ligamarket.helper;

import com.livelightlabs.livelightgames.ligamarket.entity.country.Country;
import com.livelightlabs.livelightgames.ligamarket.entity.team.Team;

public class EntityHelper {
	public static Team toTeam(com.livelightlabs.livelightgames.ligamarket.model.team.Team source) {
		Team team = new Team(source.getAbbreviation(), source.getId(), source.getBadge(), source.getBanner(), 0, null,
				source.getFacebook(), source.getFanart1(), source.getFanart2(), source.getFanart3(),
				source.getFanart4(), source.getInstagram(), source.getJersey(), source.getLogo(), source.getManager(),
				CollectionHelper.mapOf("en", source.getName()), source.getSport(), source.getStadium(),
				source.getStadiumCapacity(), source.getStadiumImage(), source.getStadiumLocation(), source.getTwitter(),
				source.getWebsite(), source.getYear(), source.getYoutube());
		return team;
	}

	public static Team toTeam(com.livelightlabs.livelightgames.ligamarket.model.team.Team source, Country country) {
		Team team = new Team(source.getAbbreviation(), source.getId(), source.getBadge(), source.getBanner(), 0,
				country, source.getFacebook(), source.getFanart1(), source.getFanart2(), source.getFanart3(),
				source.getFanart4(), source.getInstagram(), source.getJersey(), source.getLogo(), source.getManager(),
				CollectionHelper.mapOf("en", source.getName()), source.getSport(), source.getStadium(),
				source.getStadiumCapacity(), source.getStadiumImage(), source.getStadiumLocation(), source.getTwitter(),
				source.getWebsite(), source.getYear(), source.getYoutube());
		return team;
	}

	public static Country toCountry(String name) {
		return new Country(name, CollectionHelper.mapOf("en", name), null);
	}
}