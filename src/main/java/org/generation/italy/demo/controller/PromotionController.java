package org.generation.italy.demo.controller;

import java.util.List;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.serv.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/promo")
public class PromotionController {

	@Autowired
	private PromotionService promoService;
	
	@GetMapping
	public String index(Model model) {
		
		List<Promotion> promos = promoService.findAll();
		
		model.addAttribute("promos", promos);
		
		return "index-promo";
	}
}
