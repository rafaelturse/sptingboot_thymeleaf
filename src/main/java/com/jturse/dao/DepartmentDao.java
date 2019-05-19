package com.jturse.dao;

import org.springframework.stereotype.Repository;

import com.jturse.domain.Department;

@Repository
public class DepartmentDao extends AbstractDao<Department, Long> implements IDepartmentDao {

}