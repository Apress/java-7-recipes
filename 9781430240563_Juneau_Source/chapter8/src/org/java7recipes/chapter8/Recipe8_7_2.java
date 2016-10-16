package org.java7recipes.chapter8;

import com.sun.javaws.exceptions.ExitException;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.fastinfoset.algorithm.BooleanEncodingAlgorithm;

import javax.lang.model.element.Element;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.peer.FontPeer;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/9/11
 * Time: 9:50 PM
 * Recipe using Wait/Notify
 */
public class Recipe8_7_2 {

    Random random = new Random();
    List<String> itemList = new ArrayList<String>();
    Map<String,Integer> inventoryMap = new HashMap<String, Integer>();
    Collection<Order> orderList = new ArrayList<Order>();


    public static void main(String[] args) {
        Recipe8_7_2 recipe = new Recipe8_7_2();
        recipe.start();

    }

    CountDownLatch latch = new CountDownLatch(2);

    private void start() {
        loadItems();

        Thread inventoryThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Loading Inventory from Database...");
                loadInventory();
                latch.countDown();
            }
        });

        inventoryThread.start();

        Thread ordersThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("Loading Orders from XML Web service...");
                loadOrders();
                latch.countDown();
            }
        });

        ordersThread.start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        processOrders();

    }

    private void processOrders() {
        for (Order order : orderList) {
            boolean fulfillable = canFulfill(order);
            if (fulfillable) {
                doFulfill (order);
                System.out.println("Order # " + String.valueOf(order.getOrderId()) + " has been fulfilled");
            } else {
                System.out.println("Order # "+String.valueOf(order.getOrderId())+" CANNOT be fulfilled");
            }
        }
    }

    private void doFulfill(Order order) {
        for (String item : order.getOrderedItems().keySet()) {
            int quantity = order.getOrderedItems().get(item);
            int currentInventory = inventoryMap.get(item);
            inventoryMap.put(item, currentInventory-quantity);
        }
    }

    private boolean canFulfill(Order order) {
        for (String item : order.getOrderedItems().keySet()) {
            int quantity = order.getOrderedItems().get(item);
            int currentInventory = inventoryMap.get(item);
            if (quantity > currentInventory) {
                return false;
            }
        }
        return true;
    }

    private void loadOrders() {
        for (int i= 0;i < 1000;i++) {
            Order order = new Order(i);
            for (int j =0;j < 10;j++) {
                String randomItem = itemList.get(random.nextInt(itemList.size()));
                order.addItem(randomItem,random.nextInt(2)+1);
            }
            orderList.add(order);
        }
    }

    private void loadItems() {
        for (int i= 0;i < 100;i++) {
            itemList.add("Item #"+i);
        }
    }


    private void loadInventory() {
        for (String item : itemList) {
            inventoryMap.put(item,random.nextInt(500));
        }
    }

    class Order {
        long orderId;
        private Map<String,Integer> orderedItems = new HashMap<String, Integer>();

        Order(long orderId) {
            this.orderId = orderId;
        }
        public void addItem (String itemName, int quantity) {
            Integer currentQuantity = orderedItems.get(itemName);
            if (currentQuantity == null) {
                currentQuantity =0;
            }
            orderedItems.put(itemName,currentQuantity+quantity);
        }

        public Map<String, Integer> getOrderedItems() {
            return orderedItems;
        }

        public long getOrderId() {
            return orderId;
        }
    }
}
