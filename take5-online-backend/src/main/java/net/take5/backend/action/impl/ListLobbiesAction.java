package net.take5.backend.action.impl;

import java.util.ArrayList;

import javax.websocket.Session;

import net.take5.backend.action.AbstractAction;
import net.take5.backend.context.ServerState;
import net.take5.commons.pojo.input.Message;
import net.take5.commons.pojo.input.params.NoParams;
import net.take5.commons.pojo.output.common.Lobby;
import net.take5.commons.pojo.output.common.OutputAction;
import net.take5.commons.pojo.output.common.State;
import net.take5.commons.pojo.output.response.ListLobbiesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("LIST_LOBBIES")
public class ListLobbiesAction extends AbstractAction<NoParams, ListLobbiesResponse>
{
    /** Etat du serveur */
    @Autowired
    private ServerState serverState;

    @Override
    public void initialize()
    {
        response = new ListLobbiesResponse();
        response.setAction(OutputAction.LIST_LOBBIES);
    }

    @Override
    public void execute(Session session, Message<NoParams> message)
    {
        response.setLobbies(new ArrayList<Lobby>(serverState.getLobbies()));
        response.setState(State.OK);
    }

    @Override
    public Boolean validate(Session session, Message<NoParams> message)
    {
        return true;
    }

}
