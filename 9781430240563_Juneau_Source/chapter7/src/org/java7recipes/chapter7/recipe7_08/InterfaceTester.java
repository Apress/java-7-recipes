
package org.java7recipes.chapter7.recipe7_08;

import org.java7recipes.chapter7.*;


/**
 * Recipe 7-8
 * 
 * @author juneau
 */
public class InterfaceTester {
    
    static TeamType team = new Team();
    
    public static void main(String[] args){
        team.setCity("SomeCity");
        team.setName("SomeName");
        team.setPlayers(null);
        System.out.println(team.getFullName());
    }
    
}
