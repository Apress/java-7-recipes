package org.java7recipes.chapter8;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/10/11
 * Time: 11:05 AM
 * Thread safe counter
 */
public class Recipe8_9 {
    public static void main(String[] args) {
        Recipe8_9 recipe = new Recipe8_9();
        recipe.start();
    }

    List<Order> orders= Collections.synchronizedList(new ArrayList<Order>());

    private void start() {
        for (int i =0;i < 10;i++) {
            Thread orderCreationThread = new Thread(new Runnable() {
                public void run() {
                    for (int i= 0;i < 10;i++) {
                        createOrder(Thread.currentThread().getName());
                    }
                }
            });
            orderCreationThread.setName("Order Creation Thread "+i);
            orderCreationThread.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // check if there are any gaps in the orders.
        Set<Long> orderIds = new HashSet<Long>();
        for (Order order : orders) {
            if (orderIds.contains(order.getOrderId())) {
                System.out.println("The horror! an orderID has been reused!");
            }
            orderIds.add(order.getOrderId());
            System.out.println("Order id:"+order.getOrderId());
        }
    }

    AtomicLong orderIdGenerator = new AtomicLong(0);
    private void createOrder(String name) {
        long orderId = orderIdGenerator.incrementAndGet();
        Order order = new Order(name, orderId);
        orders.add(order);
    }

    class Order {
        String orderName;
        long orderId;

        Order(String orderName, long orderId) {
            this.orderName = orderName;
            this.orderId = orderId;
        }

        public String getOrderName() {
            return orderName;
        }

        public long getOrderId() {
            return orderId;
        }
    }
}
