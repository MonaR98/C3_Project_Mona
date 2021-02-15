import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //return true;
        LocalTime currentTime = getCurrentTime();

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        if (openingTime.isBefore(currentTime) && closingTime.isAfter(currentTime))
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
       // return null;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE

        return Collections.unmodifiableList(menu);


    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }



    public int getOrderTotal(List<String> selectedItems) {
        int orderTotal = 0;
        if (selectedItems == null) {
            return orderTotal;
        } else {

            int i;
            for (i = 0; i < selectedItems.size(); i++) {
                orderTotal += findItemByName(selectedItems.get(i)).getPrice();

            }
            return orderTotal;


        }
    }




}
