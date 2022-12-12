package brs;
import java.util.*;

// main class
public class BUSMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int g=1, ch=0; // for menu
		int[] depd; // array to store destination and departure
		PassengerDetails psg;    
		Bus bus;
		BusOperation bo = new BusOperation(); // operation class
		bo.initiate(); // Initialize all buses
		do {

			//display menu
			System.out.println(" --------------------------------");
			System.out.println("|\t1. BOOKING\t\t|\n|_______________________________|\n|\t2. CANCELLATION\t\t|\n|_______________________________|\n|\t3. CHECK STATUS\t\t|\n|_______________________________|\n|\t4. EXIT\t\t\t|\n|_______________________________|\n");
			

			
			try {
				ch=sc.nextInt();
				System.out.println("");
				switch(ch) {
				case 1:

					//choice 1 Booking is selected

					//displays the available locations
					//for comfort of running the code
					System.out.println("          Available Stops");
					System.out.println("--------------------------------------");
					for(int i=0; i< bo.AvailableStops.length; i++) {
						System.out.print(" "+ bo.AvailableStops[i]+" | ");
						if(i%4==3) {
							System.out.println("");
						}
					}
					System.out.println();
					System.out.println("--------------------------------------");


					//Accepting the departure and destination from the user

					System.out.print("  Enter the departure:  ");
					String dep=sc.next();
					System.out.print("  Enter the destination:  ");
					String des=sc.next();

					//Displays the buses available
					depd=bo.search(dep,des);

					//if there are buses available
					if(depd!=null) {
						//Accepting the Bus id out of the displayed choices
						System.out.print("  Enter Bus Id: ");
						int id=sc.nextInt();
						//returns the object of the class Bus corresponding to the id entered
						bus=bo.BusFinder(id);
						bus.ID=id;
						//reinitializing the bus stops for the above bus
						depd=bo.busF(bus, dep, des);
						//booking of the seat
						bo.bookSeat(bus, depd);
					}
					break;

				case 2:
					//Choice 2 Cancelling a booked seat

					System.out.print("  Enter Bus Id: ");
					bus=bo.BusFinder(sc.nextInt());
					sc.nextLine();
					if(bus!=null) {
						System.out.print("  Enter PNR No: ");
						int prn =sc.nextInt();
						sc.nextLine();
						psg=bo.Passengerfinder(bus, prn);
						if(psg!=null) {
							System.out.print("  Comfirm Cancellation? Y/N :  ");
							char cancel=sc.nextLine().charAt(0);
							if(cancel=='Y'||cancel=='y') {
								bo.cancellation(bus,psg);
							}
							else {
								System.out.println("  Cancellation Declined !");
							}

						}else {
							System.out.println("  Pasenger Not Declined !");
						}
					}
					else {
						System.out.println("  Bus Not Found!!");
					}
					bo.Queueseat_Confirmation();
					break;

				case 3:
					System.out.print("  Enter Bus Id: ");
					bus=bo.BusFinder(sc.nextInt());
					sc.nextLine();
					if(bus!=null) {
						System.out.print("  Enter PNR No: ");
						int prn =sc.nextInt();
						if(bo.Passengerfinder(bus, prn)==null) {
							psg=bo.Passenger_in_Queue(bus, prn);
							if(psg!=null) {
								System.out.println("  Passenger in queue");
							}
							else {
								System.out.println("  Passenger not found");
							}
						}
						else {
							System.out.println("  Booking Status: Seat Confirmed");
						}
					}
					bo.Queueseat_Confirmation();
					break;

				case 4:
					g=0;
					break;
				default:
					System.out.println("");
				}

			}
			catch(Exception e)  
			{  
				System.out.println("Error has occured try again"); 
				BUSMain Main = new BUSMain();
				Main.main(args);
				
			}  
			
		}while(g==1);
		sc.close();
	}
}

//main class to perform all the operations of the buses
class BusOperation{
	Scanner sc = new Scanner(System.in);
	int busNo,  n;

