
package org.java7recipes.chapter7.recipe7_09;

import org.java7recipes.chapter7.recipe7_08.*;
import java.util.List;
import org.java7recipes.chapter7.recipe7_04.Player;

/**
 * Recipe 7-9
 * 
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
