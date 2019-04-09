package server_package;
//ICSD16157 YURIY PYRIH
//DISTRIBUTED SYSTEMS LAB_2


import java.io.Serializable;
import java.util.UUID;

import javax.swing.JOptionPane;

public class Reservation implements Serializable {
	
	private static final long serialVersionUID = -1442798787354930462L;
	private String uniqueID;
	private String str_first_name;
	private String str_last_name;
	private String str_phone_number;
	private String str_arrival_date;
	private String str_room_type;
	private Boolean breakfast;
	
	public Reservation(String str_first_name, String str_last_name, String str_phone_number, String str_arrival_date, String str_room_type, Boolean breakfast) {
		
		this.uniqueID = UUID.randomUUID().toString().substring(0, 7);
		this.str_first_name = str_first_name;
		this.str_last_name = str_last_name;
		this.str_phone_number = str_phone_number;
		this.str_arrival_date = str_arrival_date;
		this.str_room_type = str_room_type;
		this.breakfast = breakfast;
		
		 JOptionPane.showMessageDialog(null,"Reservation added succesfully.");
	}
	
	public String toString() {
		
		String str_prescription = uniqueID + "\t" + str_first_name + "\t" + str_last_name + "\t" + str_phone_number + "\t" + str_arrival_date 
				+ "\t" + str_room_type + "\t" + (breakfast? "Yes" : "No") + '\n';
		return str_prescription;
	}
	
	public String getID() {
		return uniqueID;
	}
	
	public String getFirstName() {
		return str_first_name;
	}
	
	public String getLastName() {
		return str_last_name;
	}
}
