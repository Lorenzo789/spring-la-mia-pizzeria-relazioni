package org.generation.italy.demo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.generation.italy.demo.pojo.Drink;
import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.serv.DrinkService;
import org.generation.italy.demo.serv.IngredientService;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromotionService;
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
	@Autowired
	private PromotionService promoService;
	@Autowired
	private IngredientService ingredientService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Ingredient ing1 = new Ingredient("olio");
		Ingredient ing2 = new Ingredient("farina");
		Ingredient ing3 = new Ingredient("sale");
		
		ingredientService.save(ing1);
		ingredientService.save(ing2);
		ingredientService.save(ing3);
		
		List<Ingredient> allPizzaIngredient = Arrays.asList(new Ingredient[] {
				ing1,
				ing2,
				ing3
		});
		
		Pizza p1 = new Pizza("Margherita", "buona", 9, allPizzaIngredient);
		Pizza p2 = new Pizza("Diavola", "super", 10, allPizzaIngredient);
		Pizza p3 = new Pizza("Margherita di bufala", "da paura", 12, allPizzaIngredient);
		Pizza p4 = new Pizza("Salsiccia e funghi", "buonissima", 10, allPizzaIngredient);
		Pizza p5 = new Pizza("Crostino", "ce sta", 9);
		Pizza p6 = new Pizza("Margheritona", null, 20, allPizzaIngredient);
		
		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		pizzaService.save(p4);
		pizzaService.save(p5);
		pizzaService.save(p6);
		
		List<Pizza> pizze = pizzaService.findAll();
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		
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
		
		
		Promotion promo1 = new Promotion(LocalDate.now(), LocalDate.now().plusDays(2), "bronze", p1);
		Promotion promo2 = new Promotion(LocalDate.now(), LocalDate.now().plusDays(2), "silver", p2);
		Promotion promo3 = new Promotion(LocalDate.now(), LocalDate.now().plusDays(2), "gold", p2);
		
		promoService.save(promo1);
		promoService.save(promo2);
		promoService.save(promo3);
		
		List<Promotion> promos = promoService.findAll();
	}

}
