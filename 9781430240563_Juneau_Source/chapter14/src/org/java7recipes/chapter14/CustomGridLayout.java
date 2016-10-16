package org.java7recipes.chapter14;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

/**
 *
 * @author cdea
 */
public class CustomGridLayout implements LayoutManager2 {

    private int vgap;
    private int hgap;
    private int rows = 2;
    private int cols = 2;
    private int minWidth = 0;
    private int minHeight = 0;
    private int preferredWidth = 0;
    private int preferredHeight = 0;
    private boolean sizeUnknown = true;
    private Component[][] components;
    private CellConstraint[][] constraints;

    public CustomGridLayout() {
        components = new Component[rows][cols];
        constraints = new CellConstraint[rows][cols];
    }

    public CustomGridLayout(int hgap, int vgap, int cols, int rows) {
        this.hgap = hgap;
        this.vgap = vgap;
        this.rows = rows;
        this.cols = cols;
        components = new Component[rows][cols];
        constraints = new CellConstraint[rows][cols];
    }


    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    private void setSizes(Container parent) {
        preferredWidth = 0;
        preferredHeight = 0;
        minWidth = 0;
        minHeight = 0;

        // calculate the largest width of all columns
        int maxColWidth[] = new int[cols];
        int maxColHeight[] = new int[rows];
        updateMaxColWidthAndHeight(maxColWidth, maxColHeight);
        
        // update preferred width
        for (int colIndx = 0; colIndx < maxColWidth.length; colIndx++) {
            preferredWidth += maxColWidth[colIndx];
            preferredWidth += hgap;
        }
        preferredWidth += hgap;
        for (int rowIndx = 0; rowIndx < maxColHeight.length; rowIndx++) {
            preferredHeight += maxColHeight[rowIndx];
            preferredHeight += vgap;
        }
        preferredHeight += vgap;

    }

    public Dimension preferredLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        setSizes(parent);

        //Add the container's insets
        Insets insets = parent.getInsets();
        dim.width = preferredWidth + insets.left + insets.right;
        dim.height = preferredHeight + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);

        //Add the container's insets
        Insets insets = parent.getInsets();
        dim.width = minWidth + insets.left + insets.right;
        dim.height = minHeight + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
//        int maxWidth = parent.getWidth() - (insets.left + insets.right);
//        int maxHeight = parent.getHeight() - (insets.top + insets.bottom);

        int x = 0, y = insets.top;

        if (sizeUnknown) {
            setSizes(parent);
        }

        // calculate the largest width of all columns
        int maxColWidth[] = new int[cols];
        
        // calculate the largest height of all columns
        int maxColHeight[] = new int[rows];
        updateMaxColWidthAndHeight(maxColWidth, maxColHeight);


        int previousWidth = 0, previousHeight = 0;

        for (int rowNum = 0; rowNum < components.length; rowNum++) {
            y += previousHeight + vgap;
            x = 0;
            previousWidth = 0;
            for (int colNum = 0; colNum < components[rowNum].length; colNum++) {
                Component curComp = components[rowNum][colNum];
                Dimension cDim = null;
                if (curComp == null) {
                    cDim = new Dimension(maxColWidth[colNum], maxColHeight[rowNum]);
                } else {
                    cDim = curComp.getPreferredSize();
                }

                x += previousWidth + hgap;

                CellConstraint cConstr = constraints[rowNum][colNum];

                if (cConstr != null) {
                    switch (cConstr.getAlign()) {
                        case CellConstraint.RIGHT:
                            x += maxColWidth[colNum] - cDim.width;
                            break;
                        case CellConstraint.CENTER:
                            x += (maxColWidth[colNum] - cDim.width) / 2;
                            break;
                    }
                }

                if (curComp != null) {
                    // Set the component's size and position.
                    curComp.setBounds(x, y, cDim.width, cDim.height);
                }
                previousWidth = cDim.width;

            }

            previousHeight = maxColHeight[rowNum];

        }
        previousWidth += hgap;
        previousHeight += vgap;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraint) {
        CellConstraint targetC = (CellConstraint) constraint;
        if (targetC != null) {
            components[targetC.getRowNum()][targetC.getColNum()] = comp;
            constraints[targetC.getRowNum()][targetC.getColNum()] = targetC;
        }
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 1f; // center
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0f; // leading
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        return preferredLayoutSize(target);
    }

    private void updateMaxColWidthAndHeight(int[] maxColWidth, int[] maxColHeight) {
        for (int rowNum = 0; rowNum < components.length; rowNum++) {
            for (int colNum = 0; colNum < components[rowNum].length; colNum++) {
                Component curComp = components[rowNum][colNum];
                if (curComp == null) {
                    continue;
                }
                Dimension cDim = curComp.getPreferredSize();
                maxColWidth[colNum] = Math.max(maxColWidth[colNum], cDim.width);
                maxColHeight[rowNum] = Math.max(maxColHeight[rowNum], cDim.height);
            }
        }
    }

    public String toString() {
        return "hgap" + hgap;
    }
}