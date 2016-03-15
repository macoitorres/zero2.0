package com.zero.action;

import java.sql.*;

import common.functions.DBConnection;
import common.functions.SageCommon;

import net.sourceforge.stripes.action.ActionBean;  
import net.sourceforge.stripes.action.ActionBeanContext;  
import net.sourceforge.stripes.action.DefaultHandler;  
import net.sourceforge.stripes.action.ForwardResolution;  
import net.sourceforge.stripes.action.Resolution;  
  
public class SalesActionBean implements ActionBean {  
	
	private static final String VIEW = "/WEB-INF/jsp/sales.jsp";
	
	private ActionBeanContext ctxt;
	
	public ActionBeanContext getContext() { return ctxt; }
	public void setContext(ActionBeanContext ctxt) { this.ctxt = ctxt; }
	
	
	private String salesRecordNumber;
	private String sageOrder;
	private String buyerName;
	private String buyerAddress;
	private String buyerCountry;
	private String buyerState;
	private String code;
	private String quantity;
	private String salePrice;
	private String totalPrice;
	private String sageERP;
	private String allocation;
	
	private String error;
	
	
	@DefaultHandler
	public Resolution DefaultSRN() {
		return new ForwardResolution(VIEW);
	}
	
	public Resolution SearchSRN() {		
		String query="SELECT Sales_Record_Number, f_sage_order_no,Code, Quantity, Buyer_Fullname, Buyer_Address_1,Buyer_Country,Buyer_State, Sale_Price,Total_Price,f_eTransaction_ID from order_tracker_xls WHERE Sales_Record_Number = '"+ salesRecordNumber +"' LIMIT 1";
		DBConnection zero = new DBConnection("192.168.20.58:3306","elcell_co_uk_barcode","elcell","elcell123$%^","Mysql");
		SageCommon sc = new SageCommon();
		
		ResultSet zrs, srs;
		
		try{
			zrs = zero.RunSql(query);
		
			while (zrs.next()) {
				this.salesRecordNumber = zrs.getString("Sales_Record_Number");
				this.sageOrder = zrs.getString("f_sage_order_no");
				this.buyerName = zrs.getString("Buyer_Fullname");
				this.buyerAddress = zrs.getString("Buyer_Address_1");
				this.buyerCountry = zrs.getString("Buyer_Country");
				this.buyerState = zrs.getString("Buyer_State");
				this.code = zrs.getString("Code");
				this.quantity = zrs.getString("Quantity");
				this.salePrice = zrs.getString("Sale_Price");
				this.totalPrice = zrs.getString("Total_Price");				
				this.sageERP = sc.getSageCompany(zrs.getString("f_eTransaction_ID"));
			}
			
		}
		catch (SQLException e){
			this.error = e.getMessage();
		}
		finally {
	    	  zero.closecon();
		}
		
		DBConnection sage = new DBConnection("192.168.20.167\\sagedb01sql:1444",this.sageERP,"sa","elcell123$%^","Mssql");
		
		try{
			srs = sage.RunSql("SELECT sol.AllocatedQuantity FROM SOPOrderReturnLine sol INNER JOIN SOPOrderReturn so ON so.SOPOrderReturnID = sol.SOPOrderReturnID WHERE so.CustomerDocumentNo = '"+salesRecordNumber+"'");
			
			while (srs.next()){
				this.allocation =  srs.getString("AllocatedQuantity");
			}
		}
		catch (SQLException e){
			this.error = e.getMessage();
		}
		finally {
	    	  sage.closecon();
		}
		
		return new ForwardResolution(VIEW);
	}
	
	
	public String getSalesRecordNumber(){return this.salesRecordNumber;}
	public void setSalesRecordNumber(String srn){ this.salesRecordNumber = srn;}
	
	public String getSageOrder(){return this.sageOrder;}
	public void setSageOrder(String so){ this.sageOrder = so;}
	
	public String getBuyerName(){return this.buyerName;}
	public void setBuyerName(String name){ this.buyerName = name;}
	
	public String getBuyerAddress(){return this.buyerAddress;}
	public void setBuyerAddress(String addr){ this.buyerAddress = addr;}
	
	public String getBuyerCountry(){return this.buyerCountry;}
	public void setBuyerCountry(String country){ this.buyerCountry = country;}
	
	public String getBuyerState(){return this.buyerState;}
	public void setBuyerState(String state){ this.buyerState = state;}
	
	public String getCode(){return this.code;}
	public void setCode(String code){ this.code = code;}
	
	public String getQuantity(){return this.quantity;}
	public void setQuantity(String qty){ this.quantity = qty;}
	
	public String getSalePrice(){return this.salePrice;}
	public void setSalePrice(String sale){ this.salePrice = sale;}
	
	public String getTotalPrice(){return this.totalPrice;}
	public void setTotalPrice(String total){ this.totalPrice = total;}
	
	public String getSageERP(){return this.sageERP;}
	public void setSageERP(String erp){ this.sageERP = erp;}
	
	public String getAllocation(){return this.allocation;}
	public void setAllocation(String allocated){ this.allocation = allocated;}
	
	public String getError(){return this.error;}
	public void setError(String err){ this.error = err;}
	

}
