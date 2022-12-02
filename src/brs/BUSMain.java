package brs;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
class Bus{
	String name;
	long ID;
	int seats[][];
	String stops[];
	Bus(long ID,int noofstops){
		this.ID=ID;
		seats=new int[noofstops][20];
		stops=new String[noofstops];
	}
}
class BusOperation{
	void initiate() {
		try {
			File Buses = new File("C:\\Users\\Sayli\\eclipse-workspace\\BusReservationSystem\\src\\brs\\BusDetail.csv");
			Scanner sc = new Scanner(Buses);
//			sc.useDelimiter(",");   //sets the delimiter pattern  
			while (sc.hasNext())  //returns a boolean value  
			{  
				System.out.println("here:"+sc.next().toString());  //find and returns the next complete token from this scanner  
			}   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

	}
}
public class BUSMain {
	public static void main(String[] args) {
		BusOperation bs=new BusOperation();
		bs.initiate();
	}
}
