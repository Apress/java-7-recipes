
package org.java7recipes.chapter7.recipe7_04;

/**
 * Recipe 7-4
 * 
 * @author juneau
 */
public class Defense extends Player implements PlayerType {
    
    private int totalBlocks;
    private int shotsOnGoal;
    private int steals;

    /**
     * @return the totalBlocks
     */
    public int getTotalBlocks() {
        return totalBlocks;
    }

    /**
     * @param totalBlocks the totalBlocks to set
     */
    public void setTotalBlocks(int totalBlocks) {
        this.totalBlocks = totalBlocks;
    }

    /**
     * @return the shotsOnGoal
     */
    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

    /**
     * @param shotsOnGoal the shotsOnGoal to set
     */
    public void setShotsOnGoal(int shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }

    /**
     * @return the steals
     */
    public int getSteals() {
        return steals;
    }

    /**
     * @param steals the steals to set
     */
    public void setSteals(int steals) {
        this.steals = steals;
    }
    
    
}
