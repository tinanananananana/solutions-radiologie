package com.example.selecteur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.selecteur.model.DispositifMedical;

public interface DispositifMedicalRepository extends JpaRepository<DispositifMedical, Long> {

    List<DispositifMedical> findByTypeOrSurface(String type, Double surface);
}
