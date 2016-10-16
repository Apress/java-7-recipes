package org.java7recipes.chapter14.recipe14_13;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.StrokeBorder;
import javax.swing.border.TitledBorder;
import org.java7recipes.chapter14.CellConstraint;
import org.java7recipes.chapter14.CustomGridLayout;
import org.java7recipes.chapter14.SimpleAppLauncher;

/**
 * Border Designer.
 * @author cdea
 */
public final class BorderDesigner extends JTabbedPane {
    final static Map<String, Color> COLOR_MAP = new TreeMap<>();
    static {
        COLOR_MAP.put("Black", Color.BLACK);
        COLOR_MAP.put("Blue", Color.BLUE);
        COLOR_MAP.put("Green", Color.GREEN);
        COLOR_MAP.put("Red", Color.RED);
        COLOR_MAP.put("Gray", Color.GRAY);
        COLOR_MAP.put("Yellow", Color.YELLOW);
        COLOR_MAP.put("White", Color.WHITE);
    }
    final static Border[] BORDERS = new Border[8];
    static {
        BORDERS[0] = BorderFactory.createLineBorder(Color.BLACK);
        BORDERS[1] = BorderFactory.createLoweredBevelBorder();
        BORDERS[2] = BorderFactory.createRaisedBevelBorder();
        BORDERS[3] = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        BORDERS[4] = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
        BORDERS[5] = BorderFactory.createDashedBorder(Color.BLACK, 4, 4);
        BORDERS[6] = BorderFactory.createStrokeBorder(new BasicStroke(3));
        BORDERS[7] = BorderFactory.createTitledBorder(BORDERS[0], "Titled Border", TitledBorder.LEFT, TitledBorder.DEFAULT_JUSTIFICATION);

    }
    final static String[] BORDER_TYPES = {"Line Border",
            "Lowered Bevel Border",
            "Raised Bevel Border",
            "Lowered Etched Border",
            "Raised Etched Border",
            "Dashed Border",
            "Stroke Border",
            "Titled Border"};
    
    public BorderDesigner() {
        
        
        JPanel borderTab = new JPanel();
        borderTab.setLayout(new CustomGridLayout(10, 20, 2, 2));
        
        // Border area
        final JPanel borderArea = new JPanel();
        borderArea.add(new JLabel("Java 7 Recipes"));
        borderArea.setPreferredSize(new Dimension(200, 100));
        borderArea.setBorder(BORDERS[0]);
        // (Right of the border area)
        addToPanel(borderTab, borderArea, 1, 0, CellConstraint.CENTER);
        
        // ComboBox changing the individual borders
        final JComboBox<String> borderComboBox = new JComboBox<>(BORDER_TYPES);
        
        // Set border when selection changes
        final List<String> borderTypeList = Arrays.asList(BORDER_TYPES);

        borderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) borderComboBox.getSelectedItem();
                int index = borderTypeList.indexOf(selected);
                borderArea.setBorder(BORDERS[index]);
            }
        });

        // ComboBox to change the color on certain borders
        JComboBox<String> colorComboBox = createColorComboBox(BORDERS, borderArea);
        
        // Place both combo boxes on North and South of Border layout
        JPanel controlsArea = new JPanel(new BorderLayout(5, 5));
        controlsArea.add(borderComboBox, BorderLayout.NORTH);
        controlsArea.add(colorComboBox, BorderLayout.SOUTH);

        // Place controls area in grid cell 0,0 (Left of the border area)
        addToPanel(borderTab, controlsArea, 0, 0, CellConstraint.RIGHT);

        // place borders tab in tabbed pane
        addTab("Borders", null, borderTab, "Simple Borders");

    }

    private JComboBox<String> createColorComboBox(final Border[] borders, final JPanel borderArea) {
        
        final JComboBox<String> colorComboBox = new JComboBox<>();
        final DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(COLOR_MAP.keySet().toArray());
        colorComboBox.setModel(comboBoxModel);
        colorComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Color selected = COLOR_MAP.get(colorComboBox.getSelectedItem().toString());
                Border newColoredBorder = BorderFactory.createLineBorder(selected);
                borders[0] = newColoredBorder;

                newColoredBorder = BorderFactory.createDashedBorder(selected, 4, 4);
                borders[5] = newColoredBorder;

                newColoredBorder = BorderFactory.createStrokeBorder(new BasicStroke(3), selected);
                borders[6] = newColoredBorder;
                
                newColoredBorder = BorderFactory.createTitledBorder(borders[0], "Titled Border");
                borders[7] = newColoredBorder;

                Border currentBorder = borderArea.getBorder();
                
                if (currentBorder instanceof LineBorder) {
                    borderArea.setBorder(borders[0]);
                } else if (currentBorder instanceof StrokeBorder) {
                    StrokeBorder sborder = (StrokeBorder) borderArea.getBorder();
                    if (sborder.getStroke().getDashArray() != null) {
                        borderArea.setBorder(borders[5]);
                    } else {
                        borderArea.setBorder(borders[6]);
                    }
                } else if (currentBorder instanceof TitledBorder) {
                    borderArea.setBorder(borders[7]);
                }
            }
        });
        return colorComboBox;
    }

    private void addToPanel(Container container, Component comp, int colNum, int rowNum, int align) {
        CellConstraint constr = new CellConstraint()
                .setColNum(colNum)
                .setRowNum(rowNum)
                .setAlign(align);
        container.add(comp, constr);
    }

    public static void main(String[] args) {
        final JTabbedPane c = new BorderDesigner();
        c.setPreferredSize(new Dimension(409, 204));
        // Queueing GUI work to be run using the EDT.
        SimpleAppLauncher.launch("Chapter 14-13 Designing Borders", c);
    }
}
