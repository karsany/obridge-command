package org.obridge.command.implementations;

import java.time.LocalDateTime;

import org.obridge.command.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestPersistedCommandBus<T extends Command & Identifiable> extends AbstractPersistedCommandBus<T> {

    ObjectMapper om = new ObjectMapper();

    {
        om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public TestPersistedCommandBus(CommandBus commandBus) {
        super(commandBus);
    }

    @Override
    protected void persistCommandStart(T command) {
        try {
            System.out.println("Save to db: " + command.getUUID() + " at " + LocalDateTime.now() + " data: "
                    + om.writeValueAsString(command));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void persistCommandFinished(CommandResult<T> commandResult) {
        System.out.println("Finished: " + commandResult.command()
                                                       .getUUID()
                + " at " + LocalDateTime.now());

        try {
            System.out.println("  Messages: " + om.writeValueAsString(commandResult.results()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void persistCommandFailed(T command) {
        System.out.println("Failed: " + command.getUUID() + " at " + LocalDateTime.now());
    }

}
