import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setUp(){

        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);


    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE



        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime timeBetweenOpeningAndClosingTime = LocalTime.parse("16:00:00");
        Mockito.doReturn(timeBetweenOpeningAndClosingTime).when(spyRestaurant).getCurrentTime();
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();


        assertEquals(true,isRestaurantOpen);

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE



        Restaurant spyRestaurant = Mockito.spy(restaurant);
        LocalTime timeBetweenOpeningAndClosingTime = LocalTime.parse("04:00:00");
        Mockito.doReturn(timeBetweenOpeningAndClosingTime).when(spyRestaurant).getCurrentTime();
        boolean isRestaurantOpen = spyRestaurant.isRestaurantOpen();


        assertEquals(false,isRestaurantOpen);




    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    //<<<<<<<<<<<<<<<<<<<<<<Order Total>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void when_items_selected_order_total_should_not_be_zero(){


        List<String> selectedItems = new ArrayList<String>();
        selectedItems.add("Vegetable lasagne");
        selectedItems.add("Sweet corn soup");
        int orderTotal = restaurant.getOrderTotal(selectedItems);
      //  System.out.println(orderTotal);
        assertNotEquals(0,orderTotal);
    }

    @Test
    public void when_items_selected_order_total_should_be_zero(){

        List<String> selectedItems = new ArrayList<String>();
        int orderTotal = restaurant.getOrderTotal(selectedItems);
       // System.out.println(orderTotal);
        assertEquals(0,orderTotal);


    }



}