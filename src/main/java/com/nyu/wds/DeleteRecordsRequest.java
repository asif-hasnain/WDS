package com.nyu.wds;

public class DeleteRecordsRequest {
	private String tableName;
	private int intId1;
	private int intId2;
	private String strId1;
	private String strId2;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getIntId1() {
		return intId1;
	}
	public void setIntId1(int intId1) {
		this.intId1 = intId1;
	}
	public int getIntId2() {
		return intId2;
	}
	public void setIntId2(int intId2) {
		this.intId2 = intId2;
	}
	public String getStrId1() {
		return strId1;
	}
	public void setStrId1(String strId1) {
		this.strId1 = strId1;
	}
	public String getStrId2() {
		return strId2;
	}
	public void setStrId2(String strId2) {
		this.strId2 = strId2;
	}
	@Override
	public String toString() {
		return "DeleteRecordsRequest [tableName=" + tableName + ", intId1=" + intId1 + ", intId2=" + intId2
				+ ", strId1=" + strId1 + ", strId2=" + strId2 + "]";
	}
	
}
