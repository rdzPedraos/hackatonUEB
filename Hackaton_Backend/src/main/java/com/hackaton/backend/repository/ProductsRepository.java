package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.ProductsDTO;

public interface ProductsRepository extends CrudRepository<ProductsDTO, Long>{

}
