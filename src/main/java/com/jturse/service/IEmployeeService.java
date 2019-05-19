package com.jturse.service;

import java.time.LocalDate;
import java.util.List;

import com.jturse.domain.Employee;

public interface IEmployeeService {

	public void save(Employee employee);

	public void update(Employee employee);

	public void delete(Long id);

	public Employee findById(Long id);

	public List<Employee> listAll();

	public List<Employee> listByName(String nome);

	public List<Employee> listByPosition(Long id);

	public List<Employee> listByEntryAndExit(LocalDate entry, LocalDate exit);
}