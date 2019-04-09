package server_package;
//ICSD16157 YURIY PYRIH
//DISTRIBUTED SYSTEMS LAB_2


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JOptionPane;


public class ServerManager {

	private static LinkedList<Reservation> reservation_list = new LinkedList<Reservation>();
	private ObjectInputStream instream;
	private ObjectOutputStream outstream;
	
	public ServerManager(ObjectInputStream instream,ObjectOutputStream outstream) {
		this.instream = instream;
		this.outstream = outstream;
	}
	
	
	public void addObject(Reservation object) throws IOException {
		
		System.out.println(object + "Reservation has been added");
		this.reservation_list.add(object);
		outstream.writeObject(new MessageObject(REQUEST_TYPE.RESPOND, "Reservation succesfully added\nReservation"
				+ "s ID: " + object.getID()));
		System.out.println("Server -> OK, WAITING");
		
	}

	public void removeObject(Reservation object) {
		
		System.out.println(object + " Reservation has been removed");
	this.reservation_list.remove(object);		
	}
	
	public void removeObject(String id) throws IOException {
		
		
		for( Reservation reservation : reservation_list) {
			if(reservation.getID().equals(id)) {
				this.reservation_list.remove(reservation);	
				System.out.println(id + " Reservation has been removed");
				outstream.writeObject(new MessageObject(REQUEST_TYPE.RESPOND, "Reservation removed"));
				return;
			}
		}	
		outstream.writeObject(new MessageObject(REQUEST_TYPE.RESPOND, "Not found a Reservation with such ID"));
		
	}
	
	
	
	public String getAllList() {
		StringBuilder str_list = new StringBuilder("ID\tName\tSurname\tPhone\tDate\tRoom Type\tBreakfast Included\n");
		for( Reservation reservation : reservation_list) {
			str_list.append(reservation.toString());
		}
		System.out.println("\"VIEW_ALL\"");
		return str_list.toString();
	}
	
	public String getByNameList(String name) {
		StringBuilder str_list = new StringBuilder("ID\tName\tSurname\tPhone\tDate\tRoom Type\tBreakfast Included\n");
		for( Reservation reservation : reservation_list) {
			
			if(reservation.getFirstName().equals(name) || reservation.getLastName().equals(name)) {
				str_list.append(reservation.toString());
			}
			
		}
		System.out.println("\"" + name + "\"");
		return str_list.toString();
	}

}
