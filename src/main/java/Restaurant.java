import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime, List<Item> menu) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.menu = menu;
    }

    public boolean isRestaurantOpen() {
        LocalTime curLocalTime = getCurrentTime();
        return (openingTime.compareTo(curLocalTime) <= 0 && closingTime.compareTo(curLocalTime) >= 0);
        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        return menu;
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
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

    public int getTotalPrice(List<String> itemNames){
        int price = 0;
        for(String itemName: itemNames) {
            Item selectedItem = menu.stream().filter(item -> itemName.equals(item.getName())).findAny().orElse(null);
            if(selectedItem != null)
                price += selectedItem.getPrice();
        }
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Restaurant)) {
            return false;
        }
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(name, restaurant.name) && Objects.equals(location, restaurant.location) && Objects.equals(openingTime, restaurant.openingTime) && Objects.equals(closingTime, restaurant.closingTime) && Objects.equals(menu, restaurant.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, openingTime, closingTime, menu);
    }


}
