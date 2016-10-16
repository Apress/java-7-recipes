
package org.java7recipes.chapter7.recipe7_04;



/**
 * Recipe 7-4 Factory
 * 
 * @author juneau
 */
public class PlayerFactory {
    
    public static PlayerType createPlayer(String playerType){
        PlayerType returnType;
        switch(playerType){
        case "GOALIE":
            returnType = new Goalie();
            break;
        case "LEFT":
            returnType = new LeftWing();
            break;
        case "RIGHT":
            returnType = new RightWing();
            break;
        case "CENTER":
            returnType = new Center();
            break;
        case "DEFENSE":
            returnType = new Defense();
            break;
        default:
            returnType = new AllPosition();
        }
        return returnType;
    }
}
