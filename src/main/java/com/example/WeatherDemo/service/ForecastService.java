package com.example.WeatherDemo.service;

import com.example.WeatherDemo.model.Forecast;
import org.springframework.data.repository.CrudRepository;

public interface ForecastService extends CrudRepository <Forecast, Integer> {

    Iterable<Forecast> findByLocation(String location);
}
