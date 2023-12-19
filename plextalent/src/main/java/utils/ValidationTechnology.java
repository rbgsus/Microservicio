package utils;

import org.springframework.util.StringUtils;

public class ValidationTechnology {

	public static String validateTechnology(String str) {
		StringBuilder errorMessage = new StringBuilder();

		if (!StringUtils.hasText(str)) {
			errorMessage.append("El nombre de la tecnología es obligatorio y no puede estar vacío. ");
		}

		return errorMessage.toString();
	}

}
