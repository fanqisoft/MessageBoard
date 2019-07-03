package com.qianyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qianyan.model.Message;
import com.qianyan.model.PageBean;
import com.qianyan.util.StringUtil;

public class MessageDao {

	public List<Message> queryMyMessages(Connection con, String username)
			throws Exception {
		List<Message> messagesList = new ArrayList<Message>();
		String sql = "select * from t_message where userName='" + username + "'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			Message message = new Message();
			message.setId(rs.getString("id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setDate(rs.getString("date"));
			message.setUserName(rs.getString("userName"));
			messagesList.add(message);
		}
		return messagesList;
	}
	

	 public Message queryMessageInfoById(Connection con, String id) throws Exception {
		String sql = "select * from t_message where id ='" + id + "'";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		Message message = new Message();
		while (rs.next()) {
			message.setId(rs.getString("id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setDate(rs.getString("date"));
			message.setUserName(rs.getString("userName"));
		}
		return message;
	}

	public int update(Connection con, String title, String content, String date, String id) throws Exception{
		String sql = "update t_message set title=?, content=?,date=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, date);
		pstmt.setString(4, id);
		return pstmt.executeUpdate();
	}

	public int delete(Connection con, String id) throws Exception{
		String sql = "delete from t_message where id = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, id);
		return pstmt.executeUpdate();
	}

	public int addMessage(Connection con, Message message) throws Exception{
		String sql = "insert into t_message values(null,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, message.getTitle());
		pstmt.setString(2, message.getContent());
		pstmt.setString(3, message.getDate());
		pstmt.setString(4, message.getUserName());
		return pstmt.executeUpdate();
	}

	public List<Message> messageList(Connection con) throws Exception{
		List<Message> messagesList = new ArrayList<Message>();
		String sql = "select * from t_message";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while (rs.next()) {
			Message message = new Message();
			message.setId(rs.getString("id"));
			message.setTitle(rs.getString("title"));
			message.setContent(rs.getString("content"));
			message.setDate(rs.getString("date"));
			message.setUserName(rs.getString("userName"));
			messagesList.add(message);
		}
		return messagesList;
	}
	public List<Message> messagesList(Connection con,Message message,PageBean pageBean, String userName)throws Exception{
		List<Message> messagesList=new ArrayList<Message>();
		StringBuffer sb=new StringBuffer("select * from t_message");
		if(StringUtil.isNotEmpty(message.getTitle())){
			sb.append(" where title like '%"+message.getTitle()+"%'");
		}
		if(StringUtil.isNotEmpty(userName)){
			sb.append(" and userName = '"+userName+"' ");
		}
		sb.append(" order by date desc ");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Message messages=new Message();
			messages.setId(rs.getString("id"));
			messages.setTitle(rs.getString("title"));
			messages.setContent(rs.getString("content"));
			messages.setDate(rs.getString("date"));
			messages.setUserName(rs.getString("userName"));
			messagesList.add(messages);
		}
		return messagesList;
	}
	
	public int messagesCount(Connection con,Message message, String userName)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_message");
		if(StringUtil.isNotEmpty(message.getTitle())){
			sb.append(" and title like '%"+message.getTitle()+"%'");
		}
		if(StringUtil.isNotEmpty(userName)){
			sb.append(" and userName = '"+userName+"' ");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
}
