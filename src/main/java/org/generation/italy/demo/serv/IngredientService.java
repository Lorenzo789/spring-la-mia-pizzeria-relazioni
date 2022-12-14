package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;

import org.generation.italy.demo.pojo.Ingredient;
import org.generation.italy.demo.repo.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepo ingredientRepo;
	
	public void save(Ingredient ingredient) {
		
		ingredientRepo.save(ingredient);
	}
	
	public List<Ingredient> findAll() {
		
		return ingredientRepo.findAll();
	}
	
	public Ingredient findById(int id) {
		
		return ingredientRepo.findById(id).get();
	}
	
	public void delete(Ingredient ingredient) {
		
		ingredientRepo.delete(ingredient);
	}
	
	public void deleteById(int id) {
		
		ingredientRepo.deleteById(id);
	}
}
