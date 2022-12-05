package BUS;
import java.util.Scanner;
//here is the trial update
//this is updated on github
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
class BusBook{									//Class BusBook
	String PassengerName, d, dp, bustype;
	int noofseats,id,SeatNo;
}
class BusOperation{							//Class BusOperation
	Scanner sc = new Scanner(System.in);

	int busNo,  n;
	Bus[] TheArr=new Bus[5];
	BusBook bus[] = new BusBook[10];

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


	void search() {						//Searches for the available buses
		int flag=0, found_flag=0;
		int m=0, id=0;



		System.out.println("Enter the departure:");
		String dep=sc.next();
		System.out.println("Enter the destination:");
		String des=sc.next();
		for(int i=0;i<TheArr.length;i++) {
			for(int j=0;j<TheArr[i].stops.length;j++) {
				if(dep.equalsIgnoreCase(TheArr[i].stops[j])) {
					flag=1;

				}


				if(flag==1) {
					for(int k =j;k<TheArr[i].stops.length; k++) {
						if(des.equalsIgnoreCase(TheArr[i].stops[k])) {
							System.out.println("found: bus num: "+ TheArr[i].ID);
							bookSeat(i, j, k);
						}
					}
					found_flag=1;
					break;

				}


			}

		}if(found_flag==0) {
			System.out.println("Bus not available");

		}



	}


	void bookSeat(int i, int j, int k) {				//Accepting passenger details		
		System.out.println("Enter number of seats:");
		int seats=sc.nextInt();
		if(SeatCounter(i,seats)) {
			for(int a=0;a<seats;a++) {
			bus[a]=new BusBook();
			System.out.println("Enter your name:");
			bus[a].PassengerName=sc.next();
			System.out.println("Enter bus type(AC/Non AC):");
			bus[a].bustype=sc.next();
			Book(i,j,k,a);
		}
		}
			else {
				System.out.println("Seats not avalible");
			}
//			while(Seatflag==0) {
//				System.out.println("Enter seat no.:");
//				bus[a].SeatNo=sc.nextInt();
//				for(int m=j;m<k;m++) {
//					if(TheArr[i].seats[m][bus[a].SeatNo]==0) {
//						TheArr[i].seats[m][bus[a].SeatNo]=1;
//						Seatflag=1;
//						
//					}
//					else {
//						System.out.println("Seat already booked.Please choose another seat.");
//						//bookSeat( i,  j,  k);
//						break;
//					}
//					
//				}
//			}
//		}
//		
//		if(Seatflag==1) {
//			System.out.println("Seat Booked.");
//			
//		}
//		else {
//			System.out.println("Seat not booked.");
//		}
		
	}
	void Book(int i, int j, int k,int a) {			//Seat booking
		int seatflag=0;
	
		System.out.println("Enter seat no.:");
		bus[a].SeatNo=sc.nextInt();
		for(int m=j;m<k;m++) {
			if(TheArr[i].seats[m][bus[a].SeatNo]==0) {
				TheArr[i].seats[m][bus[a].SeatNo]=1;
				seatflag=1;
				
			}
			else {
				System.out.println("Seat already booked.Please choose another seat.");
				Book( i,  j,  k,a);
//				break;
			}
	}
	}
	boolean SeatCounter(int i,int seats) {				//Counting empty seats
		int trueforall=1;
		int counter=0;
		System.out.println("Inside Seat Counter");
		for(int j=0;j<TheArr[i].seats[0].length;j++) {
			for(int k=0;k<TheArr[i].stops.length;k++) {
			if(TheArr[i].seats[k][j]!=0) {
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
	void display(int noofseats, int id) {
		for(int i=0; i<1; i++) {
			System.out.println("Bus Name: "+TheArr[i].name);
			System.out.println("Passenger ID: "+ id);
			System.out.println("Passenger name: "+bus[i].PassengerName);
			System.out.println("Depature: "+bus[i].dp);
			System.out.println("Destination: "+bus[i].d);
			System.out.println("Bus type: "+bus[i].bustype);
			System.out.println("No of seats: "+bus[i].noofseats);

		}

	}
	void waiting() {
		int g=0, MaxSize, rear, front;
		System.out.println("Do you want to be in waiting list.");
		g = sc.nextInt();
		if(g==1) {

		}

	}
	void cancellation() {

	}
}
// testing clone repo
public class Main1{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int g = 0, ch = 0;
		BusOperation bo = new BusOperation();
		bo.initiate();
		bo.DisplayAllBuses();
		bo.search();
		bo.search();
	//	bo.search();
		do {
			System.out.println("1.Booking\n2.Cancellation.");
			ch= sc.nextInt();
			switch(ch){
			case 1: bo.initiate();
			bo.DisplayAllBuses();

			break;
			case 2: bo.cancellation();
			break;
			}
			System.out.println("Do you want to continue? if yes press 1 else press 0");
			g=sc.nextInt();
		}while(g==1);
	}
}