	// Available Stops
	String[] AvailableStops = {"Pune", "Nagar", "Aurangabad", "Hadapsar", "Pimpri",
			"Karve", "Swargate", "Satara", "Sangli", "Solapur", "Beed", "Mumbai",
			"Raigarh", "Ratnagiri"};

	void ticket(PassengerDetails psg) {

		System.out.println("\n");
		System.out.println("  ------------ YOUR TICKET ------------");
		System.out.println(" | Name: "+ psg.PassengerName+"\t\t\t\t|");
		System.out.println(" | Bus ID: "+ psg.id+"\t"+"| Bus Name: "+ TheArr[psg.id-1].name+"\t|");
		System.out.println(" | Seat No.: "+(psg.SeatNo)+"\t| Fare: "+psg.fare+"\t\t|");
		System.out.println(" | PNR: "+psg.PNR+"\t\t\t\t|");
		System.out.println("  -------------------------------------");
	}

	int[] search(String dep, String des){
		int departureFound=0, found_flag=0;
		int[] depd=new int[2];
		//Iterating through all busses
		for(int i=0;i<TheArr.length;i++) {
			//Iterating thru stops
			for(int j=0;j<TheArr[i].stops.length;j++) {
				//checking for departure
				if(departureFound==1) {

					if(des.equalsIgnoreCase(TheArr[i].stops[j])) {
						found_flag=1;
						depd[1]=j;
						System.out.println(" -------------------------------");
						System.out.println("  Bus ID: "+TheArr[i].ID);
						System.out.println("  Bus Name: "+TheArr[i].name);
						System.out.println("  Bus Time: "+TheArr[i].time);
						// DisplayBusDetails(TheArr[i]);
						System.out.println(" -------------------------------");
						break;
					}
				}
				else if(dep.equalsIgnoreCase(TheArr[i].stops[j])) {
					departureFound=1;
					depd[0]=j;
				}
			}
			departureFound=0;

		}if(found_flag==0) {
			System.out.println("\n  Bus not available !!!!\n");
			return null;
		}
		return depd;
	}
	//returns the indexs of the stops in stops[] of the bus searched
	int[] busF(Bus bus, String dep, String des) {
		int flg=0;
		int[] depd = new int[2];
		for(int j=0;j<bus.stops.length;j++) {
			if(dep.equalsIgnoreCase(bus.stops[j])) {
				depd[0] = j;
				flg++;
			}
			if(des.equalsIgnoreCase(bus.stops[j])) {
				depd[1] = j;
				flg++;
			}
			if(flg==2) {
				return depd;
			}
		}

		return null;
	}

	//return the bus object 
	Bus BusFinder(int BusId) {
		for (int i=0;i<TheArr.length;i++) {
			if(TheArr[i].ID==BusId) {
				return TheArr[i];
			}
		}
		return null;
	}

	//Finding and displaying the passenger information from the list of confirmed bookings
	PassengerDetails Passengerfinder(Bus bus,int prn) {
		PassengerDetails psg=bus.Psg.search(prn);
		if(psg!=null) {
			System.out.println();
			System.out.print("  Passenger Name :"+psg.PassengerName+"\t\t");
			System.out.println("SeatNo. :"+psg.SeatNo);
			System.out.print("  Departure :"+TheArr[psg.id-1].stops[psg.dep]+"\t\t\t");
			System.out.println("Destination :"+TheArr[psg.id-1].stops[psg.des]);
			System.out.println();
		}
		return psg;
	}

	//Finding and displaying the passenger information from the list of passengers in queue
	PassengerDetails Passenger_in_Queue(Bus bus,int prn){
		PassengerDetails psg=bus.WL.search(prn);
		if(psg!=null) {
			System.out.println("  Passenger Name :"+psg.PassengerName);
			System.out.println("  SeatNo. :"+psg.SeatNo);
			System.out.println("  Departure :"+psg.dep);
			System.out.println("  Destination :"+psg.des);
		}
		return psg;
	} 

