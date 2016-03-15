package common.functions;

public class SageCommon {

	public String getSageCompany(String erpId){
		String sageCompany = "";
		
		switch(erpId){
			case "2" : sageCompany = "US"; break;
			case "3" : sageCompany = "DE"; break;
			case "4" : sageCompany = "IT"; break;
			case "5" : sageCompany = "JP"; break;
			case "6" : sageCompany = "AU"; break;
			case "7" : sageCompany = "PH_MFG"; break;
			default : sageCompany = "UK"; break;
		}
		
		return "Ecell_"+sageCompany;
	}
}
