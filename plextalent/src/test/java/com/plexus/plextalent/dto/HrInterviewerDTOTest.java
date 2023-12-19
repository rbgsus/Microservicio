package com.plexus.plextalent.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import com.plexus.plextalent.mapper.HrInterviewerMapperImpl;
import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.model.HrProviderCompany;

public class HrInterviewerDTOTest {

	@Test
	void testConstructor() {
		HrProviderCompany company = new HrProviderCompany(1L, "Company1", "Description1");
		HrInterviewer interviewer1 = new HrInterviewer(1L, "Interviewer1", "LastName1", "LastName2",
				"interviewer1@example.com", "123456789", "987654321", "Username1", company);
		HrInterviewer interviewer2 = new HrInterviewer(2L, "Interviewer2", "LastName1", "LastName2",
				"interviewer2@example.com", "123456780", "987654320", "Username2", company);

		List<HrInterviewerDTO> hrInterviewerList = new ArrayList<>();
		hrInterviewerList.add(HrInterviewerMapperImpl.INSTANCE.hrInterviewerToDTO(interviewer1));
		hrInterviewerList.add(HrInterviewerMapperImpl.INSTANCE.hrInterviewerToDTO(interviewer2));
		Pageable pageable = Pageable.ofSize(10).withPage(0);

		HrInterviewerDTOPage hrInterviewerDTOPage = new HrInterviewerDTOPage(pageable, hrInterviewerList);

		assertEquals(pageable, hrInterviewerDTOPage.getPageable());
		assertEquals(hrInterviewerList, hrInterviewerDTOPage.getInterviewerList());

	}

}
