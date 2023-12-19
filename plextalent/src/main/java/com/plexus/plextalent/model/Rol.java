package com.plexus.plextalent.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This class defines objects of type rol
 */
@Data
@Entity
@Table(name = "roles")
public class Rol {
	@Schema(type = "Long", description = "Rol ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Rol name", example = "admin")
	private String rolName;

	public Rol(Long id, String rolName) {
		this.id = id;
		this.rolName = rolName;
	}

	public Rol() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Rol rol = (Rol) o;
		return id.equals(rol.id) && rolName.equals(rol.rolName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, rolName);
	}
}
