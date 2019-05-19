package com.jturse.dao;

import java.util.List;

import com.jturse.domain.Department;

public interface IDepartmentDao {

	public void save(Department department);

	public void update(Department department);

	public void delete(Long id);

	public Department findById(Long id);

	public List<Department> listAll();
}