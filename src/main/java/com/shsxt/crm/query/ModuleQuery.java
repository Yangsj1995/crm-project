package com.shsxt.crm.query;


import com.shsxt.crm.base.BaseQuery;

public class ModuleQuery extends BaseQuery {
	
	private String moduleName;
	private String optValue;
	private String parentModuleName;
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	public String getParentModuleName() {
		return parentModuleName;
	}
	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}
	
	
}
