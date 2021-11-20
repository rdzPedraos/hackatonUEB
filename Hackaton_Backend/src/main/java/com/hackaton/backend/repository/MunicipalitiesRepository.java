package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.MunicipalitiesDTO;

public interface MunicipalitiesRepository extends CrudRepository<MunicipalitiesDTO, Long>{
}
