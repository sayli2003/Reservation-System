package brs;
import java.util.*;
class Bus{
	String name;
	long ID;
	int seats[][];
	String stops[];
	Bus(long ID,int noofstops){
		this.ID=ID;
		seats=new int[noofstops][10];
		stops=new String[noofstops];
	}
}
class Company{
	Bus[] TheArr=new Bus[5];
	void initiate() {
		
		//Bus 1
		TheArr[0]=new Bus(001,3);		
		TheArr[0].name="JABBAR";
		TheArr[0].stops[0]="Pune";
		TheArr[0].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[1]="Nagar";
		TheArr[0].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[2]="Jalgaon";
		TheArr[0].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		
		//Bus 2
		TheArr[1]=new Bus(002,4);
		TheArr[1].name="KK";
		TheArr[1].stops[0]="Hadapsar";
		TheArr[1].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[1]="Pimpri";
		TheArr[1].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[2]="Karve";
		TheArr[1].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[3]="Swargate";
		TheArr[1].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
		
		//Bus 3
		TheArr[2]=new Bus(003,2);
		TheArr[2].name="VOYGER";
		TheArr[2].stops[0]="Nashik";
		TheArr[2].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[2].stops[1]="Mumbai";
		TheArr[2].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		
		//Bus 4
		TheArr[3]=new Bus(004,6);
		TheArr[3].name="Tejaswini";
		TheArr[3].stops[0]="Pune";
		TheArr[3].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[1]="Satara";
		TheArr[3].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[2]="Sangli";
		TheArr[3].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[3]="Solapur";
		TheArr[3].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[4]="Osmanabad";
		TheArr[3].seats[4]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[5]="Beed";
		TheArr[3].seats[5]=new int[]{0,0,0,0,0,0,0,0,0,0};
		
		//Bus5
		TheArr[4]=new Bus(005,3);
		TheArr[4].name="JABBAR";
		TheArr[4].stops[0]="Mumbai";
		TheArr[4].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[1]="Raigarh";
		TheArr[4].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[2]="Ratnagiri";
		TheArr[4].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
	
//		try {
//			File Buses = new File("C:\\Users\\Sayli\\eclipse-workspace\\BusReservationSystem\\src\\brs\\BusDetail.csv");
//			Scanner sc = new Scanner(Buses);
////			sc.useDelimiter(",");   //sets the delimiter pattern  
//			while (sc.hasNext())  //returns a boolean value  
//			{  
//				System.out.println("here:"+sc.next().toString());  //find and returns the next complete token from this scanner  
//			}   
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  

	}
	void DisplayAllBuses() {
		for(int i=0;i<5;i++) {
			System.out.println("The Bus Name: "+TheArr[i].name);
			for(int j=0;j<TheArr[i].stops.length;j++) {
				System.out.println("Stops: "+TheArr[i].stops[j]);
			}
			System.out.println("_______________________________________");
		}
	}
}
public class BUSMain {
	public static void main(String[] args) {
		Company bs=new Company();
		bs.initiate();
		bs.DisplayAllBuses();
	}
}
