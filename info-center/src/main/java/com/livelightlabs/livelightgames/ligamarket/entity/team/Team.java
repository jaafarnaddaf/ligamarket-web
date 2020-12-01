package com.livelightlabs.livelightgames.ligamarket.entity.team;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.livelightlabs.livelightgames.ligamarket.entity.BaseEntity;
import com.livelightlabs.livelightgames.ligamarket.entity.country.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Team extends BaseEntity {
	private String abbreviation;
	@Include
	private String apiId;
	private String badge;
	private String banner;
	private double coefficient;
	@ManyToOne
	private Country country;
	private String facebook;
	private String fanart1;
	private String fanart2;
	private String fanart3;
	private String fanart4;
	private String instagram;
	private String jersey;
	private String logo;
	private String manager;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> names;
	private String sport;
	private String stadium;
	private String stadiumCapacity;
	private String stadiumImage;
	private String stadiumLocation;
	private String twitter;
	private String website;
	private Long year;
	private String youtube;
}