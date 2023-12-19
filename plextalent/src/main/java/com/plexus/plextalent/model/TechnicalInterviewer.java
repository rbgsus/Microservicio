package com.plexus.plextalent.model;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This class defines objects of type TechnicalInterviewer
 */
@Data
@Entity
@Table(name = "technical_interviewer")
public class TechnicalInterviewer {

	@Schema(type = "Long", description = "Interviewer ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Interviewer First Name", example = "John")
	@Column(name = "name", nullable = false)
	private String name;

	@Schema(type = "string", description = "Interviewer Last Name 1", example = "Doe")
	@Column(name = "lastname1", nullable = false)
	private String lastname1;

	@Schema(type = "string", description = "Interviewer Last Name 2", example = "Smith")
	@Column(name = "lastname2", nullable = false)
	private String lastname2;

	@Schema(type = "string", description = "Email", example = "usuario@plexus.com")
	@Column(name = "email", nullable = false)
	private String email;

	@Schema(type = "string", description = "Phone Number", example = "123-456-7890")
	@Column(name = "phone")
	private String phone;

	@Schema(type = "string", description = "Mobile Phone Number", example = "987-654-3210")
	@Column(name = "mobile_phone")
	private String mobilePhone;

	@Schema(type = "string", description = "Corporate Username", example = "johndoe")
	@Column(name = "corporative_username", nullable = false, unique = true)
	private String corporativeUsername;

	@ManyToOne
	@JoinColumn(name = "technology_id", referencedColumnName = "id")
	private Technology technology;

	@ManyToOne
	@JoinColumn(name = "technical_role_id", referencedColumnName = "id")
	private TechnicalRole technicalRole;

	public TechnicalInterviewer(Long id, String name, String lastname1, String lastname2, String email, String phone,
			String mobilePhone, String corporativeUsername, Technology technology, TechnicalRole technicalRole) {
		this.id = id;
		this.name = name;
		this.lastname1 = lastname1;
		this.lastname2 = lastname2;
		this.email = email;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.corporativeUsername = corporativeUsername;
		this.technology = technology;
		this.technicalRole = technicalRole;
	}

	public TechnicalInterviewer() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TechnicalInterviewer interviewer = (TechnicalInterviewer) o;
		return Objects.equals(corporativeUsername, interviewer.corporativeUsername);
	}

	@Override
	public int hashCode() {
		return Objects.hash(corporativeUsername);
	}
}
