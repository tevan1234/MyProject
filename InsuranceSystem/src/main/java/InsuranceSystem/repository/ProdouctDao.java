package InsuranceSystem.repository;

import java.util.List;
import java.util.Map;

import InsuranceSystem.model.entity.Prodouct;

public interface ProdouctDao {
	
	List<Prodouct> findALLProdoucts();
	
	Prodouct getProdouct(Integer prodouctId);
	
	Prodouct getProdouctByName(String productType);
	
	void addProduct(Prodouct product);
	
	void updateProductPrice(Integer prodouctId,double price);
	
	void updateProdouctStatus(Integer prodouctId,Boolean prodouctStatus);
	
	void deleteProdouct(Integer prodouctId);
	
	Map<String, Integer>Ranking() ;
}
