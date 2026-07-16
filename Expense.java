import java.io.Serializable;
class Expense implements Serializable {
    int id;
    String category;
    double amount;
    String description;
    Expense(int id,String category,double amount,String description){
        this.id=id;
        this.category=category;
        this.amount=amount;
        this.description=description;
    }
    public String toString(){
        return "ID: "+id+
               "\nCategory: "+category+
               "\nAmount: ₹"+amount+
               "\nDescription: "+description+
               "\n";
    }
}