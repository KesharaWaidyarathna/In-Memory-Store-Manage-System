package Classes;

import Contollers.Search;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class ClasDB {
    public static ArrayList<CustomerTM>customers=new ArrayList<>();
    public static ArrayList<ItemsTM>items=new ArrayList<>();
    public static ArrayList<Orders>orders=new ArrayList<>();
    public static ArrayList<OrdersTM>ordertm=new ArrayList<>();
    public static ArrayList<SearchTM>search=new ArrayList<>();


    static {
        customers.add(new CustomerTM("C001","kasun","Matara"));
        customers.add(new CustomerTM("C002","ranil","colombo"));
        customers.add(new CustomerTM("C003","wimal","Matara"));
        customers.add(new CustomerTM("C004","kamal","galle"));

        items.add(new ItemsTM("I001","Mouse","20",550));
        items.add(new ItemsTM("I002","Keyboard","20",750));
        items.add(new ItemsTM("I003","Speckers","5",5000));
        items.add(new ItemsTM("I004","Monitors","10",11500));
        items.add(new ItemsTM("I005","Pen drives","30",2000));


    }
}
