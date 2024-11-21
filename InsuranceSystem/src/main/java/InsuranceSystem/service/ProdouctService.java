package InsuranceSystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import InsuranceSystem.model.dto.ProdouctDto;
import InsuranceSystem.model.entity.Prodouct;
import InsuranceSystem.repository.ProdouctDao;
import InsuranceSystem.repository.ProdouctDaoImpl;

public class ProdouctService {
	private ProdouctDao prodouctDao = new ProdouctDaoImpl();
	public List<ProdouctDto> findAll(){
		List<ProdouctDto> prodouctDtos = new ArrayList<>();
		List<Prodouct> prodoucts = prodouctDao.findALLProdoucts();
		
		for (Prodouct prodouct:prodoucts) {
			ProdouctDto prodouctDto = new ProdouctDto();
			prodouctDto.setPid(prodouct.getProdouctId());
			prodouctDto.setPtype(prodouct.getProdouctType());
			prodouctDto.setPprice(prodouct.getPrice());
			prodouctDto.setPstatus(prodouct.getProdouctStatus());
			prodouctDto.setPsales(prodouct.getSales());
			
			prodouctDtos.add(prodouctDto);
		}
		return prodouctDtos;
	}
	
	public ProdouctDto getProdouct(String prodouctId) {
		Prodouct prodouct = prodouctDao.getProdouct(Integer.parseInt(prodouctId));
		if(prodouct == null) {
			return null;
		}
		
		ProdouctDto prodouctDto = new ProdouctDto();
		prodouctDto.setPid(prodouct.getProdouctId());
		prodouctDto.setPtype(prodouct.getProdouctType());
		prodouctDto.setPprice(prodouct.getPrice());
		prodouctDto.setPstatus(prodouct.getProdouctStatus());
		prodouctDto.setPsales(prodouct.getSales());
		return prodouctDto;
	}
	
	public void addProdouct(String prodouctType, String price) {
		Boolean prodouctStatus = true;
		int Sales = 0;
		Prodouct prodouct = new Prodouct();
		prodouct.setProdouctType(prodouctType);
		prodouct.setPrice(Double.parseDouble(price));
		prodouct.setProdouctStatus(prodouctStatus);
		prodouct.setSales(Sales);
		prodouctDao.addProduct(prodouct);
	}
	
	public void updateProdouct(String prodouctId,String price,String prodouctStatus) {
		if(!price.isEmpty()) {
			prodouctDao.updateProductPrice(Integer.parseInt(prodouctId), Double.parseDouble(price));
		}
		if(!prodouctStatus.isEmpty()) {
			prodouctDao.updateProdouctStatus(Integer.parseInt(prodouctId), Boolean.parseBoolean(prodouctStatus));
		}
	}
	
	public void deleteProdouct(String prodouctId) {
		prodouctDao.deleteProdouct(Integer.parseInt(prodouctId));
	}
	
	public Map<String, Integer> Ranking(){
		return prodouctDao.Ranking();
	}
}
