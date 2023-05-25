package com.example.WeatherDemo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String mainWeather;
    private String description;
    @JsonBackReference
    @ManyToOne
    Forecast forecast;

    public Weather() {
    }

    public Weather(int id, String mainWeather, String description, Forecast forecast) {
        this.id = id;
        this.mainWeather = mainWeather;
        this.description = description;
        this.forecast = forecast;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
