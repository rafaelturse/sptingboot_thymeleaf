package com.jturse.service;

import java.util.List;

import com.jturse.domain.Department;

public interface IDepartmentService {

	public void save(Department departamento);

	public void update(Department departamento);

	public void delete(Long id);

	public Department findById(Long id);

	public List<Department> listAll();

	public boolean departmentHasPosition(Long id);
}