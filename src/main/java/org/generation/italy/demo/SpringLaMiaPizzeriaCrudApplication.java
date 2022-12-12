package org.generation.italy.demo;

import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private DrinkService drinkService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Pizza p1 = new Pizza("Margherita", "buona", 9);
		Pizza p2 = new Pizza("Diavola", "super", 10);
		Pizza p3 = new Pizza("Margherita di bufala", "da paura", 12);
		Pizza p4 = new Pizza("Salsiccia e funghi", "buonissima", 10);
		Pizza p5 = new Pizza("Crostino", "ce sta", 9);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		
		List<Pizza> pizze = pizzaService.findAll();
		
		
		Drink d1 = new Drink("Mojito", null, 12);
		Drink d2 = new Drink("Mojitone", "Good", 14);
		Drink d3 = new Drink("Moscow Mule", null, 10);
		Drink d4 = new Drink("Negroni", null, 13);
		Drink d5 = new Drink("Talisker", null, 15);
		
		drinkService.save(d1);
		drinkService.save(d2);
		drinkService.save(d3);
		drinkService.save(d4);
		drinkService.save(d5);
		
		List<Drink> drinks = drinkService.findAll();
	}

}
