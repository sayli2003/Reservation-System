package brs;
import java.util.Scanner;
//here is the trial update
//this is updated on github
class Bus{

	String name;
	long ID;
	String time;
	int seats[][];
	String stops[];
	int Passengers;
	PassengerDetails[] Psg;
	Bus(long ID,int noofstops){
		this.ID=ID;
		Psg= new PassengerDetails[10];
		seats=new int[noofstops][10];
		stops=new String[noofstops];
	}
}


class PassengerDetails{									//Class BusBook
	String PassengerName;
	int noofseats,id,SeatNo,dep ,des;
}



class BusOperation{							//Class BusOperation
	Scanner sc = new Scanner(System.in);

	int busNo,  n;
	Bus[] TheArr=new Bus[5];

	void initiate() {

		//Bus 1
		TheArr[0]=new Bus(001,3);		
		TheArr[0].name="JABBAR";
		TheArr[0].time="10:30";
		TheArr[0].stops[0]="Pune";
		TheArr[0].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[1]="Nagar";
		TheArr[0].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[2]="Jalgaon";
		TheArr[0].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus 2
		TheArr[1]=new Bus(002,4);
		TheArr[1].name="KK";
		TheArr[1].time="10:30";
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
		TheArr[2].time="10:30";
		TheArr[2].stops[0]="Pune";
		TheArr[2].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[2].stops[1]="Jalgaon";
		TheArr[2].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus 4
		TheArr[3]=new Bus(004,6);
		TheArr[3].name="Tejaswini";
		TheArr[3].time="10:30";
		TheArr[3].stops[0]="Pune";
		TheArr[3].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[1]="Satara";
		TheArr[3].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[2]="Sangli";
		TheArr[3].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[3]="Solapur";
		TheArr[3].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[4]="Jalgaon";
		TheArr[3].seats[4]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[5]="Beed";
		TheArr[3].seats[5]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus5
		TheArr[4]=new Bus(005,3);
		TheArr[4].name="JABBAR";
		TheArr[4].time="10:30";
		TheArr[4].stops[0]="Mumbai";
		TheArr[4].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[1]="Raigarh";
		TheArr[4].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[2]="Ratnagiri";
		TheArr[4].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
	}
	void DisplayAllBuses() {
		for(int i=0;i<5;i++) {
			System.out.println("Bus No: "+(i+1));
			System.out.println("The Bus Name: "+TheArr[i].name);
			for(int j=0;j<TheArr[i].stops.length;j++) {
				System.out.println("Stops: "+TheArr[i].stops[j]);
			}
			System.out.println("_______________________________________");
		}
	}


	int[] search(String dep,String des) {						//Searches for the available buses
		int departureFound=0, found_flag=0;
		int m=0, id=0;
		int[] depd=new int[2];
		//Iterating through all busses
		for(int i=0;i<TheArr.length;i++) {
			//Iterating tjru stops
			for(int j=0;j<TheArr[i].stops.length;j++) {
				//checking for departure
				if(departureFound==1) {

					if(des.equalsIgnoreCase(TheArr[i].stops[j])) {
						found_flag=1;
						depd[1]=j;
						System.out.println("-------------------------------");
						System.out.println("Bus ID: "+TheArr[i].ID);
						System.out.println("Bus Name: "+TheArr[i].name);
						System.out.println("Bus Time: "+TheArr[i].time);
						//						DisplayBusDetails(TheArr[i]);
						System.out.println("-------------------------------");
						break;
					}
				}
				else if(dep.equalsIgnoreCase(TheArr[i].stops[j])) {
					departureFound=1;
					depd[0]=j;
					
				}
				
			}

		}if(found_flag==0) {
			System.out.println("Bus not available");
			return null;
		}
		return depd;


	}
	//	void DisplayBusDetails(Bus bus) {
	//		System.out.println("-------------------------------");
	//		System.out.println("Bus ID: "+bus.ID);
	//		System.out.println("Bus Name: "+bus.name);
	//		System.out.println("Bus Time: "+bus.time);
	//		
	//	}

