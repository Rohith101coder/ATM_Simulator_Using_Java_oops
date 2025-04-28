//AN ATM SIMULATOR PROJECT USING OOPS
import java.util.Scanner;
import java.util.Calendar;

//  MAKING OUR ATM PIN PRIVATE SO THAT UNAUTHORIZED USERS CAN'T USE IT DIRECTLY
class Pin{
    private int pin;
    public void setpin(int pin){
        this.pin=pin;
    }
    public int getpin(){
        return this.pin;
}}

//ATM HAS FUNCTIONALITIES SO THAT USER CAN ONLY SEE THE EXTERNAL WORKING BUT NOT INTERNAL WORKING
 interface InnerATM_Simulator {
    void deposit();
    void withdraw();
    void check_balance();
    
}

// IMPLEMENTING THE INTERFACE METHODS
class ATM_Funtionalities extends User_Interface implements InnerATM_Simulator {                 //this class is handles overall working of this project
   static int account_balance=0;       
    static int count=0;
    int pin_number;
    Pin p1 = ATM_Simulator.p;                                              // get the shared PIN object

    public  void deposit(){
        System.out.println();
        System.out.print("Enter The Amount You Want To Deposit:");
       
        int depo=sc.nextInt();
        if(depo>0){
            account_balance=account_balance+depo;
        System.out.println("------------------------------");
        System.out.println("|Amount Deposited Succesfull|");
        System.out.println("------------------------------");
        System.out.println();
            Transaction.deposit_slip(depo);                                    //request the deposited slip
        }else{
        System.out.println();
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("X     !!Invalid Ammount. Please Enter Valid Amount!!    X");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println();
        deposit();                                                              //try again if user do mistake  
    }
    }

    public  void withdraw(){

        System.out.println();
        System.out.print("Enter Your PIN:");     
        System.out.println();              //user need to enter his pin before withdraw
        int pin_number=sc.nextInt();
        if(pin_number!=p1.getpin()){
            System.out.println("xxxxxxxxxxxxxxxxx");
            System.out.println("X Incorrect PIN X");
            System.out.println("xxxxxxxxxxxxxxxxx");
            count++;
            if(count==3){     
                System.out.println();  
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");                                            //have only 3 chance to enter correct pin
                System.out.println("X     You Have Entered 3 Times Incorrect PIN. Your Card Has Been Terminated For 24hrs     X");
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                System.out.println();
                System.exit(0);
            }else{
           withdraw();
        }}
        int draw;
        
       do{
       
        System.out.print("Enter The Amount You Want To Withdraw:");
        draw=sc.nextInt();                                                              //here we are using do while loop in do while loop to check the
                                                                                        //balance in case insufficient balance 
                                                                                        //and also draw ammount should be a valid one
        if(draw>account_balance){
            System.out.println();
            System.out.println("----------------------");
            System.out.println("|Insufficient Balance|");
            System.out.println("----------------------");
            System.out.println();
          
            System.out.println("Please Check Your Balance Before Withdraw");
            String check_balance;
            do{
            System.out.print("Do You Want To Check Your Balance?(y/n):");
           check_balance=sc.next();
            if(check_balance.equalsIgnoreCase("y")){
                check_balance();
            }else if(check_balance.equalsIgnoreCase("n")){
                Further_transaction.further_transaction();
            }else{
                System.out.println();
                System.out.println("Typo Mistake!! Try Again!!");
            }}while(!check_balance.equalsIgnoreCase("y")&&!check_balance.equalsIgnoreCase("n"));
           
        }else if(draw<0){
        System.out.println("Enter Valid Amount. ");
        }}while (draw<0); 
        account_balance=account_balance-draw;

        System.out.println("---------------------------");
        System.out.println("|Amount Withdraw Succesfull|");
        System.out.println("---------------------------");
        System.out.println();
       Transaction.withdraw_slip(draw);              // requesting withdrwan slip
    }

    public  void check_balance(){                                  // to cheack balance
        System.out.println();
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("|    Your Current Balance is:"+account_balance+  "|");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println();
        Further_transaction.further_transaction();
    }
}

