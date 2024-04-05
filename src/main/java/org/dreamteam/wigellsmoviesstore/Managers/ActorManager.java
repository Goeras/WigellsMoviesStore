package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ActorManager {

    DAOmanager daOmanager = new DAOmanager();
    public void newActor(String firstName, String lastName){
        Actor actor = new Actor();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        daOmanager.getActorDao().createActor(actor);
    }

}
