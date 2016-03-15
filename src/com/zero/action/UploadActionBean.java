package com.zero.action;

import java.io.*;

import net.sourceforge.stripes.action.ActionBean;  
import net.sourceforge.stripes.action.ActionBeanContext;  
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;  
import net.sourceforge.stripes.action.Resolution;

public class UploadActionBean implements ActionBean{
	
	private static final String VIEW = "/WEB-INF/jsp/upload.jsp";

	private ActionBeanContext ctxt;
	
	public ActionBeanContext getContext() { return ctxt; }
	public void setContext(ActionBeanContext ctxt) { this.ctxt = ctxt; }
	
	private FileBean file;
	private String actionMsg;
	
	@DefaultHandler
	public Resolution defaultUpload() {
		return new ForwardResolution(VIEW);
	}
	
	public Resolution uploadFile(){

		try{
			processFile(file);
		}catch(IOException ioe)
		{
			this.actionMsg = ioe.getMessage();
		}
	    //file.save(new File("/Users/m.due/jspFileUploads"));

	    return new ForwardResolution(VIEW);
	}
	
	public void processFile(FileBean file) throws IOException{
		file.getFileName();
	    file.getSize();
	    file.getContentType();
	}
	
	public FileBean getFile(){return this.file;}
	public void setFile(FileBean file){	this.file = file;}
	
	public String getActionMsg(){return this.actionMsg;}
	public void setActionMsg(String acmsg){	this.actionMsg = acmsg;}
}
