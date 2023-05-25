package com.example.WeatherDemo;

import com.example.WeatherDemo.controller.ForecastController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherDemoApplicationTests {

	@Autowired
	ForecastController forecastController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(forecastController);
	}

}
