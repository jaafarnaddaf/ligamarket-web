package com.livelightlabs.livelightgames.ligamarket.entity.competition;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import com.livelightlabs.livelightgames.ligamarket.entity.BaseEntity;

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
public class Competition extends BaseEntity {

	@Column(unique = true)
	@Include
	private String apiId;

	private double coefficient;

	private String federation;

	@Enumerated(EnumType.STRING)
	private CompetitionType type;

	@Enumerated(EnumType.STRING)
	private CompetitionLevel level;

	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> names;

	private int seasons;
}