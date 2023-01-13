package com.wow.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  // getter // seeter 동시사용 가능 
public class BoardModel {

	public String comId;
	public String comCd;
	public String retCode;
		
}
