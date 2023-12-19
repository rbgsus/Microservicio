package com.plexus.plextalent.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.plexus.plextalent.dto.TechnicalInterviewerDTO;
import com.plexus.plextalent.model.TechnicalInterviewer;

@Mapper(componentModel = "spring")
public interface TechnicalInterviewerMapper {

    TechnicalInterviewerMapper INSTANCE = Mappers.getMapper(TechnicalInterviewerMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastname1", source = "lastname1")
    @Mapping(target = "lastname2", source = "lastname2")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "mobilePhone", source = "mobilePhone")
    @Mapping(target = "corporativeUsername", source = "corporativeUsername")
    @Mapping(target = "technologyId", source = "technology.id")
    @Mapping(target = "technologyName", source = "technology.name")
    @Mapping(target = "technicalRoleId", source = "technicalRole.id")
    @Mapping(target = "technicalRoleName", source = "technicalRole.name")
    TechnicalInterviewerDTO technicalInterviewerToDTO(TechnicalInterviewer technicalInterviewer);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "lastname1", source = "lastname1")
    @Mapping(target = "lastname2", source = "lastname2")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "mobilePhone", source = "mobilePhone")
    @Mapping(target = "corporativeUsername", source = "corporativeUsername")
    @Mapping(target = "technology.id", source = "technologyId")
    @Mapping(target = "technology.name", source = "technologyName")
    @Mapping(target = "technicalRole.id", source = "technicalRoleId")
    @Mapping(target = "technicalRole.name", source = "technicalRoleName")
    TechnicalInterviewer dtoToTechnicalInterviewer(TechnicalInterviewerDTO technicalInterviewerDTO);

}
