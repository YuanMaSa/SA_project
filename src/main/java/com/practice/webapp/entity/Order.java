
package com.practice.webapp.entity;


public class Order {

	private int order_id;

	private int order_M_id;

	private int cart_id;

	private String O_date;

	private String receiver_name;

	private String receiver_phone;

	private String receiver_address;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_M_id() {
		return order_M_id;
	}

	public void setOrder_M_id(int order_M_id) {
		this.order_M_id = order_M_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public String getO_date() {
		return O_date;
	}

	public void setO_date(String o_date) {
		O_date = o_date;
	}


	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}
	public String getReceiver_phone() {
		return receiver_phone;
	}

	public void setReceiver_phone(String receiver_phone) {
		this.receiver_phone = receiver_phone;
	}
	public String getReceiver_address() {
		return receiver_address;
	}


	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;

	}


}