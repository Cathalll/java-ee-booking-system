/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cathal
 */
public class MatchesItemInListTest {
    
    public MatchesItemInListTest() {
    }
    
    private static Item item;
    private static List<Item> cart;
    private static Item cartItem1;
    private static Item cartItem2;
    private static Session session;
    
    @Before
    public void setUp() {
        
        item = new Item();
        session = new Session();
        item.setSession(session);
        List<Item> cart = new ArrayList<Item>();
        cart.add(item);
        
        System.out.println("number of items in cart = " + cart.size());
        boolean cartContainsItem = cart.contains(item);
        
        System.out.println("cart contains item =" + cartContainsItem);
        
        boolean sameSessionAsItem = session.equals(item.getSession());
        
        System.out.println("session.equals(item.getSession()) = "+ sameSessionAsItem);
        
    }
    
    @Test
    public void testListContainingItemItself(){
        
        boolean result1 = item.matchesItemInList(cart);
        
        assertTrue("Item item is contained within cart", result1);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
