package com.livelightlabs.livelightgames.ligamarket.entity.competition;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.livelightlabs.livelightgames.ligamarket.entity.team.Team;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Standing {
	@Include
	@EmbeddedId
	private Pk pk;

	private int position;

	public Standing(Season season, Team team, int position) {
		this.pk = new Pk(season, team);
		this.position = position;
	}

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Embeddable
	@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class Pk implements Serializable {
		private static final long serialVersionUID = 9078452149819629622L;

		@ManyToOne
		@Include
		private Season season;

		@ManyToOne
		@Include
		private Team team;
	}
}