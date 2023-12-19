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
 * This class defines objects of type technology
 */
@Data
@Entity
@Table(name = "technology")
public class Technology {

	@Schema(type = "Long", description = "Technology ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Technology Name", example = "Java")
	@Column(name = "name", nullable = false)
	private String name;

	@Schema(type = "string", description = "Technology Description", example = "Programming language")
	@Column(name = "description")
	private String description;

	public Technology(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Technology() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Technology technology = (Technology) o;
		return id.equals(technology.id) && Objects.equals(name, technology.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}
}
