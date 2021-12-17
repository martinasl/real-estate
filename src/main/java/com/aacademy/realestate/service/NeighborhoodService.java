package com.aacademy.realestate.service;

import com.aacademy.realestate.model.Neighborhood;

import java.util.Set;

public interface NeighborhoodService {

    Neighborhood findByName(String name);

    Set<Neighborhood> findAll();

    Neighborhood save(Neighborhood neighborhood);

    Neighborhood findById(Long id);

}
