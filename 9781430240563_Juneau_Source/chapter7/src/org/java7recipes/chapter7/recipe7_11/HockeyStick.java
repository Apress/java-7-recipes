
package org.java7recipes.chapter7.recipe7_11;

/**
 * 
 * Recipe 7-11
 * 
 * Object representing a hockey stick
 * 
 * @author juneau
 */
public class HockeyStick {
    
    public int length;
    public boolean isCurved;
    public String material;
    
    public HockeyStick(int length, boolean isCurved, String material){
        this.length = length;
        this.isCurved = isCurved;
        this.material = material;
    }

    public int getlength() {
        return length;
    }

    public void setlength(int length) {
        this.length = length;
    }

    public boolean isIsCurved() {
        return isCurved;
    }

    public void setIsCurved(boolean isCurved) {
        this.isCurved = isCurved;
    }

    public String getmaterial() {
        return material;
    }

    public void setmaterial(String material) {
        this.material = material;
    }
    
}