//SLIP GENERATION
class Transaction extends ATM_Funtionalities{                       // to generate slip
       static Calendar date = Calendar.getInstance();
       static int hour = date.get(Calendar.HOUR);                   //to get current time and current date
       static int min=date.get(Calendar.MINUTE);
       static int sec=date.get(Calendar.SECOND);
       static int year=date.get(Calendar.YEAR);
       static int month=date.get(Calendar.MONTH)+1;
       static int day=date.get(Calendar.DAY_OF_MONTH);
    public static void deposit_slip(int b){                           //methods gives the deposit slip
        System.out.print("Do You Need Deposited Slip?(y/n):");
        String ans=sc.next();
        if(ans.equalsIgnoreCase("Y"))
       {
        System.out.println("Loading..");
        try
        {
        Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        } 
        System.out.println("                  Your Slip");
        System.out.println(" -----------------------------------------");                  //virtual slip
        System.out.println("|                ROHITH BANK              |");
        System.out.println("|   Date:"+day+"/"+month+"/"+year+"             "+"Time:"+hour+":"+min+":"+sec+"|");
        System.out.println("|      Account No:xxxxxxxxxxxxx           |");
        System.out.println("|Current Balance:"+ATM_Funtionalities.account_balance+"   "+" Deposited Ammount:"+b+"|");
        System.out.println("|          Thank you                      |");
        System.out.println(" -----------------------------------------");
        Further_transaction.further_transaction();                         //continue the process after the slip generation
    }
    else if(ans.equalsIgnoreCase("N"))
    {
        Further_transaction.further_transaction();
    }
    else
    {
        System.out.println("Typo Mistake!! Try Again!!");
        deposit_slip( b);                                                       //user can try again if he do mistake
    }
}

public static void withdraw_slip(int d)
{                                       //methods gives the withdraw slip
    System.out.println();
    System.out.print("Do You Need Withdrawn Slip?(y/n):");
    String ans=sc.next();
    if(ans.equalsIgnoreCase("Y"))
   {
    System.out.println("Loading..");
        try
        {
        Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        } 
    System.out.println("                  Your Slip");
    System.out.println(" -----------------------------------------");
    System.out.println("|                ROHITH BANK              |");
    System.out.println("|   Date:"+day+"/"+month+"/"+year+"             "+"Time:"+hour+":"+min+":"+sec+"|");
    System.out.println("|      Account No:xxxxxxxxxxxxx           |");
    System.out.println("|Current Balance:"+ATM_Funtionalities.account_balance+"   "+" withdrawn Ammount:"+d+"|");
    System.out.println("|          Thank you                      |");
    System.out.println(" -----------------------------------------");
    Further_transaction.further_transaction();                //continue the process after the slip generation
}
    else if(ans.equalsIgnoreCase("N"))
    {
    Further_transaction.further_transaction();           //continue the process 
    }
    else
    {
    System.out.println("Typo Mistake!! Try Again!!");
    withdraw_slip(d);                   //user can try again if he do mistake
    }

}
}

//FURTHER TRANSACTIONS CLASS
class Further_transaction extends User_Interface{
    public static void further_transaction()
    {
        System.out.println("Do You Want To Do Further Transactions?(y/n):");            // to continue the process after any transaction performed
        String ans=sc.next();
        if(ans.equalsIgnoreCase("Y"))
        {
            new Options();
        }
        else if(ans.equalsIgnoreCase("N"))
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("|  Thank You For Using Our Service. Have a Nice Day :)|");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println();
            System.exit(0);
        }
        else
        {
            System.out.println("Typo Mistake!! Try Again!!");
            further_transaction();
        }

        }
    }

    //OPTIONS CLASS
class Options extends User_Interface
{                                         //inherited the User_Interface class to use Scanner class
    public Options()                                        //constructor  (it don't need any return type)
{         
    System.out.println("Loading..");
    try
    {
    Thread.sleep(3000);
    }
    catch(InterruptedException e)
    {
        e.printStackTrace();
    } 
        System.out.println("                 ATM SCREEN          ");            
        System.out.println("-----------------------------------------------");  
        System.out.println("|                                              |");
        System.out.println("|    -----------            -----------        |");
        System.out.println(  "|    |1.Deposit|"+"            |2.Withdraw|       |");
        System.out.println("|    -----------            -----------        |");            //options provided by the atm
        System.out.println("|                                              |");                                                   //like button names
        System.out.println("|  -----------------        --------           |");
        System.out.println(  "|  |3.Check Balance|"+"        |4.Exit|           |");
        System.out.println("|  -----------------        --------           |");
        System.out.println("|                                              |");
        System.out.println("-----------------------------------------------");  
         menu();                                         
    }

public static void menu()
{
    ATM_Funtionalities obj=new ATM_Funtionalities();             //instantiation of ATM_Funtionalities class
    int choice;
    do
    {                                                                            
    System.out.println();
    System.out.println("--------------------------------------------------------");
    System.out.println("|Just Enter The Number Of The Option You Want To Select|");
    System.out.println("--------------------------------------------------------");
    System.out.print(" Select Your Option:");                                          //select the option
    choice=sc.nextInt();
   switch(choice)
   {
    case 1-> obj.deposit();
    case 2-> obj.withdraw();
    case 3-> obj.check_balance();
    case 4->System.exit(0);
    default->System.out.println("This is an Invalid Option. Please Select a Valid Option.");
   }}while(choice<=4&&choice>0);
}
}

