<%@page contentType="text/html;charset=ISO-8859-1" language="java"%>
<%@taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>

<title>Zero 2.0 - Sales Page</title>


	<h2>Search Sales</h2>
	
	<s:form beanclass="com.zero.action.SalesActionBean" focus="">
		<table>
			<tr>
			  <td colspan="2">
			   SRN: <s:text name="salesRecordNumber"></s:text><s:submit name="SearchSRN" value="Submit"></s:submit>
			  </td>
			</tr>
		</table>
		<br><br>
		<fieldset>
			<legend>Order Details for : ${actionBean.salesRecordNumber}</legend>
			  Code : ${actionBean.code} <br>
     	      Quantity : ${actionBean.quantity} <br>
     	      Sale Price : ${actionBean.salePrice} <br>
     	      Total Price : ${actionBean.totalPrice} <br>
		</fieldset>
		<br>
		<fieldset>
			<legend>Buyer Details</legend>
			 Buyer Name : ${actionBean.buyerName} <br>
     	     Buyer Address : ${actionBean.buyerAddress} <br>
     	     Buyer Country : ${actionBean.buyerCountry} <br>
     	     Buyer State : ${actionBean.buyerState} <br>
		</fieldset>
		<br>
		<fieldset>
			<legend>Sage Details</legend>
			 Sage Company ${actionBean.sageERP} <br>
			 Sage Order No: ${actionBean.sageOrder} <br>
			 Allocation : ${actionBean.allocation} <br>
		</fieldset>
		
	</s:form>
