package com.livelightlabs.livelightgames.ligamarket.repository.competition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Competition;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Season;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Standing.Pk;

public interface SeasonRepository extends JpaRepository<Season, Pk> {

	public List<Season> findAllByPkCompetition(Competition competition);
}