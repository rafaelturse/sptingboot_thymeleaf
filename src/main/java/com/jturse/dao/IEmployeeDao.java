package com.jturse.dao;

import java.time.LocalDate;
import java.util.List;

import com.jturse.domain.Employee;

public interface IEmployeeDao {

	public void save(Employee employee);

	public void update(Employee employee);

	public void delete(Long id);

	public Employee findById(Long id);

	public List<Employee> listAll();

	public List<Employee> findByName(String name);

	public List<Employee> listById(Long id);

	public List<Employee> listByEntryAndExit(LocalDate entry, LocalDate exit);

	public List<Employee> listByEntry(LocalDate entry);

	public List<Employee> listByExit(LocalDate exit);
}