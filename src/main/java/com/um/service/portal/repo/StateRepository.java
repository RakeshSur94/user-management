package com.um.service.portal.repo;

import com.um.service.portal.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateRepository extends JpaRepository<State,Integer> {
    @Query(value = "select * from state where country_id=:country_id",nativeQuery = true)
    List<State> findStateByCountryId(Integer country_id);
}
