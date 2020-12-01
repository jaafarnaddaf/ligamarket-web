package com.livelightlabs.livelightgames.ligamarket.entity.country;

import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
public class Country extends BaseEntity {
	@Include
	private String code;
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> names;
	private String federation;
}