package utils;

import org.springframework.util.StringUtils;

import com.plexus.plextalent.model.TechnicalInterviewer;
import com.plexus.plextalent.service.impl.TechnicalRoleServiceImpl;

public class ValidationTechnicalInterviewer {

	public static String validateTechnicalInterviewer(TechnicalInterviewer technicalInterviewer,
			TechnicalRoleServiceImpl technicalRoleServiceImpl) {
		StringBuilder errorMessage = new StringBuilder();

		if (!StringUtils.hasText(technicalInterviewer.getName())) {
			errorMessage.append("El nombre es un campo obligatorio. ");
		}

		if (!StringUtils.hasText(technicalInterviewer.getLastname1())) {
			errorMessage.append("El primer apellido es un campo obligatorio. ");
		}

		if (!StringUtils.hasText(technicalInterviewer.getEmail())) {
			errorMessage.append("El email es un campo obligatorio. ");
		}

		if (!StringUtils.hasText(technicalInterviewer.getCorporativeUsername())) {
			errorMessage.append("El nombre de usuario corporativo es un campo obligatorio. ");
		}

		if (!StringUtils.hasText(technicalInterviewer.getTechnicalRole().getName())) {
			errorMessage.append("El nombre del rol técnico es un campo obligatorio. ");
		}
		if (technicalRoleServiceImpl.getAllTechnicalRoles().contains(technicalInterviewer.getTechnicalRole())) {
			errorMessage.append("El rol introducido no existe. ");
		}

		return errorMessage.toString();
	}

}
