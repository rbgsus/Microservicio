package com.plexus.plextalent.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * This class represents the relationship between TechnicalInterviewer and Technology
 */
@Data
@Entity
@IdClass(InterviewerTechnologyId.class)
public class InterviewerTechnology implements Serializable {

    private static final long serialVersionUID = -1839037324690973681L;

    @Id
    @ManyToOne
    @Schema(type = "Long", description = "Technical Interviewer ID", example = "1")
    private TechnicalInterviewer technicalInterviewer;

    @Id
    @ManyToOne
    @Schema(type = "Long", description = "Technology ID", example = "1")
    private Technology technology;
}
