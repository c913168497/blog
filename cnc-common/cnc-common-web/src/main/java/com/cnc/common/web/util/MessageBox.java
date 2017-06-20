package com.cnc.common.web.util;


public class MessageBox {
	
	private MessageType type;
	private String text;
	
	public enum MessageType {
		info, success, warning, error
	}
	
	public static MessageBox getErrorMsg(String msg){
		return new MessageBox(MessageType.error,msg);
	}
	public static MessageBox getSuccessMsg(String msg){
		return new MessageBox(MessageType.success,msg);
	}
	public static MessageBox getWarningMsg(String msg){
		return new MessageBox(MessageType.warning,msg);
	}
	public static MessageBox getInfoMsg(String msg){
		return new MessageBox(MessageType.info,msg);
	}
	
	private MessageBox() {
		super();
	}
	
	private MessageBox(MessageType type, String text) {
		this.type = type;
		this.text = text;
	}
	
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return type + ": " + text;
	}
	
}


