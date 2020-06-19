package com.example.curso.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	
	private int code;
	
	private OrderStatus(int num) {
		this.code = num;
	}

	public int getCode() {
		return code;
	}

	public static OrderStatus valueOff(int code) {
		for(OrderStatus value : OrderStatus.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
	

}
