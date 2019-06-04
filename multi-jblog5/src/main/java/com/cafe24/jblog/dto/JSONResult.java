package com.cafe24.jblog.dto;

public class JSONResult {
	private String result;
	private String message; // result(통신성공) fail시 set
	private Object data; // result success시 set 
	
	public static JSONResult success(Object data) {
		return new JSONResult("success", null,data);
	}
	
	public static JSONResult fail(String message) {
		return new JSONResult("fail", message, null);
	}
	
	private JSONResult(String result, String message, Object data) {
		this.result = result;
		this.message = message;
		this.data = data;
	}
	
	public String getResult() {
		return result;
	}
	public String getMessage() {
		return message;
	}
	public Object getData() {
		return data;
	}
	
	
	@Override
	public String toString() {
		return "JSONResult [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
}
