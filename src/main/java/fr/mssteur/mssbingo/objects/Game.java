package fr.mssteur.mssbingo.objects;

import org.bukkit.entity.Player;

public class Game {
    public State state;

    public void create(){
        this.state=State.Open;
    }

    public void launch(){

        this.state=State.Launched;

    }

    public void win(Player player){
        this.state=State.End;
    }
}
