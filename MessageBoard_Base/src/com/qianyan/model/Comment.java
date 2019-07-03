package com.qianyan.model;

import java.util.Date;

public class Comment {

	private int commentId;
	private int messageId=-1;
	private String content;
	private String userIP;
	private Date commentDate;
	
	public Comment(int messageId, String content, String userIP) {
		super();
		this.messageId = messageId;
		this.content = content;
		this.userIP = userIP;
	}
	public Comment() {
		super();
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserIP() {
		return userIP;
	}
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	
}
