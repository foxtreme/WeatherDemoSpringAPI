package com.example.WeatherDemo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

@Entity
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonManagedReference
    @OneToMany(mappedBy = "forecast", cascade = CascadeType.ALL)
    private List<Weather> weathers;
    @NotEmpty
    private String location;
    @OneToOne(cascade =  CascadeType.ALL)
    private Temperature temperature;
    @PositiveOrZero
    private float windSpeed;

    public Forecast() {
    }

    public Forecast(int id, List<Weather> weathers, String location, Temperature temperature, float windSpeed) {
        this.id = id;
        this.weathers = weathers;
        this.location = location;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }

    public boolean isValid(){
        return (getWeathers() != null &&
                getTemperature() != null &&
                getLocation() != null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }
}
