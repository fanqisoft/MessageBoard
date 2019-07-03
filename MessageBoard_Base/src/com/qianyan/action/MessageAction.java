package com.qianyan.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qianyan.dao.CommentDao;
import com.qianyan.dao.MessageDao;
import com.qianyan.model.Comment;
import com.qianyan.model.Message;
import com.qianyan.model.PageBean;
import com.qianyan.util.DbUtil;
import com.qianyan.util.NavUtil;
import com.qianyan.util.PageUtil;
import com.qianyan.util.PropertiesUtil;
import com.qianyan.util.StringUtil;

public class MessageAction extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private Message message;
	private List<Message> messagesList;
	private String id;
	private String userName;
	
	private DbUtil dbUtil = new DbUtil();
	private MessageDao messageDao = new MessageDao();
	private CommentDao commentDao = new CommentDao();

	//显示留言
	public String execute() throws Exception {
		String s_title = request.getParameter("s_title");
		String page = request.getParameter("page");
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("s_title", s_title);
		} else {
			s_title = (String) session.getAttribute("s_title");
		}
		Connection con = null;
		Message message = new Message();
		if (StringUtil.isNotEmpty(s_title)) {
			message.setTitle(s_title);
		}
		try {
			con = dbUtil.getCon();
			int total = messageDao.messagesCount(con, message, userName);
			String pageCode = PageUtil.getPagation(request.getContextPath() + "/main.action", total,
					Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			PageBean pageBean = new PageBean(Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			messagesList = messageDao.messagesList(con, message, pageBean, userName);
			request.setAttribute("pageCode", pageCode);
			request.setAttribute("messagesList", messagesList);
			request.setAttribute("navCode", NavUtil.genMessageManageNavigation("留言显示", "所有留言"));
			request.setAttribute("mainPage", "message/messageList.jsp");
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
	
	//根据id显示留言
	public String pass() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (id == null || id.equals("")) {
				HttpSession session = request.getSession();
				String newId = (String) session.getAttribute("id");
				id = newId;
				message = messageDao.queryMessageInfoById(con, id);
			} else {
				message = messageDao.queryMessageInfoById(con, id);
			}
			Comment s_comment = new Comment();
			s_comment.setMessageId(Integer.parseInt(id));
			List<Comment> commentList = commentDao.commentList(con, s_comment);
			request.setAttribute("commentList", commentList);
			request.setAttribute("navCode",
					NavUtil.genMessageManageNavigation("留言显示", "单个留言"));
			request.setAttribute("message", message);
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

	//更新留言预加载
	public String preSave() throws Exception {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(id)) {
				message = messageDao.queryMessageInfoById(con, id);
				return SUCCESS;
			}
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

	// 留言更新
	public String update() throws Exception {
		Connection con = null;
		String userName = request.getParameter("userName");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime = new java.util.Date();// 得到当前系统时间
		String date = formatter.format(currentTime); // 将日期时间格式化
		try {
			con = dbUtil.getCon();
			if (StringUtil.isNotEmpty(id)) {
				messageDao.update(con, title, content, date, id);
			} else {
				Message message = new Message(title, content, date, userName);
				messageDao.addMessage(con, message);
			}
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
	
	//留言删除
	public String delete() throws Exception {
		System.out.println("编号：" + id);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			messageDao.delete(con, id);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			dbUtil.closeCon(con);
		}
		return SUCCESS;
	}
	
	//添加留言
	public String add() throws Exception {
		HttpServletRequest req = (HttpServletRequest) request;
		// 获取session
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("userName");
		String title = message.getTitle();
		System.out.println(title);
		String content = message.getContent();
		System.out.println(content);
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date currentTime = new java.util.Date();// 得到当前系统时间
		String date = formatter.format(currentTime); // 将日期时间格式化
		Message message = new Message();
		message.setContent(content);
		message.setDate(date);
		message.setTitle(title);
		message.setUserName(username);
		Connection con = null;
		try {
			con = dbUtil.getCon();
			messageDao.addMessage(con, message);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.closeCon(con);
		}
		return SUCCESS;
	}

	public List<Message> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
