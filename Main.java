import java.util.*;
import java.io.*;
@SuppressWarnings("unchecked")
public class Main{
    static ArrayList<Expense> expenses=new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    public static void loadExpenses(){
        try{
            File file=new File("expenses.dat");
            if(file.exists()){
                ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
                expenses=(ArrayList<Expense>)in.readObject();
                in.close();
            }
        }
        catch(Exception e){
            System.out.println("No previous data found.");
        }
    }

    public static void saveExpenses(){
        try{

            ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("expenses.dat"));
            out.writeObject(expenses);
            out.close();
        }
        catch(Exception e){
            System.out.println("Error Saving Data.");

        }
    }
    public static void addExpense() {
        System.out.print("Enter Expense ID: ");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Category: ");
        String category=sc.nextLine();
        System.out.print("Enter Amount: ");
        double amount=sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter Description: ");
        String description=sc.nextLine();
        Expense e=new Expense(id,category,amount,description);
        expenses.add(e);
        saveExpenses();
        System.out.println("\nExpense Added Successfully!\n");
    }
    public static void viewExpenses(){
        if(expenses.isEmpty()){
            System.out.println("\nNo Expenses Found.\n");
            return;
        }
        for(Expense e:expenses){
            System.out.println(e);
        }
    }
    public static void searchExpense(){
        System.out.print("Enter Expense ID: ");
        int id=sc.nextInt();
        for(Expense e:expenses) {     
            if(e.id==id) {
                System.out.println("\nExpense Found\n");
                System.out.println(e);
                return;
            }
        }
        System.out.println("\nExpense Not Found.\n");
    }
    public static void updateExpense(){
        System.out.print("Enter Expense ID: ");
        int id=sc.nextInt();
        sc.nextLine();
        for(Expense e:expenses){
            if(e.id==id){
                System.out.print("Enter New Category: ");
                e.category=sc.nextLine();
                System.out.print("Enter New Amount: ");
                e.amount=sc.nextDouble();
                sc.nextLine();
                System.out.print("Enter New Description: ");
                e.description=sc.nextLine();
                saveExpenses();
                System.out.println("\nExpense Updated Successfully!\n");
                return;
            }
        }
        System.out.println("\nExpense Not Found.\n");
    }
    public static void deleteExpense(){
        System.out.print("Enter Expense ID: ");
        int id=sc.nextInt();
        for(int i=0;i<expenses.size();i++){
            if(expenses.get(i).id==id){
                expenses.remove(i);
                saveExpenses();
                System.out.println("\nExpense Deleted Successfully!\n");
                return;
            }
        }

        System.out.println("\nExpense Not Found.\n");
    }
        public static void totalExpense(){
        double total=0;
        for(Expense e:expenses){
            total+=e.amount;
        }
        System.out.println("\nTotal Spending = ₹"+total+"\n");
    }
    public static void main(String args[]){
        loadExpenses();
        while(true){
            System.out.println("========== Expense Tracker ==========");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Search Expense");
            System.out.println("4. Update Expense");
            System.out.println("5. Delete Expense");
            System.out.println("6. Total Spending");
            System.out.println("7. Exit");
            System.out.print("Enter Your Choice: ");
            int choice=sc.nextInt();
            switch(choice){
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    searchExpense();
                    break;
                case 4:
                    updateExpense();
                    break;
                case 5:
                    deleteExpense();
                    break;
                case 6:
                    totalExpense();
                    break;
                case 7:
                    saveExpenses();
                    System.out.println("\nThank You for Using Expense Tracker!");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid Choice!\n");

            }

        }

    }    
}