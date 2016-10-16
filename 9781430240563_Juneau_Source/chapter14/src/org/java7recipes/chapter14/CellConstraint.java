package org.java7recipes.chapter14;

/**
 *
 * @author cdea
 */
public class CellConstraint {
    private int rowNum=0;
    private int colNum=0;
    
    public final static int LEFT = -1;
    public final static int CENTER = 0;
    public final static int RIGHT = 1;
    private int align = LEFT; // left
    
    public int getAlign() {
        return align;
    }

    public CellConstraint setAlign(int align) {
        this.align = align;
        return this;
    }

    public int getColNum() {
        return colNum;
    }

    public CellConstraint setColNum(int colNum) {
        this.colNum = colNum;
        return this;
    }

    public int getRowNum() {
        return rowNum;
    }

    public CellConstraint setRowNum(int rowNum) {
        this.rowNum = rowNum;
        return this;
    }
}
