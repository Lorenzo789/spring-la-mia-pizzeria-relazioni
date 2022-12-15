package org.generation.italy.demo.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotEmpty(message = "the name can't be empty")
	private String name;
	
	//non mettere il cascade sulle manytomany
	@ManyToMany(mappedBy = "ingredients")
	private List<Pizza> pizze;

	public Ingredient() {}
	
	public Ingredient(String name) {
		
		setName(name);
	}
	
	public Ingredient(String name, List<Pizza> pizze) {
		
		this(name);
		setPizze(pizze);
	}
	
	//getter and setter
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
	public List<Pizza> getPizze() {
		return pizze;
	}
	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}
	//getter and setter

}
