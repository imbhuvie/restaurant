package com.restaurant.Restaurant;

import com.restaurant.Restaurant.service.implimentation.AllImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication{

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

}
