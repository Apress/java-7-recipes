
package org.java7recipes.chapter7.recipe7_07;

import java.util.ArrayList;
import java.util.List;
import org.java7recipes.chapter7.recipe7_04.PlayerFactory;

/**
 *
 * @author juneau
 */
public class HockeyTeamBuilder implements TeamBuilder {
    
    private Team team;
    
    public HockeyTeamBuilder(){
        this.team = new Team();
    }

    @Override
    public void buildPlayerList() {
        List players = new ArrayList();
        for(int x = 0; x <= 10; x++){
            players.add(PlayerFactory.createPlayer("ALL"));
        }
        team.setPlayers(players);
    }

    @Override
    public void buildNewTeam(String teamName) {
        team.setName(teamName);
    }

    @Override
    public void designateTeamCity(String city){
        team.setCity(city);        
    }
    
    @Override
    public Team getTeam(){
        return this.team;
    }   
    
}
