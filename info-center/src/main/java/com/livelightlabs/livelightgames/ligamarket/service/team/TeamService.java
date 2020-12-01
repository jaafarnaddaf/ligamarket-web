package com.livelightlabs.livelightgames.ligamarket.service.team;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelightlabs.livelightgames.ligamarket.entity.team.Team;
import com.livelightlabs.livelightgames.ligamarket.repository.team.TeamRepository;
import com.livelightlabs.livelightgames.ligamarket.service.BaseEntityService;

@Service
public class TeamService extends BaseEntityService<Team> {

	@Autowired
	private TeamRepository repository;

	public Optional<Team> findOneByApiId(String apiId) {
		return repository.findOneByApiId(apiId);
	}
}