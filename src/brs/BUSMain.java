//package miniproj;
//import java.io.*;
//import java.util.*;
//
//// main class
//public class BUSMain {
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int g=1, ch=0;	// for menu
//		int[] depd; // ?
//
//		BusOperation bo = new BusOperation();	// operation class
//		bo.initiate(); // Initialize all buses
//
//		do {
//			System.out.println(" ----------------------------------------");
//			System.out.println("  1. BOOKING   2. CANCELLATION  3. EXIT");
//			ch=sc.nextInt();
//			System.out.println("");
//
//			switch(ch) {
//			case 1:
//				System.out.println("          Available Stops");
//				System.out.println("--------------------------------------");
//				for(int i=0; i< bo.AvailableStops.length; i++) {
//					System.out.print(" "+ bo.AvailableStops[i]+" | ");
//					if(i%4==3) {
//						System.out.println("");
//					}
//				}
//				System.out.println();
//				System.out.println("--------------------------------------");
//				System.out.print("  Enter the departure:  ");
//				String dep=sc.next();
//				System.out.print("  Enter the destination:  ");
//				String des=sc.next();
//				depd=bo.search(dep,des);
//
//
//				if(depd!=null) {
//					System.out.println("Enter Bus Id: ");
//					Bus bus=bo.BusFinder(sc.nextInt());
//					bo.bookSeat(bus, depd);
//				}
//				break;
//
//			case 2:
//				System.out.println();
//				break;
//
//			case 3:
//				g=0;
//				break;
//
//			default:
//				System.out.println("");
//			}
//		}while(g==1);
//		sc.close();
//	}
//}
//
//class BusOperation{
//	Scanner sc = new Scanner(System.in);
//	int busNo,  n;
//	Bus[] TheArr=new Bus[5];
//	// Available Stops
//	String[] AvailableStops = {"Pune", "Nagar", "Jalgaon", "Hadapsar", "Pimpri",
//			"Karve", "Swargate", "Satara", "Sangli", "Solapur", "Beed", "Mumbai", 
//			"Raigarh", "Ratnagiri"};
//
//	int[] search(String dep, String des){		
//		int departureFound=0, found_flag=0;
//		int[] depd=new int[2];
//		//Iterating through all busses
//		for(int i=0;i<TheArr.length;i++) {
//			//Iterating thru stops
//			for(int j=0;j<TheArr[i].stops.length;j++) {
//				//checking for departure
//				if(departureFound==1) {
//
//					if(des.equalsIgnoreCase(TheArr[i].stops[j])) {
//						found_flag=1;
//						depd[1]=j;
//						System.out.println(" -------------------------------");
//						System.out.println("  Bus ID: "+TheArr[i].ID);
//						System.out.println("  Bus Name: "+TheArr[i].name);
//						System.out.println("  Bus Time: "+TheArr[i].time);
//						//	DisplayBusDetails(TheArr[i]);
//						System.out.println(" -------------------------------");
//						break;
//					}
//				}
//				else if(dep.equalsIgnoreCase(TheArr[i].stops[j])) {
//					departureFound=1;
//					depd[0]=j;
//				}				
//			}
//			departureFound=0;
//
//		}if(found_flag==0) {
//			System.out.println("\n  Bus not available !!!!\n");
//			return null;
//		}
//		return depd;
//	}
//
//	Bus BusFinder(int BusId) {
//		for (int i=0;i<TheArr.length;i++) {
//			if(TheArr[i].ID==BusId) {
//				return TheArr[i];
//			}
//		}
//		return null;
//	}
//
//	void Show_Seats(Bus bus, PassengerDetails psg) {
//		System.out.println("    ------------");
//		System.out.print("   | ");
//		for(int j=0; j<bus.seats[0].length/2;j++) {
//			if(CheckStatus_print(bus,psg,j)) {
//				System.out.print((j+1)+" ");
//			}
//			else {
//				System.out.print("B ");
//			}
//		}
//		System.out.println(" |");
//		System.out.print("   | ");
//		for(int j=bus.seats[0].length/2; j<bus.seats[0].length;j++) {
//			if(CheckStatus_print(bus,psg,j)) {
//				System.out.print((j+1)+" ");
//			}
//			else {
//				System.out.print("B ");
//			}
//		}
//		System.out.print("|  --> DRIVER\n");
//		System.out.println("    ------------");
//	}
//	
//	void bookSeat(Bus bus,int[] depd) {		
//		//Accepting passenger details		
//		PassengerDetails psg=new PassengerDetails();
//		System.out.print("  Enter number of seats: ");
//		int seats=sc.nextInt();
//		sc.nextLine();
//		//		DisplaySeats();
//		//		if(SeatCounter(bus,seats)) {
//		for(int a=0;a<seats;a++) {
//			System.out.print("  Enter your name: ");
//			psg.PassengerName=sc.nextLine();
//			Show_Seats(bus,psg);
//			System.out.println();
//			System.out.print("  Enter seat number: ");
//			psg.SeatNo=sc.nextInt();
//			sc.nextLine();
//			psg.dep=depd[0];
//			psg.des=depd[1];
//			System.out.println("  Do you want to confirm booking?? Y/N");
//			String chr=sc.nextLine();
//			char ch= chr.charAt(0);
//			if(ch=='y' || ch=='Y') {
//				System.out.println();
//				CheckStatus(bus,psg);
//			}
//		}
//		//		}
//		//		else {
//		//			System.out.println("  Seats not avalible !!!");
//		//		}
//
//
//	}
//	
//	void ChangeStatus(Bus bus, PassengerDetails psg) {
//		for(int m=psg.dep;m<=psg.des;m++) {
//			bus.seats[m][psg.SeatNo - 1]=1;
//		}
//
//	}
//
//	void CheckStatus(Bus bus,PassengerDetails psg) {			//Seat booking
//		int seatflag=0;
//		System.out.println("before");
//		for(int i=0; i<bus.stops.length; i++) {
//			for(int j=0; j<bus.seats[i].length;j++) {
//				System.out.print(bus.seats[i][j]+" ");
//			}System.out.println();
//		}
//		for(int m=psg.dep;m<=psg.des;m++) {
//			if(bus.seats[m][psg.SeatNo - 1]==0) {
//				bus.seats[m][psg.SeatNo - 1]=1;
//				seatflag=1;
//			}
//			else if (seatflag!=1) {
//				System.out.println("Seat already booked.Please choose another seat.");
//				System.out.println("Enter seat number: ");
//				psg.SeatNo=sc.nextInt();
//				CheckStatus(bus,psg);
//				break;
//			}
//		}
//		System.out.println("after");
//		for(int i=0; i<bus.stops.length; i++) {
//			for(int j=0; j<bus.seats[i].length;j++) {
//				System.out.print(bus.seats[i][j]+" ");
//			}System.out.println();
//		}
//	}
//
//	void Save_psg_data(PassengerDetails psg) {
//		
//	}
//	
//	Boolean CheckStatus_print(Bus bus,PassengerDetails psg, int x) {			//Seat booking
////		int seatflag=0;
//		for(int m=psg.dep;m<=psg.des;m++) {
//			if ( bus.seats[m][x]==1) {
//				return false;
//			}
//		}
//
//		return true;
//
//	}
//
//	void initiate() {
//
//		//Bus 1
//		TheArr[0]=new Bus(001,3);		
//		TheArr[0].name="JABBAR";
//		TheArr[0].time="10:30";
//		TheArr[0].stops[0]="Pune";
//		TheArr[0].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[0].stops[1]="Nagar";
//		TheArr[0].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[0].stops[2]="Jalgaon";
//		TheArr[0].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		//Bus 2
//		TheArr[1]=new Bus(002,4);
//		TheArr[1].name="KK";
//		TheArr[1].time="10:30";
//		TheArr[1].stops[0]="Hadapsar";
//		TheArr[1].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[1].stops[1]="Pimpri";
//		TheArr[1].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[1].stops[2]="Karve";
//		TheArr[1].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[1].stops[3]="Swargate";
//		TheArr[1].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		//Bus 3
//		TheArr[2]=new Bus(003,2);
//		TheArr[2].name="VOYGER";
//		TheArr[2].time="10:30";
//		TheArr[2].stops[0]="Pune";
//		TheArr[2].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[2].stops[1]="Jalgaon";
//		TheArr[2].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		//Bus 4
//		TheArr[3]=new Bus(004,6);
//		TheArr[3].name="Tejaswini";
//		TheArr[3].time="10:30";
//		TheArr[3].stops[0]="Pune";
//		TheArr[3].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[3].stops[1]="Satara";
//		TheArr[3].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[3].stops[2]="Sangli";
//		TheArr[3].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[3].stops[3]="Solapur";
//		TheArr[3].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[3].stops[4]="Jalgaon";
//		TheArr[3].seats[4]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[3].stops[5]="Beed";
//		TheArr[3].seats[5]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		//Bus5
//		TheArr[4]=new Bus(005,3);
//		TheArr[4].name="JABBAR";
//		TheArr[4].time="10:30";
//		TheArr[4].stops[0]="Mumbai";
//		TheArr[4].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[4].stops[1]="Raigarh";
//		TheArr[4].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
//		TheArr[4].stops[2]="Ratnagiri";
//		TheArr[4].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
//	}
//
//}
//
//
//
//
//class Bus{		// storing arr class for arr obj
//
//	String name, time;
//	long ID;
//	int seats[][];
//	String stops[];
//	int Passengers;
//	PassengerDetails[] Psg;
//	Bus(long ID,int noofstops){
//		this.ID=ID;
//		Psg= new PassengerDetails[10];
//		seats=new int[noofstops][10];
//		stops=new String[noofstops];
//	}
//}
//
//
//class PassengerDetails{									//Class BusBook
//	String PassengerName;
//	int noofseats,id,SeatNo,dep ,des;
//}
