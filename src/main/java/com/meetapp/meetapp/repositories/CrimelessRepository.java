package com.meetapp.meetapp.repositories;


import com.meetapp.meetapp.models.Position;

import java.util.List;

public interface CrimelessRepository {

    Position getPositionByUserId(String userId);

    Position setPositionByUserId(String userId, Position position);

    boolean getExists(String userId);


    List<Position> getActivePlayers();
}
