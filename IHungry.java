import java.util.*;
/**
 * Represents a burger order in the iHungry ordering system.
 * This class encapsulates all order-related information including
 * customer details, quantity, and order status.
 * 
 * @author PasinduOG
 * @author wasudasandaruvan (Collaborative Development)
 * @version 2.1
 * @since 2025-09-18
 */
class Burger {
	/** Fixed price per burger in LKR */
	public static final double BURGER_PRICE=500;
	/** Order status: Cancelled */
	public static final int CANCEL=0;
	/** Order status: Being prepared */
	public static final int PREPARING=1;
	/** Order status: Delivered to customer */
	public static final int DELIVERED=2;
	
    /** Unique identifier for the order (format: B0001, B0002, etc.) */
    private String orderId;
    /** Customer's phone number (10 digits starting with 0) */
    private String customerId;
    /** Customer's full name */
    private String customerName;
    /** Number of burgers ordered */
    private int orderQty;
    /** Current status of the order (CANCEL, PREPARING, DELIVERED) */
    private int orderStatus;
    
    /**
     * Default constructor for Burger class.
     * Creates an empty burger order object.
     */
    public Burger(){}

    /**
     * Parameterized constructor for Burger class.
     * Creates a burger order with specified details.
     * 
     * @param orderId Unique order identifier (e.g., "B0001")
     * @param customerId Customer's phone number (10 digits)
     * @param customerName Customer's full name
     * @param orderQty Number of burgers ordered (must be positive)
     * @param orderStatus Order status (0=Cancel, 1=Preparing, 2=Delivered)
     */
    public Burger(String orderId, String customerId, String customerName, int orderQty, int orderStatus){
        this.orderId=orderId;
        this.customerId=customerId;
        this.customerName=customerName;
        this.orderQty=orderQty;
        this.orderStatus=orderStatus;
    }

    public String getOrderId(){
        return orderId;
    }
    public String getCustomerId(){
        return customerId;
    }
    public String getCustomerName(){
        return customerName;
    }
    public int getOrderQty(){
        return orderQty;
    }
    public int getOrderStatus(){
        return orderStatus;
    } 
    public void setOrderQty(int orderQty){
		this.orderQty=orderQty;
	}
    public void setOrderStatus(int orderStatus){
		this.orderStatus=orderStatus;
	}
}

class IHungry{
	public static Burger[] burger=new Burger[4];

	public static String getOrderId(){
		int orderCount=burger.length+1;
		return orderCount<10 ? "B000"+orderCount :
				orderCount<100 ? "B00"+orderCount :
						orderCount<1000 ? "B0"+orderCount : "B"+orderCount;
	}

	public static String getName(String name){
		for(int i=0; i<burger.length; i++){
			if(burger[i].getCustomerId().equalsIgnoreCase(name)){
				return burger[i].getCustomerName();
			}
		}
		return "";
	}

	public static String getStatusNameById(int index){
		return index==Burger.CANCEL ? "Cancelled" :
				index==Burger.PREPARING ? "Preparing" :
						index==Burger.DELIVERED ? "Delivered" : "";
	}

	public static boolean isValidCustomerId(String customerId){
		if(customerId.length()!=10){
			return false;
		}
		if(customerId.charAt(0)!='0'){
			return false;
		}
		for(int i=0; i<customerId.length(); i++){
			if(customerId.charAt(i)<'0' || customerId.charAt(i)>'9'){
				return false;
			}
		}
		return true;
	}

