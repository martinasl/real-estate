package com.aacademy.realestate.repository;

import com.aacademy.realestate.model.EstateFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstateFeatureRepository extends JpaRepository<EstateFeature, Long> {

 Optional<EstateFeature> findByFeature(String feature);
}
