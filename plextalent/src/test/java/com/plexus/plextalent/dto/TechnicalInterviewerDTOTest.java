package com.plexus.plextalent.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import com.plexus.plextalent.model.TechnicalInterviewer;

public class TechnicalInterviewerDTOTest {

	
	@Test
	void testConstructor() {
		TechnicalInterviewer technicalInterviewer1 = new TechnicalInterviewer(1L, "John1", "Doe", "Smith",
				"john.doe1@example.com", "123-456-7890", "987-654-3210", "johndoe1", null, null);
		TechnicalInterviewer technicalInterviewer2 = new TechnicalInterviewer(2L, "John2", "Doe", "Smith",
				"john.doe2@example.com", "123-456-7891", "987-654-3211", "johndoe2", null, null);
		TechnicalInterviewer technicalInterviewer3 = new TechnicalInterviewer(3L, "John3", "Doe", "Smith",
				"john.doe3@example.com", "123-456-7892", "987-654-3212", "johndoe3", null, null);
		List<TechnicalInterviewer> simulatedCollection = new ArrayList<>();
		simulatedCollection.add(technicalInterviewer1);
		simulatedCollection.add(technicalInterviewer2);
		simulatedCollection.add(technicalInterviewer3);
		Pageable pageable = Pageable.ofSize(10).withPage(0);
		
		TechnicalInterviewerDTO technicalInterviewerDTO = new TechnicalInterviewerDTO(pageable, simulatedCollection);
		
		assertEquals(pageable, technicalInterviewerDTO.getPageable());
		assertEquals(simulatedCollection, technicalInterviewerDTO.getTechnicalInterviewerList());
		
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
}
