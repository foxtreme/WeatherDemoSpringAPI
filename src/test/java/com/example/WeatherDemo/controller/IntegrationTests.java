package com.example.WeatherDemo.controller;

import com.example.WeatherDemo.model.Forecast;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    ForecastController forecastController;

    @Test
    public void testCreateReadDelete(){
        Forecast forecast = new Forecast();
        forecast.setLocation("Cali");
        forecast.setWindSpeed(0.0f);
        Forecast forecastResult = forecastController.create(forecast);

        Iterable<Forecast> forecasts = forecastController.read();
        assertThat(forecasts).extracting(Forecast::getLocation).containsOnlyOnce("Cali");

        forecastController.delete(forecastResult.getId());
        assertThat(forecastController.read()).isEmpty();
    }

    @Test()
    public void errorHandlingValidationExceptionThrown(){
        ValidationException thrown = Assertions.assertThrows(
                ValidationException.class,
                () -> {forecastController.somethingIsWrong();}
        );
        Assertions.assertEquals("Something is wrong",  thrown.getMessage());
    }
}
