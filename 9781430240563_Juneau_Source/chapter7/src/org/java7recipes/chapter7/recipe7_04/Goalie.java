
package org.java7recipes.chapter7.recipe7_04;

/**
 * Recipe 7-4
 * 
 * @author juneau
 */
public class Goalie extends Player implements PlayerType {
    
    private int totalSaves;
    
    /**
     * @return the totalSaves
     */
    public int getTotalSaves() {
        return totalSaves;
    }

    /**
     * @param totalSaves the totalSaves to set
     */
    public void setTotalSaves(int totalSaves) {
        this.totalSaves = totalSaves;
    }
    
    
}
