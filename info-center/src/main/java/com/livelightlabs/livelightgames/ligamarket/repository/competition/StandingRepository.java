package com.livelightlabs.livelightgames.ligamarket.repository.competition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Standing;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.Standing.Pk;
import com.livelightlabs.livelightgames.ligamarket.entity.team.Team;

public interface StandingRepository extends JpaRepository<Standing, Pk> {

	public List<Standing> findAllByPkTeam(Team team);
}