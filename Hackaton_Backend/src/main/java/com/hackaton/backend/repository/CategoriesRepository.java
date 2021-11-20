package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.CategoriesDTO;

public interface CategoriesRepository extends CrudRepository<CategoriesDTO, Long>{

}