	public static boolean isValidOrderId(String orderId) {
		if (orderId.length() != 5) {
			return false;
		}
		if (orderId.charAt(0) != 'B' && orderId.charAt(0) != 'b') {
			return false;
		}
		for (int i = 1; i < orderId.length(); i++) {
			if (orderId.charAt(i) < '0' || orderId.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	public static boolean search(Burger[] br,String customerId){
		for(int i=0; i<br.length; i++){
			if(br[i].getCustomerId().equalsIgnoreCase(customerId)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean indexOf(String key){
		for(int i=0; i<burger.length; i++){
			if(key.equalsIgnoreCase(burger[i].getCustomerId())){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isDeliveredOrderId(String orderId){
		for(int i=0; i<burger.length; i++){
			if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
				if(burger[i].getOrderStatus()==Burger.DELIVERED){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isCancelledOrderId(String orderId){
		for(int i=0; i<burger.length; i++){
			if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
				if(burger[i].getOrderStatus()==Burger.CANCEL){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean isOrderStatusId(int newOrderId){
		if(newOrderId==Burger.PREPARING || newOrderId==Burger.DELIVERED || newOrderId==Burger.CANCEL){
			return true;
		}
		return false;
	}

	public static void placeOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-----------------------------------------------------------");
			System.out.println("|                       Place Order                       |");
			System.out.println("-----------------------------------------------------------\n\n");

			String newOrderId=getOrderId();
			System.out.printf("ORDER ID - %s\n", newOrderId);
			System.out.println("================\n\n\n");

			System.out.print("Enter Customer ID (phone no.) : ");
			String customerId=input.next();
			while(!isValidCustomerId(customerId)){
				System.out.println("\tInvalid phone number... Try again...!\n");
				System.out.print("Enter Customer ID (phone no.) : ");
				customerId=input.next();
			}
			String index=getName(customerId);
			String customerName="";
			if(index.isEmpty()){
				System.out.print("Enter Customer Name           : ");
				customerName=input.next();
			}else{
				System.out.printf("Customer Name                 : %s\n",index);
			}

			System.out.print("Enter Burger Quantity         - ");
			int qty=input.nextInt();

			double total=(double)Burger.BURGER_PRICE*qty;
			System.out.printf("Total Value                   - %.2f\n",total);

			L2:do{
				System.out.print("\tAre you confirm order - ");
				String confirm=input.next().toLowerCase();
				if(confirm.equals("y")){
					extendBurgerArray();

					if(index.isEmpty()){
						burger[burger.length-1]=new Burger(newOrderId,customerId,customerName,qty,Burger.PREPARING);
					}else{
						burger[burger.length-1]=new Burger(newOrderId,customerId,index,qty,Burger.PREPARING);
					}

					System.out.println("\n\tYour order is entered to the system successfully...");
					L3:do{
						System.out.print("\nDo you want to place another order (Y/N): ");
						String addNewOrder=input.next().toLowerCase();
						if(addNewOrder.equals("y")){
							continue L1;
						}else if(addNewOrder.equals("n")){
							break L1;
						}else{
							System.out.println("\tWrong option\n");
							continue L3;
						}
					}while(true);
				}else if(confirm.equals("n")){
					L4:do{
						System.out.print("\nDo you want to retry: ");
						String addNewOrder=input.next().toLowerCase();
						if(addNewOrder.equals("y")){
							continue L1;
						}else if(addNewOrder.equals("n")){
							break L1;
						}else{
							System.out.println("\tWrong option\n");
							continue L4;
						}
					}while(true);
				}else{
					System.out.println("\t\tWrong option\n");
					continue L2;
				}
			}while(true);
		}while(true);
	}

	public static void searchBestCustomer(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-------------------------------------------------------------");
			System.out.println("|                       BEST Customer                       |");
			System.out.println("-------------------------------------------------------------\n\n");

			System.out.println("--------------------------------------");
			System.out.printf("%-12s %-15s %7s\n","CustomerID","Name","Total");
			System.out.println("--------------------------------------");

			
			Burger[] bestCustomerArr=new Burger[0];
			for(int i=0; i<burger.length; i++){
				if(!search(bestCustomerArr,burger[i].getCustomerId())){
					Burger[] tempBr=new Burger[bestCustomerArr.length+1];
					for(int j=0; j<bestCustomerArr.length; j++){
						tempBr[j]=bestCustomerArr[j];
					}
					bestCustomerArr=tempBr;
					bestCustomerArr[bestCustomerArr.length-1]=burger[i];
				}
			}
			
			double[] totals=new double[bestCustomerArr.length];
			for(int i=0; i<bestCustomerArr.length; i++){
				double total=0;
				for(int j=0; j<burger.length; j++){
					if(burger[j].getCustomerId().equalsIgnoreCase(bestCustomerArr[i].getCustomerId())){
						total+=burger[j].getOrderQty()*Burger.BURGER_PRICE;
					}
				}
				totals[i]=total;
			}
			
			for(int i=0; i<totals.length-1; i++){
				for(int j=0; j<totals.length-1; j++){
					if(totals[j]<totals[j+1]){
						double tempTotal=totals[j];
						totals[j]=totals[j+1];
						totals[j+1]=tempTotal;

						Burger temp=bestCustomerArr[j];
						bestCustomerArr[j]=bestCustomerArr[j+1];
						bestCustomerArr[j+1]=temp;
					}
				}
			}

			for(int i=0; i<bestCustomerArr.length; i++){
				System.out.printf("%-12s %-15s %8.2f\n",bestCustomerArr[i].getCustomerId(),bestCustomerArr[i].getCustomerName(),totals[i]);
				System.out.println("--------------------------------------");
			}

			L2:do{
				System.out.print("\n\tDo you want to go back to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\t\tWrong option\n");
					continue L2;
				}
			}while(true);
		}while(true);
	}

	public static void searchOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|                       SEARCH ORDER DETAILS                       |");
			System.out.println("--------------------------------------------------------------------\n");

			System.out.print("Enter order Id - ");
			String orderId=input.next();
			while(!isValidOrderId(orderId)){
				System.out.println("\tInvalid Order ID...Please try again...\n");
				System.out.print("Enter order Id - ");
				orderId=input.next();
			}

			boolean haveDetails=false;
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.println("\n-----------------------------------------------------------------------------");
					System.out.printf("%-10s %-12s %-15s %-10s %-12s %-12s|\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue", "Status");
					System.out.println("-----------------------------------------------------------------------------");
					System.out.printf("%-10s %-12s %-15s %-10d %-12.2f %-12s|\n",burger[i].getOrderId(), burger[i].getCustomerId(), 
									burger[i].getCustomerName(), burger[i].getOrderQty(), burger[i].getOrderQty()*Burger.BURGER_PRICE, getStatusNameById(burger[i].getOrderStatus()));
					System.out.println("-----------------------------------------------------------------------------");
					haveDetails=true;
				}
			}
			if(!haveDetails){
				L2:do{
					System.out.print("\n\n\nInvalid Order ID. Do you want to enter again? (Y/N)> ");
					String retry=input.next().toLowerCase();
					if(retry.equals("y")){
						continue L1;
					}else if(retry.equals("n")){
						break L1;
					}else{
						System.out.print("Wrong option");
						continue L2;
					}
				}while(true);
			}
			L3:do{
				System.out.print("\nDo you want to search another order details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void searchCustomer(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("|                       SEARCH CUSTOMER DETAILS                       |");
			System.out.println("-----------------------------------------------------------------------\n\n");
			
			System.out.print("Enter customer Id - ");
			String customerId=input.next();
			while(!isValidCustomerId(customerId)){
				System.out.println("\tInvalid phone number... Try again...!\n");
				System.out.print("Enter Customer Id (phone no.) : ");
				customerId=input.next();
			}
			
			if(!indexOf(customerId)){
				System.out.println("\n\n\tThis customer ID is not added yet....");
			}else{
				System.out.printf("\n\n\nCustomerID - %s\n",customerId);
				System.out.printf("Name       - %s\n",getName(customerId));
				
				System.out.println("\nCustomer Order details");
				System.out.println("========================\n");
				
				System.out.println("-----------------------------------------");
				System.out.printf(" %-10s %8s %13s \n","Order_ID","Order_Quantity","Total_Value");
				System.out.println("-----------------------------------------");
				for(int i=0; i<burger.length; i++){
					if(customerId.equalsIgnoreCase(burger[i].getCustomerId())){
						System.out.printf(" %-10s %8d %19.2f\n",burger[i].getOrderId(),burger[i].getOrderQty(),burger[i].getOrderQty()*Burger.BURGER_PRICE);
						System.out.println("-----------------------------------------");
					}
				}
			}
			L3:do{
				System.out.print("\nDo you want to search another customer details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void viewOrders(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("------------------------------------------------------------------");
			System.out.println("|                       VIEW ORDER DETAILS                       |");
			System.out.println("------------------------------------------------------------------\n");
			
			System.out.println("[1] Delivered Order");
			System.out.println("[2] Preparing Order");
			System.out.println("[3] Cancel Order");
			System.out.println("[4] Exit to Main Menu");
			
			System.out.print("\nEnter an option to continue > ");
			int option=input.nextInt();
			
			switch(option){
				case 1 : 
					deliveredOrder();
					break;
				case 2 :
					preparingOrder();
					break;
				case 3 :
					cancelOrder();
					break;
				case 4 :
					break L1;
				default :
					System.out.println("\tInvalid option...");
					System.out.print("Do you want to try again? (Y/N) : ");
					String retry=input.next().toUpperCase();
					if(retry.equals("Y")){
						continue L1;
					}else{
						break L1;
					}
			}
		}while(true);
	}
	
	public static void deliveredOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       DELIVERED ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.DELIVERED){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(), burger[i].getCustomerId(), burger[i].getCustomerName(),
					burger[i].getOrderQty(), burger[i].getOrderQty()*Burger.BURGER_PRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void preparingOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       PREPARING ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.PREPARING){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(),burger[i].getCustomerId(),
					burger[i].getCustomerName(),burger[i].getOrderQty(),burger[i].getOrderQty()*Burger.BURGER_PRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void cancelOrder(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("---------------------------------------------------------------");
			System.out.println("|                       CANCELLED ORDER                       |");
			System.out.println("---------------------------------------------------------------\n\n");
			
			System.out.println("-------------------------------------------------------------------");
			System.out.printf(" %-10s %-16s %-13s %8s %13s |\n","OrderID", "CustomerID", "Name", "Quantity", "OrderValue");
			System.out.println("-------------------------------------------------------------------");
			for(int i=0; i<burger.length; i++){
				if(burger[i].getOrderStatus()==Burger.CANCEL){
					System.out.printf(" %-10s %-16s %-13s %5d %16.2f |\n",burger[i].getOrderId(), burger[i].getCustomerId(), 
					burger[i].getCustomerName(), burger[i].getOrderQty(), burger[i].getOrderQty()*Burger.BURGER_PRICE);
					System.out.println("-------------------------------------------------------------------");
				}
			}
			L2:do{
				System.out.print("\nDo you want to go to main menu? (Y/N)> ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					break L1;
				}else if(retry.equals("n")){
					continue L1;
				}else{
					System.out.println("\n\tWrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateOrderDetails(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("|                       UPDATE ORDER DETAILS                       |");
			System.out.println("--------------------------------------------------------------------\n\n");
			
			System.out.print("Enter order Id - ");
			String orderId=input.next();
			while(!isValidOrderId(orderId)){
				System.out.println("\tInvalid Order ID...Please try again...\n");
				System.out.print("Enter order Id - ");
				orderId=input.next();
			}
			
			if(isDeliveredOrderId(orderId)){
				System.out.println("\nThe Order is already delivered...You can not update this order...");
			}else if(isCancelledOrderId(orderId)){
				System.out.println("\nThe Order is already cancelled...You can not update this order...");
			}else{
				for(int i=0; i<burger.length; i++){
					if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
						System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
						System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
						System.out.printf("Name          - %s\n",burger[i].getCustomerName());
						System.out.printf("Quantity      - %d\n",burger[i].getOrderQty());
						System.out.printf("OrderValue    - %.2f\n",burger[i].getOrderQty()*Burger.BURGER_PRICE);
						System.out.printf("OrderStatus   - %s\n",getStatusNameById(burger[i].getOrderStatus()));
					}
				}
				
				L2:do{
					System.out.println("\nWhat do you want to update ?");
					System.out.println("\t(01) Quantity");
					System.out.println("\t(02) Status\n");
					
					System.out.print("Enter your option - ");
					int option=input.nextInt();
				
					switch(option){
						case 1 :
							updateQuantity(orderId);
							break;
						case 2 :
							updateStatus(orderId);
							break;
						default :
							System.out.println("\tWrong option...\n");
							continue L2;
				}
				}while(true);
			}
			
			L3:do{
				System.out.print("\n\nDo you want to update another order details (Y/N)>: ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					continue L1;
				}else if(retry.equals("n")){
					break L1;
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateQuantity(String orderId){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("Quantity Update");
			System.out.println("================");
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
					System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
					System.out.printf("Name          - %s\n\n",burger[i].getCustomerName());
					
					L2:while(true){
						System.out.print("Enter your quantity update value - ");
						int qty=input.nextInt();
						if(qty>0){
							burger[i].setOrderQty(qty);
							break L2;
						}else{
							System.out.println("\tInvalid quantity input...Please try again...\n");
							continue L2;
						}
					}
					
					System.out.println("\n\tUpdate order quantity successfully...\n");
					
					System.out.printf("New order quantity - %d\n",burger[i].getOrderQty());
					System.out.printf("New order value - %.2f\n\n",burger[i].getOrderQty()*Burger.BURGER_PRICE);
				}
			}
			L3:do{
				System.out.print("Do you want to update another order details (Y/N): ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					updateOrderDetails();
				}else if(retry.equals("n")){
					mainMenu();
				}else{
					System.out.print("Wrong option");
					continue L3;
				}
			}while(true);
		}while(true);
	}
	
	public static void updateStatus(String orderId){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("Status Update");
			System.out.println("================");
			for(int i=0; i<burger.length; i++){
				if(orderId.equalsIgnoreCase(burger[i].getOrderId())){
					System.out.printf("\nOrderID       - %s\n",burger[i].getOrderId());
					System.out.printf("CustomerID    - %s\n",burger[i].getCustomerId());
					System.out.printf("Name          - %s\n\n",burger[i].getCustomerName());
					
					System.out.println("\t(0)Cancel");
					System.out.println("\t(1)Preparing");
					System.out.println("\t(2)Delivered");
					
					System.out.print("\nEnter new order status - ");
					int newOrderStatus=input.nextInt();
					while(!isOrderStatusId(newOrderStatus)){
						System.out.println("\tInvalid status input...Please try again...");
						System.out.print("\nEnter new order status - ");
						newOrderStatus=input.nextInt();
					}
					burger[i].setOrderStatus(newOrderStatus);
					
					System.out.println("\n\tUpdate order quantity successfully...\n");
					
					System.out.printf("New order status - %s\n",getStatusNameById(burger[i].getOrderStatus()));
				}
			}
			L2:do{
				System.out.print("\n\nDo you want to update another order details (Y/N): ");
				String retry=input.next().toLowerCase();
				if(retry.equals("y")){
					updateOrderDetails();
				}else if(retry.equals("n")){
					mainMenu();
				}else{
					System.out.print("Wrong option");
					continue L2;
				}
			}while(true);
		}while(true);
	}

	public static void mainMenu(){
		Scanner input=new Scanner(System.in);
		L1:do{
			clearConsole();
			System.out.println("--------------------------------------------------------------");
			System.out.println("|                       iHungry Burger                       |");
			System.out.println("--------------------------------------------------------------\n\n");
			System.out.println("[1] Place Order		[2] Search Best Customer");
			System.out.println("[3] Search Order	[4] Search Customer");
			System.out.println("[5] View Orders		[6] Update Order Details");
			System.out.println("[7] Exit\n\n");

			System.out.print("Enter an option to continue > ");
			int option=input.nextInt();

			switch(option){
				case 1 :
					placeOrder();
					break;
				case 2 :
					searchBestCustomer();
					break;
				case 3 :
					searchOrder();
					break;
				case 4 :
					searchCustomer();
					break;
				case 5 :
					viewOrders();
					break;
				case 6 :
					updateOrderDetails();
					break;
				case 7 :
					System.out.println("\n\tTHANK YOU FOR USING iHUNGRY BURGER");
					System.out.println("\t\tHAVE A NICE DAY!!!");
					break L1;
				default :
					System.out.println("\tInvalid option...");
					System.out.print("Do you want to try again? (Y/N) : ");
					String retry=input.next().toUpperCase();
					if(retry.equals("Y")){
						continue L1;
					}else{
						break L1;
					}
			}
		}while(true);
	}

	public static void main(String[] args){
		mainMenu();
	}
	
	public static void extendBurgerArray(){
		Burger[] temp=new Burger[burger.length+1];
		for(int i=0; i<burger.length; i++) {
			temp[i]=burger[i];
		}
		burger=temp;
	}

	public static void clearConsole(){
		try{
			final String os=System.getProperty("os.name");
			if(os.contains("Windows")){
				new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			}else{
				System.out.print("\033[H\033[2J");
				System.out.flush();
			}
		}catch(final Exception e){
			e.printStackTrace();
		}
	}
}
