package com.practice.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.sql.DataSource;


import com.practice.webapp.entity.Product;

public class ProductDAODB implements ProductDAO {
	private DataSource dataSource;
	private Connection conn = null ;
	private ResultSet rs = null ;
	private PreparedStatement smt = null ;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public List<Product> getList(){
		String sql = "SELECT * FROM Product";
		return getList(sql);
	}
	
	
	public List<Product> getList(String sql) {
		
		List<Product> ProductList = new ArrayList<Product>();
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			rs = smt.executeQuery();
			while(rs.next()){
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_category(rs.getInt("p_category"));
				product.setClick_count(rs.getInt("p_click_count"));
				product.setP_describe(rs.getString("p_describe"));
				product.setP_image(rs.getString("p_image"));
				product.setP_inventory(rs.getInt("p_inventory"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_onsale_date(rs.getString("p_onsale_date"));
				product.setP_remove_date(rs.getString("p_remove_date"));
				product.setP_update_date(rs.getString("p_update_date"));
				ProductList.add(product);
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
		return ProductList;
	}
	public void insert(Product product) {

		// remove first parameter when Id is auto-increment
	    String sql = "INSERT INTO Product ( p_category,p_click_count, p_describe,p_image,p_inventory,p_name,p_price,p_onsale_date) VALUES( ?, ?, ? , ? , ? , ? , ? , ? )";	
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, product.getP_category());
			smt.setInt(2, product.getClick_count());
			smt.setString(3, product.getP_describe());
			smt.setString(4, product.getP_image());
			smt.setInt(5, product.getP_inventory());
			smt.setString(6, product.getP_name());
			smt.setInt(7, product.getP_price());
			smt.setString(8, product.getP_onsale_date());
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
	public Product get(int id) {
		List<Product> ProductList = new ArrayList<Product>();
		Product product = new Product();
		String sql = "SELECT * FROM Product WHERE p_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setLong(1, ProductList.get(id).getP_id());
			rs = smt.executeQuery();
			if(rs.next()){
				product.setP_id(rs.getInt("p_id"));
				product.setP_category(rs.getInt("p_category"));
				product.setClick_count(rs.getInt("p_click_count"));
				product.setP_describe(rs.getString("p_describe"));
				product.setP_image(rs.getString("p_image"));
				product.setP_inventory(rs.getInt("p_inventory"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_onsale_date(rs.getString("p_onsale_date"));
				product.setP_remove_date(rs.getString("p_remove_date"));
				product.setP_update_date(rs.getString("p_update_date"));
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
		return product;
	}

public void update(Product product,int id) {
		List<Product> ProductList = new ArrayList<Product>();
		String sql = "UPDATE Product SET click_count=?, p_describe=?,p_image=?, p_inventory=?,p_name=?,p_price=?,p_update_date=? "
				+ "WHERE p_id = ?";
		try {
			conn = dataSource.getConnection();
			smt = conn.prepareStatement(sql);
			smt.setInt(1, product.getClick_count());
			smt.setString(2, product.getP_describe());
			smt.setString(3, product.getP_image());
			smt.setInt(4, product.getP_inventory());
			smt.setString(5, product.getP_name());
			smt.setInt(6, product.getP_price());
			smt.setInt(8, ProductList.get(id).getP_id());
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
public void delete(int id) {
	List<Product> ProductList = new ArrayList<Product>();
	String sql = "DELETE FROM Product WHERE p_id = ?";
	try {
		conn = dataSource.getConnection();
		smt = conn.prepareStatement(sql);
		smt.setLong(1, ProductList.get(id).getP_id());
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

}
		  