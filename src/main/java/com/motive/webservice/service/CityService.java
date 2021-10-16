package com.motive.webservice.service;

import java.util.List;
import java.util.Optional;

import com.motive.webservice.exception.CityAlreadyExistException;
import com.motive.webservice.exception.CityNotFoundExcepion;
import com.motive.webservice.model.City;
import com.motive.webservice.repository.CityRepository;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityService {

    private final CityRepository ilRepositoy;

    public List<City> getCities(String name){
        if(name == null){
            return ilRepositoy.findAll();
        }else{
            return ilRepositoy.findAllByName(name);
        }
        
    }

    public City createCity(City newCity) {
        System.out.println("************************************LOG BURAYA GİRDİ*******************************");
        Optional<City> cityByName = ilRepositoy.findByName(newCity.getName());
        System.out.println("************************************LOG BURAYA GİRDİ");
        if(cityByName.isPresent()){
            System.out.println("LOG BURAYA GİRDİ");
            throw new CityAlreadyExistException("City already exists with name: " + newCity.getName());
        }
        return ilRepositoy.save(newCity);
        
    }

    public void deleteCity(String id) {
        ilRepositoy.deleteById(id);
    }

    public City getCityById(String id) {
        return ilRepositoy.findById(id)
            .orElseThrow(() -> new CityNotFoundExcepion("City not found with id: " +id));
    }
    public void updateCity(String id, City newCity){
        City oldCity = getCityById(id);
        oldCity.setName(newCity.getName());
        ilRepositoy.save(oldCity);
    }

    
}