	void bookSeat(Bus bus,int[] depd) {		
		//Accepting passenger details		
		PassengerDetails psg=new PassengerDetails();
		System.out.println("Enter number of seats:");
		int seats=sc.nextInt();
		DisplaySeats();
		if(SeatCounter(bus,seats)) {
			for(int a=0;a<seats;a++) {
				System.out.println("Enter your name:");
				psg.PassengerName=sc.nextLine();
				System.out.println("Enter seat number: ");
				psg.SeatNo=sc.nextInt();
				psg.dep=depd[0];
				psg.des=depd[1];
				System.out.println("Do you want to confirm booking?? Y/N");
				char ch=sc.nextLine().charAt(0);
				if(ch=='y' || ch=='Y') {
					ChangeStatus(bus,psg);
				}
			}
		}
		else {
			System.out.println("Seats not avalible");
		}


	}
		void ChangeStatus(Bus bus,PassengerDetails psg) {			//Seat booking
			int seatflag=0;
			for(int m=psg.dep;m<psg.dep;m++) {
				if(bus.seats[m][psg.SeatNo]==0) {
					bus.seats[m][psg.SeatNo]=1;
					seatflag=1;
				}
				else {
					System.out.println("Seat already booked.Please choose another seat.");
					System.out.println("Enter seat number: ");
					psg.SeatNo=sc.nextInt();
					ChangeStatus(bus,psg);
	//				break;
				}
		}
		}
		boolean SeatCounter(Bus bus,int seats) {				//Counting empty seats
			int trueforall=1;
			int counter=0;
			System.out.println("Inside Seat Counter");
			for(int j=0;j<bus.seats[0].length;j++) {
				for(int k=0;k<bus.stops.length;k++) {
				if(bus.seats[k][j]!=0) {
					trueforall=0;
				}
				}
				if(trueforall==1) {
					counter++;
				}
			}
			if(counter>=seats) {
				return true;
			}
			return false;
		}
	//	void display(int noofseats, int id) {
	//		for(int i=0; i<1; i++) {
	//			System.out.println("Bus Name: "+TheArr[i].name);
	//			System.out.println("Passenger ID: "+ id);
	//			System.out.println("Passenger name: "+bus[i].PassengerName);
	//			System.out.println("Depature: "+bus[i].dp);
	//			System.out.println("Destination: "+bus[i].d);
	//			System.out.println("Bus type: "+bus[i].bustype);
	//			System.out.println("No of seats: "+bus[i].noofseats);
	//
	//		}
	//
	//	}
	//	void waiting() {
	//		int g=0, MaxSize, rear, front;
	//		System.out.println("Do you want to be in waiting list.");
	//		g = sc.nextInt();
	//		if(g==1) {
	//
	//		}
	//
	//	}
	Bus BusFinder(int BusId) {
		int BusfoundFlg=0;
		int cancelseat;
		for (int i=0;i<TheArr.length;i++) {
			if(TheArr[i].ID==BusId) {
				return TheArr[i];
			}
		}
		return null;
	}
	//	
	//	void cancellation() {
	//		String cancelname;
	//		int BusId,BusfoundFlg=0;
	//		int cancelseat;
	//		System.out.println("Enter Bus Id");
	//		BusId=sc.nextInt();
	//		for (int i=0;i<TheArr.length;i++) {
	//			if(TheArr[i].ID==BusId) {
	//				BusfoundFlg=1;
	//				break;
	//			}
	//		}
	//		if(BusfoundFlg==0) {
	//			System.out.println("Bus NOT found");
	//		}
	//		
	//		System.out.print("Enter your name: ");
	//		cancelname = sc.next();
	//		System.out.print("Enter your Seat Number: ");
	//		cancelseat = sc.nextInt();
	//		
	//	}
	//}
	// testing clone repo
}


public class BUSMain{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int g = 0, ch = 0;
		int[] depd;
		BusOperation bo = new BusOperation();
		bo.initiate();
		do {
		System.out.println("Enter the departure:");
		String dep=sc.next();
		System.out.println("Enter the destination:");
		String des=sc.next();
		depd=bo.search(dep,des);
		if(depd!=null) {
			System.out.println("1.Booking\n2.Cancellation.");
			ch= sc.nextInt();
			switch(ch){
			case 1: 
				System.out.println("Enter Bus Id: ");
				Bus bus=bo.BusFinder(sc.nextInt());
				
						break;
				//			case 2: bo.cancellation();
				//			break;
			}
		}
			
			System.out.println("Do you want to continue? if yes press 1 else press 0");
			g=sc.nextInt();
		}while(g==1);
	}
}
