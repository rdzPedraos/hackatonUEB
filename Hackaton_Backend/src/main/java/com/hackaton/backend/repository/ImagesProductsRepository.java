package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.ImagesProductsDTO;

public interface ImagesProductsRepository extends CrudRepository<ImagesProductsDTO, Long>{
}
