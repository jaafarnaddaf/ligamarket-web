package com.livelightlabs.livelightgames.ligamarket.repository.team;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.livelightlabs.livelightgames.ligamarket.entity.team.Team;
import com.livelightlabs.livelightgames.ligamarket.repository.BaseEntityRepository;

@Repository
public interface TeamRepository extends BaseEntityRepository<Team> {
	Optional<Team> findOneByApiId(String apiId);
}