package com.jturse.dao;

import java.util.List;

import com.jturse.domain.Position;

public interface IPositionDao {

	public void save(Position position);

	public void update(Position position);

	public void delete(Long id);

	public Position findById(Long id);

	public List<Position> listAll();
}