package com.jturse.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jturse.domain.Employee;

@Repository
public class EmployeeDao extends AbstractDao<Employee, Long> implements IEmployeeDao {

	public List<Employee> findByName(String name) {
		return createQuery("select e from Employee e where e.name like concat('%',?1,'%') ", name);
	}

	@Override
	public List<Employee> listById(Long id) {
		return createQuery("select e from Employee e where e.position.id = ?1", id);
	}

	@Override
	public List<Employee> listByEntryAndExit(LocalDate entry, LocalDate exit) {
		StringBuilder jpql = new StringBuilder();

		jpql.append("select e from Employee e ");
		jpql.append("where e.dateEntry >= ?1 and e.dateExit <= ?2 ");
		jpql.append("order by f.dateEntry asc").toString();

		return createQuery(jpql.toString(), entry, exit);
	}

	@Override
	public List<Employee> listByEntry(LocalDate entry) {
		StringBuilder jpql = new StringBuilder();

		jpql.append("select e from Employee e ");
		jpql.append("where e.dateEntry = ?1 ");
		jpql.append("order by e.dateExit asc").toString();

		return createQuery(jpql.toString(), entry);
	}

	@Override
	public List<Employee> listByExit(LocalDate exit) {
		StringBuilder jpql = new StringBuilder();

		jpql.append("select e from Employee e ");
		jpql.append("where e.dateExit = ?1 ");
		jpql.append("order by e.dateEntry asc").toString();

		return createQuery(jpql.toString(), exit);
	}
}