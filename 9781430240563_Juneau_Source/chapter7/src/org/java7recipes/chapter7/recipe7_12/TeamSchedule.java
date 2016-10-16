
package org.java7recipes.chapter7.recipe7_12;

/**
 * Recipe 7-12
 * 
 * Defining a Class Template
 * 
 * @author juneau
 */
public class TeamSchedule extends Schedule {
    
    public TeamSchedule(String teamName){
        super(teamName);
    }
    
    

    @Override
    void calculateDaysPlayed(int month) {
        
        // Perform implementation here
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
