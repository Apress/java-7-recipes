package org.java7recipes.chapter14.recipe14_04;

/**
 * Cell Constraints. Aligns components on the custom grid layout.
 * @author cdea
 */
public class MyCellConstraint {
    private int rowNum=0;
    private int colNum=0;
    
    public final static int LEFT = -1;
    public final static int CENTER = 0;
    public final static int RIGHT = 1;
    private int align = LEFT; // left
    
    public int getAlign() {
        return align;
    }

    public MyCellConstraint setAlign(int align) {
        this.align = align;
        return this;
    }

    public int getColNum() {
        return colNum;
    }

    public MyCellConstraint setColNum(int colNum) {
        this.colNum = colNum;
        return this;
    }

    public int getRowNum() {
        return rowNum;
    }

    public MyCellConstraint setRowNum(int rowNum) {
        this.rowNum = rowNum;
        return this;
    }
}
