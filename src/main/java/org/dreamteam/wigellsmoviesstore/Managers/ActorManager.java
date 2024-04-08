package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.dreamteam.wigellsmoviesstore.IoConverter;

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
    public void updateActor(int actorId, String newFirstName, String newLastName){
        Actor actor = daOmanager.getActorDao().getActorById(actorId);
        actor.setFirstName(newFirstName);
        actor.setLastName(newLastName);
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        daOmanager.getActorDao().updateActor(actor);
    }
    public void deleteActor(int actorId){
        Actor actor = daOmanager.getActorDao().getActorById(actorId);
        daOmanager.getActorDao().deleteActor(actor);

    }
}
