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
 * This class defines objects of type HrInterviewer
 */

@Data
@Entity
@Table(name = "hr_interviewer")
public class HrInterviewer {
	@Schema(type = "Long", description = "Interviewer ID", example = "1")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Schema(type = "string", description = "Interviewer First Name", example = "Jesus")
	@Column(name = "name", nullable = false)
	private String name;

	@Schema(type = "string", description = "Interviewer Last Name 1", example = "Rodriguez")
	@Column(name = "lastname1", nullable = false)
	private String lastname1;

	@Schema(type = "string", description = "Interviewer Last Name 2", example = "Begines")
	@Column(name = "lastname2")
	private String lastname2;

	@Schema(type = "string", description = "Email", example = "usuario@plexus.com")
	@Column(name = "email", nullable = false)
	private String email;

	@Schema(type = "string", description = "Phone Number", example = "955903344")
	@Column(name = "phone")
	private String phone;

	@Schema(type = "string", description = "Mobile Phone Number", example = "667788990")
	@Column(name = "mobile_phone")
	private String mobilePhone;

	@Schema(type = "string", description = "Corporate Username", example = "JesusRB")
	@Column(name = "corporative_username", nullable = false, unique = true)
	private String corporativeUsername;

	@ManyToOne
	@JoinColumn(name = "company_id", referencedColumnName = "id")
	private HrProviderCompany company;

	public HrInterviewer() {
	}

	public HrInterviewer(Long id, String name, String lastName1, String lastName2, String email, String phone,
			String mobilePhone, String corporativeUsername, HrProviderCompany company) {
		this.id = id;
		this.name = name;
		this.lastname1 = lastName1;
		this.lastname2 = lastName2;
		this.email = email;
		this.phone = phone;
		this.mobilePhone = mobilePhone;
		this.corporativeUsername = corporativeUsername;
		this.company = company;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HrInterviewer interviewer = (HrInterviewer) o;
		return Objects.equals(corporativeUsername, interviewer.corporativeUsername);
	}

	@Override
	public int hashCode() {
		return Objects.hash(corporativeUsername);
	}
}