class User_Interface{
     static Scanner sc= new Scanner(System.in);             //making it static so that we can call it anywhere
    public static void ui1(){                                //this method called in main method
        System.out.println();
        System.out.println();
        try
        {
        Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        } 
        System.out.println(" --------------------");
        System.out.println("|      Insert Slot   |");               //gives a real atm feel
        System.out.println(" --------------------");
        System.out.println();
        System.out.println("    -------------");
        System.out.println("    |            |");
        System.out.println("    |            |");
        System.out.println("    |  ATM CARD  |");
        System.out.println("    |            |");               //atm card
        System.out.println("    |            |");
        System.out.println("    -------------");
        System.out.println();
        System.out.println();
    }
    public static void ui2()
    {                           //called in main method
        String message;
        System.out.println();
        do
        {
        System.out.print("Type 'INSERT CARD' To Continue:");           //as it is virtual project we need to type 'insert card' so that card get inserted into the insert slot
         message=sc.nextLine();
        
        if(message.equalsIgnoreCase("INSERT CARD"))
        {
            System.out.println("Loading..");
            try
            {
            Thread.sleep(3000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            } 
    System.out.println("    -------------");
    System.out.println("    |            |");
    System.out.println("    |            |");
    System.out.println(" --------------------");
    System.out.println("|      Insert Slot   |");
    System.out.println(" --------------------");
    System.out.println("    |  ATM CARD  |");
    System.out.println("    |            |");
    System.out.println("    |            |");
    System.out.println("    -------------");
    System.out.println("---------------");
    System.out.println("|Card Inserted|");
    System.out.println("---------------");
    System.out.println();
        }else
        {
            System.out.println("Typo Mistake!! Type Again");
            
        }}while(!message.equalsIgnoreCase("INSERT CARD"));                     //user can try again if he do mistake

    }
}

//PUBLIC CLASS ATM_SIMULATOR 
public class ATM_Simulator 
{
    public static Pin p = new Pin();                  //making object of the class pin as static so that we can access it anywhere
    public static void main(String[] args) 
    {                                                 //main method           (project excution starts from here)
        System.out.println("                            ------------------------------------");
        System.out.println("                            |An ATM Simulator Project Using OOPS|");
        System.out.println("                            ------------------------------------");
        Scanner sc=new Scanner(System.in);                                                              //headings about the project
        System.out.println();
        System.out.println("                                  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("                                  $$    Hello Welcome to My ATM    $$");
        System.out.println("                                  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println();
        System.out.println("-----> !!PIN Should Be Exactly 4 Digits!!   <-----");
        System.out.println();
        String PIN;
        do
        {
            System.out.print("Set Up Your PIN:");                        //setting up the pin
            PIN=sc.nextLine();                                              //declaring pin as string to avoid crash in console when other key is entered rather than numbers
            if(pincheck(PIN))
            {                                                                       //using do while loop to set pin again if it is not valid
                p.setpin(Integer.parseInt(PIN));
                System.out.println("Loading..");
                try
                {
                Thread.sleep(3000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                } 
                System.out.println();
                   System.out.println("****************************");
                   System.out.println("|   PIN Set Successfully   |");
                   System.out.println("****************************");
            }
            else
            {
                    System.out.println();
                    System.out.println("--------------------");
                    System.out.println("!!Set Up Valid PIN!!");
                    System.out.println("--------------------");
                    System.out.println();
            }
        }while(!pincheck(PIN)); 
        
        Options.ui1();                                        //user interface methods called automatically with class name because of static
        Options.ui2();
        System.out.println("Loading..");
        try
        {
        Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        } 
        new Options();                                          //constructor called automatically
        sc.close();
    }

    public static boolean pincheck(String b) {
        if (b.length() != 4) return false;
        for (int i = 0; i < b.length(); i++) {
            if (!Character.isDigit(b.charAt(i))) {                                  //logic to check whether the entered pin contains all numeric digits or not
                                                                                    //and also to check whether the entered pin is four digits or not
                return false;
            }
        }
        return true;
    }         
}
            
        
        
            
        
        
    
