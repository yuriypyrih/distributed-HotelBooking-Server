package server_package;
//ICSD16157 YURIY PYRIH
//DISTRIBUTED SYSTEMS LAB_2


import java.io.Serializable;

public class MessageObject implements Serializable{
	private static final long serialVersionUID = -1442798787354930462L;
	private REQUEST_TYPE REQUEST;
	private String str_message;
	private Reservation reservation;
	
	public MessageObject(REQUEST_TYPE REQUEST) {
		this.REQUEST = REQUEST;
	}
	
	public MessageObject(REQUEST_TYPE REQUEST, String str_message) {
		this.REQUEST = REQUEST;
		this.str_message = str_message;
	}
	
	public MessageObject(REQUEST_TYPE REQUEST, Reservation reservation) {
		this.REQUEST = REQUEST;
		this.reservation = reservation;
	}
	
	public REQUEST_TYPE getREQUEST(){
		return REQUEST;
	}
	
	public String getMessage() {
		return str_message;
	}
	
	public Reservation getObject() {
		return reservation;
	}
	
}