	//Displays the seats and availability
	void Show_Seats(Bus bus, PassengerDetails psg) {
		System.out.println("    ------------");
		System.out.print("   | ");
		for(int j=0; j<bus.seats[0].length/2;j++) {
			if(CheckStatus_print(bus,psg,j)) {
				System.out.print((j+1)+" ");
			}
			else {
				System.out.print("B ");
			}
		}
		System.out.println(" |");
		System.out.print("   | ");
		for(int j=bus.seats[0].length/2; j<bus.seats[0].length;j++) {
			if(CheckStatus_print(bus,psg,j)) {
				System.out.print((j+1)+" ");
			}
			else {
				System.out.print("B ");
			}
		}
		System.out.print("|  --> DRIVER\n");
		System.out.println("    ------------");
	}

	void bookSeat(Bus bus,int[] depd) {
		//Accepting passenger details
		PassengerDetails psg=new PassengerDetails();
		psg.id=bus.ID;
		psg.dep=depd[0];
		psg.des=depd[1];
		Show_Seats(bus,psg);
		System.out.print("  Enter number of seats: ");
		int seats=sc.nextInt();
		sc.nextLine();
		//checking availability of seats
		if(SeatCounter(bus,seats,depd)) {
			for(int a=0;a<seats;a++) {
				psg.id=bus.ID;
				psg.dep=depd[0];
				psg.des=depd[1];
				//Acceptng the information from the customer
				System.out.print("  Enter your name: ");
				psg.PassengerName=sc.nextLine();
				//Accepting the seat that they want to select
				System.out.print("  Enter seat number: ");
				psg.SeatNo=sc.nextInt();
				sc.nextLine();
				//displaying the fare
				System.out.println("\n  *Fare: "+fare(bus, psg));
				System.out.println();
				System.out.print("  Do you want to confirm booking?? Y/N : ");
				String chr=sc.nextLine();
				char ch= chr.charAt(0);
				if(ch=='y' || ch=='Y') {
					System.out.println();
					CheckStatus(bus,psg);
					System.out.println("  Booking successful!");
					psg.PNR=depd[0]*1000+bus.ID*100+depd[1]*10+psg.SeatNo;
					bus.Psg.add(psg);
					ticket(psg);
					System.out.println("  * Note your PNR for future reference\n\n");
					psg=new PassengerDetails();
				}
			}
		}
		else {			
			if(bus.seats[0].length<seats) {
				System.out.println("Invalid Number of Seats !!!");
			}
			else {
				System.out.println("  Seats not avalible !!!");
				System.out.println("Do you want to enter the waiting list? Y/N");
				char ch=sc.nextLine().charAt(0);
				if(ch=='y' || ch=='Y') {
					for(int a=0;a<seats;a++) {
						System.out.print("  Enter your name: ");
						psg.PassengerName=sc.nextLine();
						psg.dep=depd[0];
						psg.des=depd[1];
						psg.PNR=(depd[0]+1)*1000+bus.ID*100+depd[1]*10+(bus.WL.length+1);
						System.out.println("your PNR No.: "+psg.PNR);
						bus.WL.enqueue(psg);
					}
				}
			}
		}
	}

	//changing the status of the seat
	void ChangeStatus(Bus bus, PassengerDetails psg) {
		for(int m=psg.dep;m<=psg.des;m++) {
			bus.seats[m][psg.SeatNo - 1]=1;
		}

	}

	//checking if anyone in the queue can confirm their seats
	void Queueseat_Confirmation() {
		int seatflag=0;
		//iterating through buses
		for(int i=0;i<TheArr.length;i++) {
			if(!TheArr[i].WL.isEmpty()) {
				PassengerDetails psg=TheArr[i].WL.front.p;
				int[] depd=new int[] {psg.dep,psg.des};
				while(SeatCounter(TheArr[i],1,depd) && !TheArr[i].WL.isEmpty()) {
					for(int j=0;j<TheArr[i].seats[0].length;j++) {
						for(int m=psg.dep;m<=psg.des;m++) {
							if(TheArr[i].seats[m][j]!=0) {
								seatflag=1;
								break;
							}
						}
						if(seatflag==0) {
							psg.SeatNo=j+1;
							CheckStatus(TheArr[i],psg);
							TheArr[i].Psg.add(psg);
							TheArr[i].WL.dequeue();
							break;
						}
						seatflag=0;
					}

				}
			}
		}
	}

