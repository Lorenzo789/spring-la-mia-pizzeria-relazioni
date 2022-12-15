package org.generation.italy.demo.pojo;

import java.util.List;

import org.generation.italy.demo.interf.PriceableInt;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Pizza implements PriceableInt{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	@NotEmpty(message = "the name can't be empty")
	private String name;
	
	@Column
	@Lob
	private String description;
	
	@Column
	@NotNull(message = "price can't be null")
	@Min(value = 1, message = "the price must be bigger than 0")
	private int price;
	
	@OneToMany(mappedBy = "pizza")
	private List<Promotion> promos;
	
	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {}
	
	public Pizza(String name, String description, int price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
		setPromos(promos);
	}
	
	public Pizza(String name, String description, int price, List<Ingredient> ingredients) {
		
		this(name, description, price);
		setIngredients(ingredients);
	}

	//setter and getter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<Promotion> getPromos() {
		return promos;
	}

	public void setPromos(List<Promotion> promos) {
		this.promos = promos;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	//setter and getter

	public void addIngredients(Ingredient ingredient) {
		
		boolean finded = false;
		
		for(Ingredient ing : getIngredients()) {
		
			if (ing.getId() == ingredient.getId()) {
				finded = true;
			}
			
		}
		
		if (!finded) {
			getIngredients().add(ingredient);
		}
	}
	
	public void removeIngredients(Ingredient ingredient) {
		getIngredients().remove(ingredient);
	}
	
	//funzione per eliminare un ingrediente ed eliminarlo anche nelle pizze che lo possiedono
	public void deleteIngredient(Ingredient ingredient) {
		
		this.ingredients.remove(ingredient);
		ingredient.getPizze().remove(this);
	}
	
	@Override
	public String toString() {
		return "name: " + getName()
		+ "\nDescription: " + getDescription()
		+ "\nPrice: " + getPrice();
	}
}
