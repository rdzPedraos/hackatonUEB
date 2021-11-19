package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.ContractsDTO;

public interface ContractsRepository extends CrudRepository<ContractsDTO, Long>{
}
