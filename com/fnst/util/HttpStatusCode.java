package com.fnst.paper.util;

public enum HttpStatusCode {
	 CONTINUE("100"), SWITCHINGPROTOCOLS("101"), PROCESSING("102"),
	 OK("200"), CREATED("201"), ACCEPTED("202"),
	 UNAUTHORIZED("401"), FORBIDDEN("403"), NOTFOUND("404"),
	 INTERALSERVERERROR("500");
	 
	 private String code;
     private HttpStatusCode(String code){
    	 this.code = code;
     }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
     
}
