package org.generation.italy.demo.serv;

import java.util.List;
import java.util.Optional;
import org.generation.italy.demo.pojo.Promotion;
import org.generation.italy.demo.repo.PromotionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionService {

	@Autowired
	private PromotionRepo promoRepo;
	
	public void save(Promotion promo) {
		
		promoRepo.save(promo);
	}
	public List<Promotion> findAll() {
		
		return promoRepo.findAll();
	}
	public Optional<Promotion> findPromoById(int id) {
		
		return promoRepo.findById(id);
	}
	public void delete(Promotion promo) {
		
		promoRepo.delete(promo);
	}
	public void deleteById(int id) {
		
		promoRepo.deleteById(id);
	}
}
