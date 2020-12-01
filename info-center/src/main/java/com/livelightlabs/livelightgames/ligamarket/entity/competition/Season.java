package com.livelightlabs.livelightgames.ligamarket.entity.competition;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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
public class Season {
	@Include
	@EmbeddedId
	private Pk pk;

	private int size;

	public Season(Competition competition, long year, int size) {
		this.pk = new Pk(competition, year);
		this.size = size;
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
		private Competition competition;

		@Include
		private long year;
	}
}