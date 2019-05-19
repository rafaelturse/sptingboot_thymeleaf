package com.jturse.dao;

import org.springframework.stereotype.Repository;

import com.jturse.domain.Position;

@Repository
public class PositionDao extends AbstractDao<Position, Long> implements IPositionDao {

}