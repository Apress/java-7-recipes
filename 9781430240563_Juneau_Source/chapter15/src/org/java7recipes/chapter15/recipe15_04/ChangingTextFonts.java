package org.java7recipes.chapter15.recipe15_04;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Changing Text Fonts
 * @author cdea
 */
public class ChangingTextFonts extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chapter 15-4 Changing Text Fonts");
        Group root = new Group();
        Scene scene = new Scene(root, 330, 250, Color.WHITE);

        // Serif with drop shadow
        Text java7Recipes2 = new Text(50, 50, "Java 7 Recipes");
        Font serif = Font.font("Serif", 30);
        java7Recipes2.setFont(serif);
        java7Recipes2.setFill(Color.RED);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2.0f);
        dropShadow.setOffsetY(2.0f);
        dropShadow.setColor(Color.rgb(50, 50, 50, .588));
        java7Recipes2.setEffect(dropShadow);
        root.getChildren().add(java7Recipes2);


        // SanSerif
        Text java7Recipes3 = new Text(50, 100, "Java 7 Recipes");
        Font sanSerif = Font.font("SanSerif", 30);
        java7Recipes3.setFont(sanSerif);
        java7Recipes3.setFill(Color.BLUE);
        root.getChildren().add(java7Recipes3);

        // Dialog
        Text java7Recipes4 = new Text(50, 150, "Java 7 Recipes");
        Font dialogFont = Font.font("Dialog", 30);
        java7Recipes4.setFont(dialogFont);
        java7Recipes4.setFill(Color.rgb(0, 255, 0));
        root.getChildren().add(java7Recipes4);

        // Monospaced
        Text java7Recipes5 = new Text(50, 200, "Java 7 Recipes");
        Font monoFont = Font.font("Monospaced", 30);
        java7Recipes5.setFont(monoFont);
        java7Recipes5.setFill(Color.BLACK);
        root.getChildren().add(java7Recipes5);

        Reflection refl = new Reflection();
        refl.setFraction(0.8f);
        java7Recipes5.setEffect(refl);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
