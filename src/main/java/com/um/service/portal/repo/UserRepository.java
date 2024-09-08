package com.um.service.portal.repo;

import com.um.service.portal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
    UserEntity findByEmailAndPassword(String email,String password);
}
