package com.example.WeatherDemo;

import com.example.WeatherDemo.model.Forecast;
import com.example.WeatherDemo.model.Temperature;
import com.example.WeatherDemo.model.Weather;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class SystemTests {

    @Test
    public void testCreateReadDelete(){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/forecast";

        Forecast forecast = new Forecast(1,null, "Cali", null, 0.0f );
        ResponseEntity<Forecast> entity = restTemplate.postForEntity(url, forecast, Forecast.class);

        Forecast[] forecasts = restTemplate.getForObject(url, Forecast[].class);
        assertThat(forecasts).extracting(Forecast::getLocation).containsOnly("Cali");

        restTemplate.delete(url + "/" + entity.getBody().getId());
        assertThat(restTemplate.getForObject(url, Forecast[].class)).isEmpty();
    }

    @Test
    public void testErrorHandlingReturnsBadRequest(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/wrong";

        try {
            restTemplate.getForEntity(url, String.class);
        } catch (HttpClientErrorException e) {
            Assertions.assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
        }
    }
}
