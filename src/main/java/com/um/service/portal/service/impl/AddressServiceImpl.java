package com.um.service.portal.service.impl;

import com.um.service.portal.entity.City;
import com.um.service.portal.entity.Country;
import com.um.service.portal.entity.State;
import com.um.service.portal.repo.CityRepository;
import com.um.service.portal.repo.CountryRepository;
import com.um.service.portal.repo.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl {

    private final CountryRepository countryRepository;
    private final StateRepository stateRepository;
    private final CityRepository cityRepository;

    public List<Country> getAllCountry(){
        return this.countryRepository.findAll();
    }
    public List<State> getStateByCountryId(Integer country_id){
       return this.stateRepository.findStateByCountryId(country_id);
    }
    public List<City> getCityByStateId(Integer state_id){
        return this.cityRepository.findCityByStateId(state_id);
    }
}
