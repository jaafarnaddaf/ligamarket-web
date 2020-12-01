package com.livelightlabs.livelightgames.ligamarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livelightlabs.livelightgames.ligamarket.entity.BaseEntity;

public interface BaseEntityRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}