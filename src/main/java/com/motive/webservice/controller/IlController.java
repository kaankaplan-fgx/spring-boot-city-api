package com.motive.webservice.controller;



import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import com.motive.webservice.exception.CityAlreadyExistException;
import com.motive.webservice.exception.CityNotFoundExcepion;
import com.motive.webservice.model.City;
import com.motive.webservice.service.*;

@RestController
@RequestMapping("/cities")
@AllArgsConstructor
public class IlController {

    private final CityService cityService;

    @GetMapping
    public ResponseEntity<List<City>> getCities(@RequestParam(required = false) String name){

        return new ResponseEntity<>(cityService.getCities(name),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCity(@PathVariable String id){
        return new ResponseEntity<>(getCityById(id), HttpStatus.OK);
    }


     @PostMapping
     public ResponseEntity<City> createCity(@RequestBody City newCity){
         return new ResponseEntity<>(cityService.createCity(newCity), HttpStatus.CREATED);
        
     }

     @PutMapping("/{id}")
     public ResponseEntity<Void> getIl(@PathVariable String id,@RequestBody City newCity){
        return new ResponseEntity<>(HttpStatus.OK);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteCity(@PathVariable String id){
         cityService.deleteCity(id);
         return new ResponseEntity<>(HttpStatus.OK);
     }



     private City getCityById(String id){
        return cityService.getCityById(id);
    
     }

    @ExceptionHandler(CityNotFoundExcepion.class)
    public ResponseEntity<String> handleCityNotFoundException(CityNotFoundExcepion ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CityAlreadyExistException.class)
    public ResponseEntity<String> handeCityAlreadyExistException(CityAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.CONFLICT);
    }
}
