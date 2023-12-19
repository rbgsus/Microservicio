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
 * This class defines objects of type user
 */

@Data
@Entity
@Table(name = "users")
public class User {

	@Schema(type = "Long", description = "User ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Schema(type = "string", description = "User's first name", example = "Nombre1")
	private String userName;
	@Schema(type = "string", description = "User's last name", example = "Apellido1")
	private String userLastName;
	@Schema(type = "string", description = "User's passkey", example = "PassKey1")
	private String passKey;

	public User(Long id, String userName, String userLastName, String passKey) {
		this.id = id;
		this.userName = userName;
		this.userLastName = userLastName;
		this.passKey = passKey;
	}

	public User() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return id.equals(user.id) && userName.equals(user.userName) && userLastName.equals(user.userLastName)
				&& passKey.equals(user.passKey);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, userName, userLastName, passKey);
	}
}
