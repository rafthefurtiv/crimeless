package com.meetapp.meetapp.repositories;

import com.meetapp.meetapp.models.Position;
import com.meetapp.meetapp.services.CrimelessServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CrimelessRepositoryImpl implements CrimelessRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CrimelessServices gameServices;


    @Override
    public Position getPositionByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Position pos = mongoTemplate.findOne(query, Position.class);
        return pos;
    }

    @Override
    public Position setPositionByUserId(String userId, Position position) {
        if(position.getId() == null){
            position.setId(this.getPositionByUserId(userId).getId());
        }
        position.setUserId(userId);
        position.setLastUpdate(new Date().getTime());
        mongoTemplate.save(position);
        return position;
    }

    @Override
    public boolean getExists(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Position pos = mongoTemplate.findOne(query, Position.class);
        return (pos != null) ? true : false;
    }



    @Override
    public List<Position> getActivePlayers() {
        List<Position> positions = mongoTemplate.findAll(Position.class);
        //positions = positions.stream().filter(a -> gameServices.isActive(a)).collect(Collectors.toList());
        return positions;
    }


}
