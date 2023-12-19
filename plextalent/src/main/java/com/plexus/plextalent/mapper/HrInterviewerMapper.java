package com.plexus.plextalent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.plexus.plextalent.dto.HrInterviewerDTO;
import com.plexus.plextalent.model.HrInterviewer;

@Mapper(componentModel = "spring")
public interface HrInterviewerMapper {

	HrInterviewerMapper INSTANCE = Mappers.getMapper(HrInterviewerMapper.class);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "lastname1", source = "lastname1")
	@Mapping(target = "lastname2", source = "lastname2")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "phone", source = "phone")
	@Mapping(target = "mobilePhone", source = "mobilePhone")
	@Mapping(target = "corporativeUsername", source = "corporativeUsername")
	@Mapping(target = "companyId", source = "company.id")
	@Mapping(target = "companyName", source = "company.name")
	@Mapping(target = "companyDescription", source = "company.description")
	HrInterviewerDTO hrInterviewerToDTO(HrInterviewer hrInterviewer);

	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "lastname1", source = "lastname1")
	@Mapping(target = "lastname2", source = "lastname2", defaultValue = "N/A")
	@Mapping(target = "email", source = "email")
	@Mapping(target = "phone", source = "phone", defaultValue = "N/A")
	@Mapping(target = "mobilePhone", source = "mobilePhone")
	@Mapping(target = "corporativeUsername", source = "corporativeUsername")
	@Mapping(target = "company.id", source = "companyId", ignore = true)
	@Mapping(target = "company.name", source = "companyName")
	@Mapping(target = "company.description", source = "companyDescription", ignore = true)
	HrInterviewer hrInterviewerDTOToEntity(HrInterviewerDTO hrInterviewerDTO);
}
