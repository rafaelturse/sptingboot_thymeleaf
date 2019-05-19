package com.jturse.service;

import java.util.List;

import com.jturse.domain.Position;

public interface IPositionService {

	public void save(Position position);

	public void update(Position position);

	public void delete(Long id);

	public Position findById(Long id);

	public List<Position> listAll();

	public boolean positionHasEmployee(Long id);
}