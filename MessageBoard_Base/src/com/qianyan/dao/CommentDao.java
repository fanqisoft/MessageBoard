package com.qianyan.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.qianyan.model.Comment;
import com.qianyan.util.DateUtil;

public class CommentDao {

	public List<Comment> commentList(Connection con,Comment s_comment)throws Exception{
		List<Comment> commentList=new ArrayList<Comment>();
		StringBuffer sb=new StringBuffer("select * from t_comment t1,t_message t2 where t1.messageId=t2.id");
		if(s_comment.getMessageId()!=-1){
			sb.append(" and t1.messageId="+s_comment.getMessageId());
		}
		sb.append(" order by t1.commentDate desc ");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			Comment comment=new Comment();
			comment.setCommentId(rs.getInt("commentId"));
			comment.setMessageId(rs.getInt("messageId"));
			comment.setContent(rs.getString("content"));
			comment.setUserIP(rs.getString("userIP"));
			comment.setCommentDate(DateUtil.formatString(rs.getString("commentDate"), "yyyy-MM-dd HH:mm:ss"));
			commentList.add(comment);
		}
		return commentList;
	}
	
	public int commentAdd(Connection con,Comment comment)throws Exception{
		String sql="insert into t_comment values(null,?,?,?,now())";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, comment.getMessageId());
		pstmt.setString(2, comment.getContent());
		pstmt.setString(3, comment.getUserIP());
		return pstmt.executeUpdate();
	}
}
