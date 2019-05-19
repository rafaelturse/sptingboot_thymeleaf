package com.jturse.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jturse.dao.IEmployeeDao;
import com.jturse.domain.Employee;

@Service
@Transactional(readOnly = true)
public class EmployeeService implements IEmployeeService {

	@Autowired
	private IEmployeeDao dao;

	@Transactional(readOnly = false)
	@Override
	public void save(Employee employee) {
		dao.save(employee);
	}

	@Transactional(readOnly = false)
	@Override
	public void update(Employee employee) {
		dao.update(employee);
	}

	@Transactional(readOnly = false)
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public Employee findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Employee> listAll() {
		return dao.listAll();
	}

	@Override
	public List<Employee> listByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Employee> listByPosition(Long id) {
		return dao.listById(id);
	}

	@Override
	public List<Employee> listByEntryAndExit(LocalDate entry, LocalDate exit) {
		if (entry != null && exit != null) {
			return dao.listByEntryAndExit(entry, exit);
		} else if (entry != null) {
			return dao.listByEntry(entry);
		} else if (exit != null) {
			return dao.listByExit(exit);
		} else {
			return new ArrayList<>();
		}
	}
}