package com.example.selecteur.repository;

import com.example.selecteur.model.LogicielRadiologie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogicielRadiologieRepository extends JpaRepository<LogicielRadiologie, Long> {
    List<LogicielRadiologie> findByCategoryOrPlatform(String category, String platform);
}