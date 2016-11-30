package com.practice.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.practice.webapp.entity.Comment;
public class CommentDAODB implements CommentDAO {
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public void insert(Comment comment) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Comment (comment_M_id,comment_p_id,c_comment,c_create_date) VALUES(?, ?, ?, ? , ? , ? , ? , ?)";	
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, comment.getComment_M_id());
			smt.setInt(2, comment.getComment_p_id());
			smt.setString(3, comment.getC_comment());
			smt.setString(4, comment.getC_create_date());
			
			smt.executeUpdate();			
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
	}
	public void delete(long id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Comment  WHERE comment_M_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, id);
			smt.executeUpdate();			
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
	}


	public void update( Comment comment,long id) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Comment SET comment_M_id=?, comment_p_id=?, c_comment=?, c_create_date=?,c_update_date=? "
				+ "WHERE comment_M_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setString(1, comment.getC_comment());
			smt.setString(2, comment.getC_create_date());
			smt.setString(3, comment.getC_update_date());
			smt.setLong(9,id);
			smt.executeUpdate();			
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public List<Comment> getC_commentList(String sql) {
		// TODO Auto-generated method stub
		List<Comment> CommentList = new ArrayList<Comment>();
		try {
			conn = dataSource.getConnection();
			
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
				Comment comment = new Comment();
				comment.setComment_M_id(rs.getInt("comment_M_id"));
				comment.setComment_p_id(rs.getInt("comment_p_id"));
				comment.setC_comment(rs.getString("c_comment"));
				comment.setC_create_date(rs.getString("c_create_date"));
				comment.setC_update_date(rs.getString("c_update_date"));
				CommentList.add(comment);
			}
			rs.close();
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return CommentList;
		
	}
	@Override
	public Comment get(Comment comment) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM Comment WHERE comment_M_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, comment.getComment_M_id());
			rs = smt.executeQuery();
			if(rs.next()){
				
				comment.setC_comment(rs.getString("c_comment"));
				comment.setC_create_date(rs.getString("c_create_date"));
			}
			rs.close();
			smt.close();
 
		} catch (SQLException e) {
			throw new RuntimeException(e);
 
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return comment;
		
	}
}