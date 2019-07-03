package com.qianyan.action;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.qianyan.dao.UserDao;
import com.qianyan.model.Message;
import com.qianyan.model.User;
import com.qianyan.util.DbUtil;
import com.qianyan.util.ResponseUtil;

import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	private List<Message> messages;
	private String imageCode;
	private String error;
	private String userName;
	private String password;
	private String trueName;
	private String phone;
	private String email;
	private User user;
	
	private DbUtil dbUtil=new DbUtil();
	private UserDao userDao=new UserDao();

	public String execute() throws Exception {
		HttpSession session=request.getSession();
		Connection con=null;
		try{
			String sRand = (String) session.getAttribute("sRand");
			con = dbUtil.getCon();
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			User currentUser = userDao.login(con, user);
			if (!sRand.equals(imageCode)) {
				error = "验证码错误！";
				return ERROR;
			} else if (currentUser == null) {
				error = "用户名或者密码错误！";
				return ERROR;
			} else {
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String addUser() throws Exception {
		Connection con=null;
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setTrueName(trueName);
		user.setPhone(phone);
		user.setEmail(email);
		try{
			con=dbUtil.getCon();
			int result=userDao.addUser(con, user);
			if (result == 1) {
				return "success";
			}else{
				error = "注册失败！";
				return "error";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String logout() throws Exception {
		request.getSession().invalidate();
		return SUCCESS;
	}
	
	public String panduan() {
		Connection con = null;
		try {
			con = dbUtil.getCon();
			boolean exist = userDao.existUserWithUserName(con, userName);
			JSONObject result = new JSONObject();
			if (exist) {
				result.put("exist", true);
			} else {
				result.put("exist", false);
			}
			ResponseUtil.write(result, ServletActionContext.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public HttpServletRequest getServletRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
