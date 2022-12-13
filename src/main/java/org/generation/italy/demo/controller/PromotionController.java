package org.generation.italy.demo.controller;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Pizza;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.serv.PizzaService;
import org.generation.italy.demo.serv.PromotionService;
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
@RequestMapping("/promo")
public class PromotionController {

	@Autowired
	private PromotionService promoService;
	
	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Promotion> promos = promoService.findAllWPizza();
		model.addAttribute("promos", promos);
		
		return "index-promo";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		Promotion promos = new Promotion();
		model.addAttribute("promos", promos);
		
		List<Pizza> pizzas = pizzaService.findAll();
		model.addAttribute("pizzas", pizzas);
		
		return "create-promo";
	}
	
	@PostMapping("/create")
	public String store(@Valid Promotion promo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			
			System.err.println("ERROR ------------------------------------------");
			System.err.println(bindingResult.getAllErrors());
			System.err.println("------------------------------------------------");
			
			redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
			
			return "redirect:/promo/create";
		}
		
		promoService.save(promo);
		
		return "redirect:/promo";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		
		Optional<Promotion> optPromo = promoService.findPromoById(id);
		Promotion promos = optPromo.get();
		
		promoService.delete(promos);
		
		return "redirect:/promo";
	}
}
