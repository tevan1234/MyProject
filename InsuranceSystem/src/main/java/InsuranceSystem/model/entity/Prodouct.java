package InsuranceSystem.model.entity;

import lombok.Data;

@Data
public class Prodouct {
	public int prodouctId;
	public String prodouctType;//險種
	public Double price;//年繳
	public Boolean prodouctStatus;// 上架中/停賣
	public int Sales;//售出件數
}