	//counts the total number of free seats
	boolean SeatCounter(Bus bus,int seats,int[] depd) {
		int seatcounter=0,flag=0;
		for(int i=0;i<bus.seats[0].length;i++) {
			flag=0;
			for(int j=depd[0];j<=depd[1];j++) {
				if(bus.seats[j][i]==1){
					flag=1;
					break;
				}
			}
			if(flag==0) {
				seatcounter++;
			}
		}
		if(seatcounter>=seats) {
			return true;
		}
		return false;
	}

	//	changes status of the seat from booked to unbooked
	void cancellation(Bus bus,PassengerDetails psg) {
		for(int m=psg.dep;m<=psg.des;m++) {
			if(bus.seats[m][psg.SeatNo - 1]==1) {
				bus.seats[m][psg.SeatNo - 1]=0;
			}
		}
		bus.Psg.delete(psg);
		System.out.println("Cancellation Successful");
	}

	//	checks if the the is available for booking
	void CheckStatus(Bus bus,PassengerDetails psg) { //Seat booking
		int seatflag=0;
		for(int m=psg.dep;m<=psg.des;m++) {
			if(bus.seats[m][psg.SeatNo - 1]==0) {
				seatflag=1;
			}
			else  {
				seatflag=0;
				System.out.println("  Seat already booked.Please choose another seat.");
				System.out.println("\n  Enter seat number: ");
				psg.SeatNo=sc.nextInt();
				sc.nextLine();
				CheckStatus(bus,psg);
				break;
			}
		}

		if(seatflag==1) {
			ChangeStatus(bus,psg);
			psg.fare=fare(bus,psg);
		}
	}

	//	checks if the the is available for printing
	Boolean CheckStatus_print(Bus bus,PassengerDetails psg, int x) { //Seat booking
		int seatflag=0;
		for(int m=psg.dep;m<=psg.des;m++) {
			if ( bus.seats[m][x]==1) {
				seatflag=1;
			}
		}
		if (seatflag==1) {
			return false;
		}
		return true;


	}

	//	calculates the fare
	int fare(Bus bus, PassengerDetails psg) {
		int fare=0;
		int rate=2, total_dis=0;
		for(int i=(psg.dep+1); i<=psg.des; i++) {
			total_dis += TheArr[bus.ID-1].dist[i];
		}
		fare = total_dis * rate;
		//		psg.fare = fare;
		return fare;
	}

	Bus[] TheArr=new Bus[5];

	void initiate() {

		//Bus 1
		TheArr[0]=new Bus(001,3);
		TheArr[0].name="JABBAR";
		TheArr[0].time="10:30";
		TheArr[0].stops[0]="Pune";
		TheArr[0].dist[0]=0;
		TheArr[0].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[1]="Nagar";
		TheArr[0].dist[1]= 139;
		TheArr[0].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[0].stops[2]="Aurangabad";
		TheArr[0].dist[2]=144;
		TheArr[0].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus 2
		TheArr[1]=new Bus(002,7);
		TheArr[1].name="ROYAL";
		TheArr[1].time="10:30";
		TheArr[1].stops[0]="Pune";
		TheArr[1].dist[0]=0;
		TheArr[1].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[1]="Satara";
		TheArr[1].dist[1]=144;
		TheArr[1].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[2]="Sangli";
		TheArr[1].dist[2]=122;
		TheArr[1].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[3]="Ratnagiri";
		TheArr[1].dist[3]=178;
		TheArr[1].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[4]="Raigarh";
		TheArr[1].dist[4]=228;
		TheArr[1].seats[4]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[5]="Mumbai";
		TheArr[1].dist[5]=103;
		TheArr[1].seats[5]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[1].stops[6]="Pune";
		TheArr[1].dist[6]=151;
		TheArr[1].seats[6]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus 3
		TheArr[2]=new Bus(003,2);
		TheArr[2].name="VOYGER";
		TheArr[2].time="10:30";
		TheArr[2].stops[0]="Pune";
		TheArr[2].dist[0]=0;
		TheArr[2].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[2].stops[1]="Solapur";
		TheArr[2].dist[1]=253;
		TheArr[2].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus 4
		TheArr[3]=new Bus(004,6);
		TheArr[3].name="TEJASWINI";
		TheArr[3].time="10:30";
		TheArr[3].stops[0]="Pune";
		TheArr[3].dist=new int[] {0,114,122,191,182,125};
		TheArr[3].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[1]="Satara";
		TheArr[3].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[2]="Sangli";
		TheArr[3].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[3]="Solapur";
		TheArr[3].seats[3]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[4]="Beed";
		TheArr[3].seats[4]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[3].stops[5]="Aurangabad";
		TheArr[3].seats[5]=new int[]{0,0,0,0,0,0,0,0,0,0};
		//Bus5
		TheArr[4]=new Bus(005,3);
		TheArr[4].name="LIBERTY";
		TheArr[4].time="10:30";
		TheArr[4].stops[0]="Aurangabad";
		TheArr[4].dist=new int[] {0,114,122};
		TheArr[4].seats[0]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[1]="Sangli";
		TheArr[4].seats[1]=new int[]{0,0,0,0,0,0,0,0,0,0};
		TheArr[4].stops[2]="Aurangabad";
		TheArr[4].seats[2]=new int[]{0,0,0,0,0,0,0,0,0,0};
	}

}



