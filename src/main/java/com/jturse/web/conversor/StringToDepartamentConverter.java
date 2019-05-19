package com.jturse.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jturse.domain.Department;
import com.jturse.service.DepartmentService;

@Component
public class StringToDepartamentConverter implements Converter<String, Department> {

	@Autowired
	private DepartmentService service;

	@Override
	public Department convert(String text) {
		if (text.isEmpty()) {
			return null;
		}

		Long id = Long.valueOf(text);

		return service.findById(id);
	}
}