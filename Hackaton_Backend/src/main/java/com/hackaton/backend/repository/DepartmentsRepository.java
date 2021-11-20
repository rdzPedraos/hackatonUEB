package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.DepartmentsDTO;

public interface DepartmentsRepository extends CrudRepository<DepartmentsDTO, Long>{
}
