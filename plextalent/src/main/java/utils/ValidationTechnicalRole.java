package utils;

import org.springframework.util.StringUtils;

public class ValidationTechnicalRole {

	public static String validateRole(String str) {
		StringBuilder errorMessage = new StringBuilder();

		if (!StringUtils.hasText(str)) {
			errorMessage.append("El nombre es un campo obligatorio. ");
		}

		return errorMessage.toString();
	}

}
