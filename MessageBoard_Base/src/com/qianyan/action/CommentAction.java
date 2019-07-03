package com.qianyan.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qianyan.dao.CommentDao;
import com.qianyan.model.Comment;
import com.qianyan.util.DbUtil;


public class CommentAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private DbUtil dbUtil=new DbUtil();
	private CommentDao commentDao = new CommentDao();
	
	public String execute() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			String messageId = request.getParameter("id");
			HttpSession session = request.getSession();
			session.setAttribute("id", messageId);
			String content = request.getParameter("content");
			String userIP = request.getRemoteAddr();
			Comment comment = new Comment(Integer.parseInt(messageId), content,
					userIP);
			commentDao.commentAdd(con, comment);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
}
