package com.meetapp.meetapp.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Players {

    List<String> playersListString;

    public List<String> getPlayersListString() {
        return playersListString;
    }

    public void setPlayersListString(List<String> playersListString) {
        this.playersListString = playersListString;
    }

}
