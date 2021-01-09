package com.meetapp.meetapp.services;

import com.meetapp.meetapp.models.Lobby;
import com.meetapp.meetapp.models.Position;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CrimelessServices {

    private int timeOut = 1000;

    public boolean isActive(Position pos){
        boolean active = (new Date().getTime() - pos.getLastUpdate()) >= timeOut ? false : true;
        return active;
    }

    public boolean isGameStarted(Lobby lobby){
        return lobby.isGameStarted();
    }

    public String badBoyGeneration(List<Position> activePlayers){
        int badBoyNumber = (int)(Math.random() * activePlayers.size());
        return activePlayers.get(badBoyNumber).getUserId();
    }

}
