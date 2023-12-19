package com.plexus.plextalent.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This class defines objects of type HrProviderCompany
 */
@Data
@Entity
@Table(name = "hr_provider_company")
public class HrProviderCompany {
	@Schema(type = "Long", description = "Company ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Company Name", example = "Plexus Inc.")
	@Column(name = "name", nullable = false)
	private String name;

	@Schema(type = "string", description = "Company Description", example = "A talent provider company.")
	@Column(name = "description")
	private String description;

	public HrProviderCompany(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public HrProviderCompany() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HrProviderCompany company = (HrProviderCompany) o;
		return id.equals(company.id) && Objects.equals(name, company.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
