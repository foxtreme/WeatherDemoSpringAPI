package com.example.WeatherDemo.controller;

import com.example.WeatherDemo.model.Forecast;
import com.example.WeatherDemo.service.ForecastService;
import com.example.WeatherDemo.util.FieldErrorMessage;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ForecastController {

    @Autowired
    ForecastService forecastService;

    @PostMapping("/forecast")
    Forecast create(@Valid @RequestBody Forecast forecast){
        return forecastService.save(forecast);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(
                fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @GetMapping("/forecast")
    Iterable<Forecast> read() {
        return forecastService.findAll();
    }

    @GetMapping("/forecast/{id}")
    Optional<Forecast> findById(@PathVariable Integer id){
        return forecastService.findById(id);
    }

    @GetMapping("/forecast/search")
    Iterable<Forecast> findByQuery(@RequestParam("location") String location){
        return forecastService.findByLocation(location);
    }

    @PutMapping("/forecast")
    ResponseEntity<Forecast> update(@RequestBody Forecast forecast){
        if (forecastService.findById(forecast.getId()).isPresent())
            return new ResponseEntity<>(forecastService.save(forecast), HttpStatus.OK);
        else
            return new ResponseEntity<>(forecast, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/forecast/{id}")
    void delete(@PathVariable Integer id){
        forecastService.deleteById(id);
    }

    @GetMapping("/wrong")
    Forecast somethingIsWrong(){
        throw new ValidationException("Something is wrong");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    String exceptionHandler(ValidationException e){
        return e.getMessage();
    }
}
