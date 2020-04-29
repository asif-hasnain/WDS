package com.nyu.wds;

import java.util.List;

import com.nyu.wds.dbmapper.Invoice;

public class InvoiceDetailsResponse {

	private Response response;
	private List<Invoice> invoiceList;
	public InvoiceDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public InvoiceDetailsResponse(Response response, List<Invoice> invoiceList) {
		super();
		this.response = response;
		this.invoiceList = invoiceList;
	}
	public InvoiceDetailsResponse(Response response) {
		super();
		this.response = response;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public List<Invoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<Invoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	@Override
	public String toString() {
		return "InvoiceDetailsResponse [response=" + response + ", invoiceList=" + invoiceList + "]";
	}
	
	
}
