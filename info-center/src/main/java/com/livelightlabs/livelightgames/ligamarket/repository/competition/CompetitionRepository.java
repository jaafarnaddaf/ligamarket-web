package com.livelightlabs.livelightgames.ligamarket.repository.competition;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Competition;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.CompetitionType;
import com.livelightlabs.livelightgames.ligamarket.repository.BaseEntityRepository;

@Repository
public interface CompetitionRepository extends BaseEntityRepository<Competition> {
	public List<Competition> findAllByType(CompetitionType type);
}