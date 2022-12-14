package org.generation.italy.demo.pojo;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Promotion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@NotNull(message = "start_date can't be null")
	private LocalDate start_date;
	
	@Column
	@NotNull(message = "end_date can't be null")
	private LocalDate end_date;
	
	@Column(unique = true)
	@NotEmpty(message = "the title can't be empty")
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "pizza_id", nullable = false)
	private Pizza pizza;
	
	public Promotion() {}
	
	public Promotion(LocalDate start_date, LocalDate end_date, String title, Pizza pizza) {
		
		setStart_date(start_date);
		setEnd_date(end_date);
		setTitle(title);
		setPizza(pizza);
	}

	//setter and getter
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}
	//setter and getter

	@Override
	public String toString() {
		
		return "Start: " + getStart_date()
		+ "\nEnd: " + getEnd_date()
		+ "\nTitle: " + getTitle();
	}
}
