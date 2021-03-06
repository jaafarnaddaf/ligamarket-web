package com.livelightlabs.livelightgames.ligamarket.service.competition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.entity.competition.Competition;
import com.livelightlabs.livelightgames.ligamarket.entity.competition.CompetitionType;
import com.livelightlabs.livelightgames.ligamarket.repository.competition.CompetitionRepository;
import com.livelightlabs.livelightgames.ligamarket.service.BaseEntityService;

@Service
public class CompetitionService extends BaseEntityService<Competition> {

	@Autowired
	private CompetitionRepository repository;

	public List<Competition> findAllByType(CompetitionType type) {
		return repository.findAllByType(type);
	}
}