package org.generation.italy.demo.controller;

import java.util.List;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.serv.IngredientService;
import org.generation.italy.demo.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	@Autowired
	private PizzaService pizzaService;
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		model.addAttribute("ingredients", ingredients);
		
		return "index-ingredient";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);
		
		return "create-ingredient";
	}
	
	@PostMapping("/create")
	public String store(@Valid Ingredient ingredient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/create";
		}
		//da fare per salvare le pizze collegate a quell'ingrediente
		List<Pizza> pizze = ingredient.getPizze();
		for (Pizza p : pizze) {
			
			p.getIngredients().add(ingredient);
		}

		ingredientService.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/update/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		
		Ingredient ingredient = ingredientService.findById(id);
		
		model.addAttribute("ingredient", ingredient);
		
		List<Pizza> pizze = pizzaService.findAll();
		
		model.addAttribute("pizze", pizze);
		
		return "edit-ingredient";
	}
	
	@PostMapping("/update")
	public String update(@Valid Ingredient ingredient, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/ingredient";
		}
		
		Ingredient oldIng = ingredientService.findById(ingredient.getId());
		
		for (Pizza p : oldIng.getPizze()) {

			p.removeIngredients(ingredient);
			
			List<Pizza> pizze = ingredient.getPizze();
			
			for (Pizza pizza : pizze) {
				
				pizza.addIngredients(ingredient);
			}
		}
		
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredient";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Ingredient optIngredient = ingredientService.findById(id);
		
		for (Pizza p : optIngredient.getPizze()) {
			
			p.removeIngredients(optIngredient);
			
		}
		
		ingredientService.delete(optIngredient);
		
		return "redirect:/ingredient";
	}
}
