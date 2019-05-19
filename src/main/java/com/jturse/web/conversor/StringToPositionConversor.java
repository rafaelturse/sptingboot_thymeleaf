package com.jturse.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jturse.domain.Position;
import com.jturse.service.PositionService;

@Component
public class StringToPositionConversor implements Converter<String, Position> {

	@Autowired
	private PositionService service;

	@Override
	public Position convert(String text) {
		if (text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
		return service.findById(id);
	}
}