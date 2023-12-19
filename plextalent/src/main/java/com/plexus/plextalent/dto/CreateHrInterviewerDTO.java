package com.plexus.plextalent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateHrInterviewerDTO {

//	@Schema(description = "ID of the HR Interviewer")
//	private Long id;

	@Schema(description = "Name of the HR Interviewer")
	private String name;

	@Schema(description = "First last name of the HR Interviewer")
	private String lastname1;

//	@Schema(description = "Second last name of the HR Interviewer")
//	private String lastname2;
	
	@Schema(description = "Email of the HR Interviewer")
	private String email;

//	@Schema(description = "Phone number of the HR Interviewer")
//	private String phone;

	@Schema(description = "Mobile phone number of the HR Interviewer")
	private String mobilePhone;

	@Schema(description = "Corporate username of the HR Interviewer")
	private String corporativeUsername;

//	@Schema(description = "ID of the HR Interviewer")
//	private Long companyId;

	@Schema(description = "Company name of the HR Interviewer")
	private String companyName;

//	@Schema(description = "Description of the HR Interviewer's company")
//	private String companyDescription;
}
