package com.goods.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class DataGrid {
	
	private Long total = 0L;
	
	private List rows = new ArrayList();
	//动态表格的title字段
	private List title = new ArrayList();


}