//Bus class to store all the data related to a bus
class Bus{ // storing arr class for arr obj
	String name, time;
	int g ,ID;
	int seats[][];
	String stops[];
	int dist[];
	LinkedList Psg=new LinkedList();
	Queue WL=new Queue();
	Bus(int ID,int noofstops){
		this.ID=ID;
		seats=new int[noofstops][10];
		stops=new String[noofstops];
		dist= new int[noofstops];
	}
}

//to store data related to a passenger
class PassengerDetails{ //Class BusBook
	String PassengerName;
	int id,SeatNo,dep ,des, fare,PNR;
}

//node for the linked list and queue
class Node{
	PassengerDetails p;
	Node next;
	Node(PassengerDetails p){
		this.p=p;
		this.next=null;
	}
}


//linked list
class LinkedList{
	Node head;
	LinkedList(){
		head=null;
	}
	void add(PassengerDetails p) {
		Node temp=new Node(p);
		if(head==null) {
			head=temp;
		}
		else {
			Node ptr=head;
			while(ptr.next!=null) {
				ptr=ptr.next;
			}
			ptr.next=temp;

		}
	}

	PassengerDetails search(int prn){
		Node ptr=head;
		while(ptr!=null) {
			if(ptr.p.PNR==prn) {
				return ptr.p;
			}
			ptr=ptr.next;
		}
		return null;
	}
	void display() {
		Node ptr=head;
		while(ptr!=null) {
			System.out.print("PNR"+ptr.p.PNR);
			ptr=ptr.next;
		}
	}
	void delete(PassengerDetails psg) {
		Node ptr=head;
		if(ptr!=null) {
			if(ptr.p.PNR==psg.PNR) {
				head=head.next;
			}
			else {
				while(ptr.next!=null) {
					if(ptr.next.p.PNR==psg.PNR) {
						break;
					}
					ptr=ptr.next;
				}
				ptr.next=ptr.next.next;
			}

		}

	}
}


//Queue
class Queue{
	Node tail=null;
	int length=0;
	Node front=null;
	void enqueue(PassengerDetails p) {
		Node temp=new Node(p);
		if(tail==null) {
			tail=temp;
			length=1;
			front=temp;
		}
		else {
			length++;
			tail.next=temp;
			tail=tail.next;
		}
	}
	void dequeue() {
		if(!isEmpty()) {
			front=front.next;
		}
	}
	boolean isEmpty() {
		if(front==null) {
			return true;
		}
		return false;
	}
	PassengerDetails search(int prn) {
		Node ptr=front;
		while(ptr!=null) {
			if(ptr.p.PNR==prn) {
				return ptr.p;
			}
			ptr=ptr.next;
		}
		return null;
	}
	void display() {
		Node ptr=front;
		while(ptr!=null) {
			System.out.println("Queue"+ptr.p.PNR);
			ptr=ptr.next;
		}
	}
}


