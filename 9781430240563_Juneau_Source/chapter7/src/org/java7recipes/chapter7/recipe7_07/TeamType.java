
package org.java7recipes.chapter7.recipe7_07;

import java.util.List;
import org.java7recipes.chapter7.recipe7_04.Player;

/**
 * Recipe 7-7
 * 
 * Constructing Instances of the Same Class with Different Values
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
