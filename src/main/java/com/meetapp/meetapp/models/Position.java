package com.meetapp.meetapp.models;

import com.google.api.client.util.DateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

public class Position {

    @Id
    ObjectId id;

    String userId;

    Float xPosition;

    Float yPosition;

    boolean locked; // if true is in game.

    long lastUpdate; // if too much he delete the gameObject

    int skinId;



    public int getSkinId() {
        return skinId;
    }

    public void setSkinId(int skinId) {
        this.skinId = skinId;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getxPosition() {
        return xPosition;
    }

    public void setxPosition(Float xPosition) {
        this.xPosition = xPosition;
    }

    public Float getyPosition() {
        return yPosition;
    }

    public void setyPosition(Float yPosition) {
        this.yPosition = yPosition;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
