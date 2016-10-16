/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.java7recipes.chapter7.recipe7_07;

/**
 *
 * @author juneau
 */
public class Roster {

    public static void main(String[] args) {

        TeamType myTeam = createTeam("Juneau Royals", "Chicago");
        System.out.println(myTeam.getFullName());
    }

    public static Team createTeam(String teamName, String city) {
        TeamBuilder builder = new HockeyTeamBuilder();
        builder.buildNewTeam(teamName);
        builder.designateTeamCity(city);
        builder.buildPlayerList();
        return builder.getTeam();
    }
}
