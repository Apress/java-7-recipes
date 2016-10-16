package org.java7recipes.chapter14.recipe14_04;


import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;

/**
 * My Custom Grid Layout.
 * @author cdea
 */
public class MyCustomGridLayout implements LayoutManager2 {

    private int vgap;
    private int hgap;
    private int rows = 2;
    private int cols = 2;
    private int minWidth;
    private int minHeight;
    private int preferredWidth;
    private int preferredHeight;
    private boolean sizeUnknown = true;
    private Component[][] components;
    private MyCellConstraint[][] constraints;

    public MyCustomGridLayout(int hgap, int vgap, int cols, int rows) {
        System.out.println("-MyCustomGridLayout");
        this.hgap = hgap;
        this.vgap = vgap;
        this.rows = rows;
        this.cols = cols;
        components = new Component[rows][cols];
        constraints = new MyCellConstraint[rows][cols];
    }


    public void addLayoutComponent(String name, Component comp) {
        System.out.println("-addLayoutComponent");
    }

    public void removeLayoutComponent(Component comp) {
        System.out.println("-removeLayoutComponent");
    }

    private void setSizes(Container parent) {
        System.out.println("-setSizes");
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
        System.out.println("-preferredLayoutSize");
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
        System.out.println("-minimumLayoutSize");
        Dimension dim = new Dimension(0, 0);

        //Add the container's insets
        Insets insets = parent.getInsets();
        dim.width = minWidth + insets.left + insets.right;
        dim.height = minHeight + insets.top + insets.bottom;

        sizeUnknown = false;

        return dim;
    }

    public void layoutContainer(Container parent) {
        System.out.println("-layoutContainer");
        Insets insets = parent.getInsets();
        int availableWidth = parent.getWidth() - (insets.left + insets.right);
        int availableHeight = parent.getHeight() - (insets.top + insets.bottom);

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

                MyCellConstraint cConstr = constraints[rowNum][colNum];

                if (cConstr != null) {
                    switch (cConstr.getAlign()) {
                        case MyCellConstraint.RIGHT:
                            x += maxColWidth[colNum] - cDim.width;
                            break;
                        case MyCellConstraint.CENTER:
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
        System.out.println("-addLayoutComponent");
        MyCellConstraint targetC = (MyCellConstraint) constraint;
        if (targetC != null) {
            components[targetC.getRowNum()][targetC.getColNum()] = comp;
            constraints[targetC.getRowNum()][targetC.getColNum()] = targetC;
        }
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        System.out.println("-getLayoutAlignmentX");
        return 1f; // center
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        System.out.println("-getLayoutAlignmentY");
        return 0f; // leading
    }

    @Override
    public void invalidateLayout(Container target) {
        System.out.println("-invalidateLayout");
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        System.out.println("-maximumLayoutSize");
        return preferredLayoutSize(target);
    }

    private void updateMaxColWidthAndHeight(int[] maxColWidth, int[] maxColHeight) {
        //System.out.println("-updateMaxColWidthAndHeight");
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
}