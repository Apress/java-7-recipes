
package org.java7recipes.chapter10;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author John O'Conner (john@joconner.com)
 */
public class CurrencyOverride {

    public CurrencyOverride() {
    }
    
    public void run() {        
        BigDecimal value = new BigDecimal(12345);
 
        Locale.setDefault(Locale.JAPAN);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance(Locale.US));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);

        Locale.setDefault(Locale.US);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("JPY"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);
        
        
        Locale.setDefault(Locale.FRANCE);
        System.out.printf("Default locale: %s\n", Locale.getDefault().getDisplayName());
        nf = NumberFormat.getCurrencyInstance();
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n", formattedCurrency);
        nf.setCurrency(Currency.getInstance("USD"));
        formattedCurrency = nf.format(value);
        System.out.printf("%s\n\n", formattedCurrency);
        
        
    }
    
    public static void main(String[] args) {
        CurrencyOverride app  = new CurrencyOverride();
        app.run();
    }
}
