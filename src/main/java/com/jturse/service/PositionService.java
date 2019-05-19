package com.jturse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jturse.dao.PositionDao;
import com.jturse.domain.Position;

@Service
@Transactional(readOnly = false)
public class PositionService implements IPositionService {

	@Autowired
	private PositionDao dao;

	@Override
	public void save(Position position) {
		dao.save(position);
	}

	@Override
	public void update(Position position) {
		dao.update(position);
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Position findById(Long id) {
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Position> listAll() {
		return dao.listAll();
	}

	@Override
	public boolean positionHasEmployee(Long id) {
		if (findById(id).getEmployees().isEmpty()) {
			return false;
		}

		return true;
	}
}