package com.jturse.web.validator;

import java.time.LocalDate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jturse.domain.Employee;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Employee.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		Employee i = (Employee) object;

		LocalDate entrada = i.getDateEntry();

		if (i.getDateExit() != null) {
			if (i.getDateEntry().isBefore(entrada)) {
				errors.rejectValue("dateExit", "PosteriorDataEntrada.funcionario.dataSaida");
			}
		}
	}
}