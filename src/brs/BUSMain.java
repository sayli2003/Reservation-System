package brs;
import java.util.Scanner;

//here is the trial update
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

class BusBook{
	String PassengerName, d, dp, bustype;
	int noofseats;
}

class BusOperation{
	Scanner sc = new Scanner(System.in);
	
	int busNo,  n;
	Bus[] TheArr=new Bus[5];
	BusBook bus[] = new BusBook[50];
	
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
	
	void passengerinput() {
		int flag =0;
		for(int i=0; i<1; i++) {
			bus[i] = new BusBook();
		System.out.println("Enter Passenger name: ");
		bus[i].PassengerName = sc.next();

		System.out.println("Enter departure: ");
		bus[i].dp = sc.next();
		System.out.println("Enter destination: ");
		bus[i].d = sc.next();
		System.out.println("Enter bus type: ");
		bus[i].bustype = sc.next();
		System.out.println("Enter no of seats you want to book: ");
		bus[i].noofseats = sc.nextInt();
		
		search(bus[i].d, bus[i].noofseats);
		
		

		}
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
	



	void search(String d, int noofseats) {
		int flag1=0, flag2=0, count=0;
		int m=0, id=0;
		
	
		for(int i=0; i<1 ; i++) {
			for(int j =0; j<3; j++) {

				if(d.equalsIgnoreCase(TheArr[i].stops[j])) {
				
					System.out.println("Bus is available.");
					
					while(flag1==0) {

						for(int k=0; k<10; k++) {
							if(TheArr[i].seats[j][k]==0){
								count++;
							}

						}
						if(count>noofseats) {
							
							while(m!=noofseats) {
								System.out.println("Enter seat no : ");
								n = sc.nextInt();
								if(TheArr[i].seats[j][n]==0) {
									TheArr[i].seats[j][n]=1;
									System.out.println("Booked seat no"+n);
								
									m++;
									flag1=1;
									
								}
								else {
									System.out.println("Seat already Booked. Choose another seat.");
									flag1=0;

								}
								
							}
							id++;
							System.out.println("Your ID is:"+id);
							display(noofseats, id);
						}
						else if(count<noofseats) {
							System.out.println("get added in waiting list");
							waiting();
						}


					}

				}
			}	
		}
		if(flag1==0) {
			System.out.println("Bus is not available.");
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


public class BUSMain{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int g = 0, ch = 0;

		BusOperation bo = new BusOperation();
		bo.initiate();
		bo.DisplayAllBuses();
		bo.passengerinput();
		bo.passengerinput();
		bo.passengerinput();
		bo.passengerinput();
		do {
			System.out.println("1.Booking\n2.Cancellation.");
			ch= sc.nextInt();
			switch(ch){
			case 1: bo.initiate();
			bo.DisplayAllBuses();
			bo.passengerinput();
			break;

			case 2: bo.cancellation();
			break;
			}
			System.out.println("Do you want to continue? if yes press 1 else press 0");
			g=sc.nextInt();
		}while(g==1);
	}

}

