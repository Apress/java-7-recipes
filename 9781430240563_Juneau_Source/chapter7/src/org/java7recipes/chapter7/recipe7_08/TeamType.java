
package org.java7recipes.chapter7.recipe7_08;

import java.util.List;
import org.java7recipes.chapter7.recipe7_04.Player;

/**
 * Recipe 7-8
 * 
 * Interacting with Classes via an Interface
 * 
 * @author juneau
 */
public interface TeamType {
    
    void setPlayers(List<Player> players);
    void setName(String name);
    void setCity(String city);
    String getFullName();

}
