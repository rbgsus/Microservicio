package com.plexus.plextalent.dto;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plexus.plextalent.model.TechnicalInterviewer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for TechnicalInterviewer.
 */
@Data
@NoArgsConstructor
public class TechnicalInterviewerDTO {
	private Pageable pageable;
	private List<TechnicalInterviewer> technicalInterviewerList;

	@Schema(type = "Long", description = "Technical Interviewer ID", example = "1")
	private Long id;

	@Schema(type = "string", description = "Technical Interviewer First Name", example = "John")
	private String name;

	@Schema(type = "string", description = "Technical Interviewer Last Name 1", example = "Doe")
	private String lastname1;

	@JsonIgnore
	@Schema(type = "string", description = "Technical Interviewer Last Name 2", example = "Smith")
	private String lastname2;

	@Schema(type = "string", description = "Email", example = "usuario@plexus.com")
	private String email;

	@JsonIgnore
	@Schema(type = "string", description = "Phone Number", example = "123-456-7890")
	private String phone;

	@Schema(type = "string", description = "Mobile Phone Number", example = "987-654-3210")
	private String mobilePhone;

	@Schema(type = "string", description = "Corporate Username", example = "johndoe")
	private String corporativeUsername;

	@JsonIgnore
	@Schema(type = "Long", description = "Technology ID", example = "1")
	private Long technologyId;

	@Schema(type = "string", description = "Technology Name", example = "Java")
	private String technologyName;

	@JsonIgnore
	@Schema(type = "Long", description = "Technical Role ID", example = "1")
	private Long technicalRoleId;

	@Schema(type = "string", description = "Technical Role Name", example = "Software Engineer")
	private String technicalRoleName;

	/**
	 * Constructo that creates a new instance of TechnicalInterviewerDTO with the
	 * provided pageable and technical interviewer list
	 * 
	 * @param pageable                 The Pageable object containing the pagination
	 *                                 parameters for the technical interviewer list
	 * 
	 * @param technicalInterviewerList The list of TechnicalInterviewer objects
	 *                                 retrieved from the database
	 */

	public TechnicalInterviewerDTO(Pageable pageable, List<TechnicalInterviewer> technicalInterviewerList) {
		this.pageable = pageable;
		this.technicalInterviewerList = technicalInterviewerList;
	}

}
