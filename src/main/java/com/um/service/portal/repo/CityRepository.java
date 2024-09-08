package com.um.service.portal.repo;

import com.um.service.portal.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City,Integer> {
    @Query(value = "select * from city where state_id=:state_id",nativeQuery = true)
    List<City> findCityByStateId(Integer state_id);
}
