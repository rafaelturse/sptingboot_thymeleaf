package com.jturse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jturse.dao.DepartmentDao;
import com.jturse.domain.Department;

@Service
public class DepartmentService implements IDepartmentService {

	@Autowired
	private DepartmentDao dao;

	@Transactional(readOnly = false)
	@Override
	public void save(Department department) {
		dao.save(department);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(Department department) {
		dao.update(department);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	@Override
	public Department findById(Long id) {
		return dao.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Department> listAll() {
		return dao.listAll();
	}

	@Override
	public boolean departmentHasPosition(Long id) {
		if (findById(id).getPositions().isEmpty()) {
			return false;
		}

		return true;
	}
}