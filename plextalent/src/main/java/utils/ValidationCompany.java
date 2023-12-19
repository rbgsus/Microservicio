package utils;

import org.springframework.util.StringUtils;

import com.plexus.plextalent.model.HrProviderCompany;

public class ValidationCompany {

	public static String validateCompany(HrProviderCompany company) {
		StringBuilder errorMessage = new StringBuilder();

		if (company == null) {
			errorMessage.append("La empresa no puede ser nula. ");
		}

		if (!StringUtils.hasText(company.getName())) {
			errorMessage.append("El nombre de la empresa es obligatorio y no puede estar vacío. ");
		}

		return errorMessage.toString();
	}
}
