package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.UsersDTO;

public interface UsersRepository extends CrudRepository<UsersDTO, Long>{

}
