package utils;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.util.StringUtils;

import com.plexus.plextalent.model.HrInterviewer;
import com.plexus.plextalent.model.HrProviderCompany;
import com.plexus.plextalent.repository.HrProviderCompanyRepository;
import com.plexus.plextalent.service.impl.HrInterviewerServiceImpl;

public class ValidationInterviewer {
	public static String validateInterviewer(HrInterviewer interviewer) {
		StringBuilder errorMessage = new StringBuilder();

		// Valida los campos requeridos del entrevistador
		if (!StringUtils.hasText(interviewer.getName())) {
			errorMessage.append("Nombre es un campo obligatorio. ");
		}
		if (!StringUtils.hasText(interviewer.getLastname1())) {
			errorMessage.append("Primer apellido es un campo obligatorio. ");
		}

		if (!StringUtils.hasText(interviewer.getEmail()) || !isValidEmail(interviewer.getEmail())) {
			errorMessage.append("El campo email no es válido. ");
		}

		if (!StringUtils.hasText(interviewer.getCorporativeUsername())) {
			errorMessage.append("El nombre de usuario corporativo no es válido. ");
		}

		if (!StringUtils.hasText(interviewer.getCompany().getName())) {
			errorMessage.append("El nombre de la empresa es obligatorio. ");
		}

		return errorMessage.toString();
	}

	// Assign the company by name if it exists
	public static void assignCompanyIfExist(HrInterviewer hrInterviewer,
			HrProviderCompanyRepository hrProviderCompanyRepository) {

		List<HrProviderCompany> availableCompanies = hrProviderCompanyRepository.findAll();

		Optional<HrProviderCompany> selectedCompany = availableCompanies.stream()
				.filter(company -> company.getName().equals(hrInterviewer.getCompany().getName())).findFirst();
		hrInterviewer.setCompany(selectedCompany.orElse(null));

	}

	public static String checkIfExistInterviewer(HrInterviewer hrInterviewer,
			HrInterviewerServiceImpl interviewerServiceImpl) {

		List<HrInterviewer> allInterviewer = interviewerServiceImpl.getAllHrInterviewers();

		StringBuilder errorMessage = new StringBuilder();
		Optional<HrInterviewer> existeInterviewer = allInterviewer.stream().filter(
				interviewer -> interviewer.getCorporativeUsername().equals(hrInterviewer.getCorporativeUsername()))
				.findFirst();

		if (existeInterviewer.isPresent()) {
			errorMessage
					.append("El entrevistador ya existe en la lista, tiene el ID: " + existeInterviewer.get().getId());
		}

		return errorMessage.toString();
	}

	public static String validateCompany(HrProviderCompany hrProviderCompany) {
		StringBuilder errorMessage = new StringBuilder();

		// Valida los campos requeridos de la empresa
		if (!StringUtils.hasText(hrProviderCompany.getName())) {
			errorMessage.append("El nombre de la empresa es obligatorio. ");
		}

		return errorMessage.toString();
	}

	public static boolean isValidEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
}
