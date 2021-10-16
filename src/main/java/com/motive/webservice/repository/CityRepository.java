package com.motive.webservice.repository;

import java.util.List;
import java.util.Optional;

import com.motive.webservice.model.City;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CityRepository extends MongoRepository<City,String> {
    List<City> findAllByName(String name);
    Optional<City> findByName(String name);
    

}
