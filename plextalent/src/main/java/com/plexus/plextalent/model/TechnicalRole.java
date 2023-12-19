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
 * This class defines objects of type TechnicalRole
 */
@Data
@Entity
@Table(name = "technical_role")
public class TechnicalRole {

	@Schema(type = "Long", description = "Technical Role ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Technical Role Name", example = "Software Engineer")
	@Column(name = "name", nullable = false)
	private String name;

	@Schema(type = "string", description = "Technical Role Description", example = "Development role")
	@Column(name = "description")
	private String description;

	public TechnicalRole(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public TechnicalRole() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TechnicalRole technicalRole = (TechnicalRole) o;
		return id.equals(technicalRole.id) && Objects.equals(name, technicalRole.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
