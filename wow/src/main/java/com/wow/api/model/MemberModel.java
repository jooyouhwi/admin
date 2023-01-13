package com.wow.api.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.json.JSONArray;

@Data
@NoArgsConstructor  //Default Constructor 추가
public class MemberModel {

	public String userid;
	public String username;
	public String cntCd;
	public String ntCd;
	public String loginId;
	public String passwd;
	
	public String cntName;
	public String pId;
	public String pName;
	public String rId;
	public String rName;
	
	public Integer recid ;
	
}
