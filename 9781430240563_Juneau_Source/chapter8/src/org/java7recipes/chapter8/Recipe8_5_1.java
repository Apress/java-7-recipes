package org.java7recipes.chapter8;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/8/11
 * Time: 10:41 PM
 * Updated Collections
 */
public class Recipe8_5_1 {

    Set<Thread> orderingThreads = new HashSet<Thread>();
    public static void main(String[] args) throws InterruptedException {
        Recipe8_5_1 recipe = new Recipe8_5_1();
        recipe.start();
    }

    final Map<String,Integer> inventoryMap = new LinkedHashMap<String, Integer>();
    List<CustomerOrder> customerOrders = new ArrayList<CustomerOrder>();
    Random random = new Random();
    private void start() throws InterruptedException {
        // let's populate our inventory with items.
        // at most, we have 20 books
        for (int i =0;i < 100;i++) {
            inventoryMap.put("Apress Book #"+i,1000);
        }

        // now, let's create ordering threads.
        for (int i= 0;i < 20;i++) {
            createOrderingThread();
        }


        //wait a little
        Thread.sleep(100);

        // Check on inventory right now
        checkInventoryLevels();

        // Wait little longer
        Thread.sleep(100);


        // Stop everything.
        for (Thread thread : orderingThreads) {
            thread.interrupt();
        }

        Thread.sleep(1000);

        // Check inventory levels again
        checkInventoryLevels();
        // Print the orders.
        displayOrders();

    }



    private void displayOrders() {
        synchronized (inventoryMap) {
            for (CustomerOrder order : customerOrders) {
                System.out.println(order.getQuantityOrdered()+" "+order.getItemOrdered()+" for "+order.getCustomerName());
            }
        }
    }

    private void createOrderingThread() {
        Thread orderingThread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    createRandomOrder();
                }
            }
        });
        orderingThread.start();
        orderingThreads.add(orderingThread);



    }

    private void createRandomOrder() {
        String itemOrdered = "Apress Book #"+random.nextInt(100);
        int quantityOrdered = random.nextInt(2)+1;
        String customerName = "Customer :"+UUID.randomUUID().toString();
        fulfillOrder(itemOrdered, quantityOrdered, customerName);
    }

    private boolean fulfillOrder(String itemOrdered, int quantityOrdered, String customerName) {
        synchronized (inventoryMap) {
            int currentInventory  = inventoryMap.get(itemOrdered);
            if (currentInventory < quantityOrdered) {
                System.out.println("Couldn't fulfill order for "+customerName+" not enough "+itemOrdered+" ("+quantityOrdered+")");
                return false; // sorry, we sold out
            }
            inventoryMap.put(itemOrdered,currentInventory - quantityOrdered);
            CustomerOrder order = new CustomerOrder(itemOrdered, quantityOrdered, customerName);
            customerOrders.add(order);
            System.out.println("Order fulfilled for "+customerName+" of "+itemOrdered+" ("+quantityOrdered+")");
            return true;
        }
    }

    private void checkInventoryLevels() {
        synchronized (inventoryMap) {
            System.out.println("------------------------------------");
            for (Map.Entry<String,Integer> inventoryEntry : inventoryMap.entrySet()) {
                System.out.println("Inventory Level :"+inventoryEntry.getKey()+" "+inventoryEntry.getValue());
            }
            System.out.println("------------------------------------");
        }
    }

    class CustomerOrder {
        String itemOrdered;
        int quantityOrdered;
        String customerName;

        CustomerOrder(String itemOrdered, int quantityOrdered, String customerName) {
            this.itemOrdered = itemOrdered;
            this.quantityOrdered = quantityOrdered;
            this.customerName = customerName;
        }

        public String getItemOrdered() {
            return itemOrdered;
        }

        public int getQuantityOrdered() {
            return quantityOrdered;
        }

        public String getCustomerName() {
            return customerName;
        }
    }
}
