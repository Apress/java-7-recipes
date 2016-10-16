package org.java7recipes.chapter12.recipe12_01;

import java.awt.Point;
import java.awt.geom.Point2D;

public class CreatePoints {
    public static void main(String[] args) {
        Point2D pointA = new Point2D.Double(2.555555555555555, 3.7777777777777777);
        Point2D.Float pointB = new Point2D.Float(11.555555555555555555555555555f, 10.2f);
        Point pointC = new Point(100, 100);

        System.out.println("pointA = " + pointA.getX() + ", " + pointA.getY());
        System.out.println("pointB = " + pointB.x      + ", " + pointB.y);
        System.out.println("pointC = " + pointC.x      + ", " + pointC.y);
    }
}