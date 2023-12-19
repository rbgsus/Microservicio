package com.plexus.plextalent.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HrInterviewerDTO {

	@Schema(type = "Long", description = "Interviewer ID", example = "1")
	private Long id;

	@Schema(type = "string", description = "Interviewer First Name", example = "Jesus")
	private String name;

	@Schema(type = "string", description = "Interviewer Last Name 1", example = "Rodriguez")
	private String lastname1;

	@JsonIgnore
	@Schema(type = "string", description = "Interviewer Last Name 2", example = "Begines")
	private String lastname2;

	@Schema(type = "string", description = "Email", example = "usuario@plexus.com")
	private String email;

	@JsonIgnore
	@Schema(type = "string", description = "Phone Number", example = "955903344")
	private String phone;

	@Schema(type = "string", description = "Mobile Phone Number", example = "667788990")
	private String mobilePhone;

	@Schema(type = "string", description = "Corporate Username", example = "JesusRB")
	private String corporativeUsername;

	@JsonIgnore
	@Schema(type = "Long", description = "Company ID", example = "1")
	private Long companyId;

	@Schema(type = "string", description = "Company Name", example = "Plexus Inc.")
	private String companyName;

	@JsonIgnore
	@Schema(type = "string", description = "Company Description", example = "A talent provider company.")
	private String companyDescription;

}
