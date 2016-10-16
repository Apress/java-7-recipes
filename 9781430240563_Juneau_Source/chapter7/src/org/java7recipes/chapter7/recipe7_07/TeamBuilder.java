
package org.java7recipes.chapter7.recipe7_07;

/**
 *
 * @author juneau
 */
public interface TeamBuilder {
    public void buildPlayerList();
    public void buildNewTeam(String teamName);
    public void designateTeamCity(String city);
    public Team getTeam();

}
