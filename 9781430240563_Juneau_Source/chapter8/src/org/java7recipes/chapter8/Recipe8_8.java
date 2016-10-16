package org.java7recipes.chapter8;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/10/11
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe8_8 {
    public static void main(String[] args) {
        Recipe8_8 recipe = new Recipe8_8();
        recipe.start();
    }

    private void start() {

    }

    class CustomerOrder {
        private String itemOrdered;
        private int quantityOrdered;
        private String customerName;

        public CustomerOrder() {

        }

        public double calculateOrderTotal (double price) {
            synchronized (this) {
                return getQuantityOrdered()*price;
            }
        }

        public synchronized String getItemOrdered() {
            return itemOrdered;
        }

        public synchronized int getQuantityOrdered() {
            return quantityOrdered;
        }

        public synchronized String getCustomerName() {
            return customerName;
        }

        public synchronized void setItemOrdered(String itemOrdered) {
            this.itemOrdered = itemOrdered;
        }

        public synchronized void setQuantityOrdered(int quantityOrdered) {
            this.quantityOrdered = quantityOrdered;
        }

        public synchronized void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }

    class ImmutableCustomerOrder {
        final private String itemOrdered;
        final private int quantityOrdered;
        final private String customerName;

        ImmutableCustomerOrder(String itemOrdered, int quantityOrdered, String customerName) {
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

        public synchronized double calculateOrderTotal (double price) {
            return getQuantityOrdered()*price;
        }
    }
}
