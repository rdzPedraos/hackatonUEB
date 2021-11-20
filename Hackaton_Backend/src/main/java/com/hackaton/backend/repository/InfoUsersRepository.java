package com.hackaton.backend.repository;
import org.springframework.data.repository.CrudRepository;

import com.hackaton.backend.model.InfoUserDTO;

public interface InfoUsersRepository extends CrudRepository<InfoUserDTO, Long>{

}
