package org.java7recipes.chapter4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StockPortfolio1 implements Iterable<Stock> {

    Map<String, Stock> portfolio = new HashMap<>();

    public void add(Stock stock) {
        portfolio.put(stock.getSymbol(), stock);
    }

    public void add(List<Stock> stocks) {
        for (Stock s : stocks) {
            portfolio.put(s.getSymbol(), s);
        }
    }

    public void remove(String stock) {
        portfolio.remove(stock);
    }
    
    public void remove(List<String> sellList) {
        Iterator<String> keyIter = portfolio.keySet().iterator();
        while (keyIter.hasNext()) {
            if (sellList.contains(keyIter.next())) {
                keyIter.remove();
            }
        }
    }
    
    public List<Stock> alertList() {
        List<Stock> alertList = new ArrayList<>();
        for (Stock stock : portfolio.values()) {
            if (!StockScreener.screen(stock.getSymbol(), StockScreener.Screen.PE, 20)) {
                alertList.add(stock);
            }
        }
        return alertList;
    }
    
    public void summary() {
        for (Map.Entry<String, Stock> entry : portfolio.entrySet()) {
            System.out.println("Stock = " + entry.getKey() + ", Shares = " + entry.getValue().getShares());
        }  
    }

    @Override
    public Iterator<Stock> iterator() {
        return portfolio.values().iterator();
    }

    public static void main(String[] args) {

        StockPortfolio1 myPortfolio = new StockPortfolio1();
        myPortfolio.add(new Stock("ORCL", "Oracle", 500.0));
        myPortfolio.add(new Stock("AAPL", "Apple", 200.0));
        myPortfolio.add(new Stock("GOOG", "Google", 100.0));
        myPortfolio.add(new Stock("IBM", "IBM", 50.0));
        myPortfolio.add(new Stock("MCD", "McDonalds", 300.0));

        // foreach loop (uses Iterator returned from iterator() method) 
        for (Stock stock : myPortfolio) {
            System.out.println(stock);
        }

        List<String> sellList = new ArrayList<>();
        sellList.add("IBM");
        sellList.add("GOOG");
        
        myPortfolio.remove(sellList);

        System.out.println("Portfolio Summary:");
        myPortfolio.summary();
        
        System.out.println("Alerts:");
        for (Stock stock : myPortfolio.alertList()) {
            System.out.println("Alert: " + stock.getSymbol());
        }
        
    }
}