//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//1
//
//         Available Stops
//--------------------------------------
//Pune |  Nagar |  Aurangabad |  Hadapsar | 
//Pimpri |  Karve |  Swargate |  Satara | 
//Sangli |  Solapur |  Beed |  Mumbai | 
//Raigarh |  Ratnagiri | 
//--------------------------------------
// Enter the departure:  punr
// Enter the destination:  nagar
//
// Bus not available !!!!
//
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//1
//
//         Available Stops
//--------------------------------------
//Pune |  Nagar |  Aurangabad |  Hadapsar | 
//Pimpri |  Karve |  Swargate |  Satara | 
//Sangli |  Solapur |  Beed |  Mumbai | 
//Raigarh |  Ratnagiri | 
//--------------------------------------
// Enter the departure:  pune
// Enter the destination:  nagar
//-------------------------------
// Bus ID: 1
// Bus Name: JABBAR
// Bus Time: 10:30
//-------------------------------
// Enter Bus Id: 1
//   ------------
//  | 1 2 3 4 5  |
//  | 6 7 8 9 10 |  --> DRIVER
//   ------------
// Enter number of seats: 10
// Enter your name: y
// Enter seat number: 1
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 1	| Fare: 278		|
//| PNR: 111				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 2
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 2	| Fare: 278		|
//| PNR: 112				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 3
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 3	| Fare: 278		|
//| PNR: 113				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 4
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 4	| Fare: 278		|
//| PNR: 114				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 5
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 5	| Fare: 278		|
//| PNR: 115				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 6
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 6	| Fare: 278		|
//| PNR: 116				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 7
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 7	| Fare: 278		|
//| PNR: 117				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 8
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 8	| Fare: 278		|
//| PNR: 118				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 9
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 9	| Fare: 278		|
//| PNR: 119				|
// -------------------------------------
// * Note your PNR for future reference
//
//
// Enter your name: y
// Enter seat number: 10
//
// *Fare: 278
//
// Do you want to confirm booking?? Y/N : y
//
// Booking successful!
//
//
// ------------ YOUR TICKET ------------
//| Name: y				|
//| Bus ID: 1	| Bus Name: JABBAR	|
//| Seat No.: 10	| Fare: 278		|
//| PNR: 120				|
// -------------------------------------
// * Note your PNR for future reference
//
//
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//1
//
//         Available Stops
//--------------------------------------
//Pune |  Nagar |  Aurangabad |  Hadapsar | 
//Pimpri |  Karve |  Swargate |  Satara | 
//Sangli |  Solapur |  Beed |  Mumbai | 
//Raigarh |  Ratnagiri | 
//--------------------------------------
// Enter the departure:  pune 
// Enter the destination:  nagar
//-------------------------------
// Bus ID: 1
// Bus Name: JABBAR
// Bus Time: 10:30
//-------------------------------
// Enter Bus Id: 1
//   ------------
//  | B B B B B  |
//  | B B B B B |  --> DRIVER
//   ------------
// Enter number of seats: 1
// Seats not avalible !!!
//Do you want to enter the waiting list? Y/N
//y
// Enter your name: sayli
//your PNR No.: 1111
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//3
//
// Enter Bus Id: 1
// Enter PNR No: 1111
// Passenger Name :sayli
// SeatNo. :0
// Departure :0
// Destination :1
// Passenger in queue
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//2
//
// Enter Bus Id: 1
// Enter PNR No: 120
//
// Passenger Name :y		SeatNo. :10
// Departure :Pune			Destination :Nagar
//
// Comfirm Cancellation? Y/N :  y
//Cancellation Successful
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
//3
//
// Enter Bus Id: 1
// Enter PNR No: 1111
//
// Passenger Name :sayli		SeatNo. :10
// Departure :Pune			Destination :Nagar
//
// Booking Status: Seat Confirmed
//--------------------------------
//|	1. BOOKING		|
//|_______________________________|
//|	2. CANCELLATION		|
//|_______________________________|
//|	3. CHECK STATUS		|
//|_______________________________|
//|	4. EXIT			|
//|_______________________________|
//
