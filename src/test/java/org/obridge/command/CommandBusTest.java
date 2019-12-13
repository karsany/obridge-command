package org.obridge.command;

import org.junit.Test;
import org.obridge.command.example.FormRegisterNewUserCommand;
import org.obridge.command.example.RegisterNewUserCommand;
import org.obridge.command.example.RegisterNewUserCommandHandler;
import org.obridge.command.implementations.SimpleAsyncCommandBus;
import org.obridge.command.implementations.TestPersistedCommandBus;

public class CommandBusTest {

    @Test
    public void registerHandler() throws InterruptedException {
        final FormRegisterNewUserCommand command = new FormRegisterNewUserCommand("hupu@example.com", "PassCode12345");

        final CommandBus cb = new TestPersistedCommandBus(new SimpleCommandBus());
        cb.registerHandler(new RegisterNewUserCommandHandler());

        final CommandResult<RegisterNewUserCommand> commandCommandResult = cb.handle(command);

        for (ResultMessage result : commandCommandResult.results()) {
            System.out.println(result.getLevel() + "  " + result.getMessage());
        }

        System.out.println("------------------------------------");

        final AsyncCommandBus sacb = new SimpleAsyncCommandBus(cb);
        sacb.handle(command, commandResult -> {
            commandResult.results()
                         .stream()
                         .forEach(o -> {
                             System.out.println("   Msg: " + ((ResultMessage) o).getMessage());
                         });
        });

        Thread.sleep(3000);

    }
}