package InsuranceSystem.model.entity;

import lombok.Data;

@Data
public class Order {
	private int orderId;
	private String holder;//要保人
	private String holderCardNumber;//要保人身份證字號
	private String insured;//被保人
	private String InsuredCardNumber;//被保人身份證字號
	private String orderType;//險種
	private int payType;//繳費年期
	private Boolean orderStatus;//保單繳費狀態(繳完/未繳完)
	private String orderDate;//簽約日期
	private String charge;//負責業務
	private String contact;//業務員聯繫方式
	private String comment;
}
