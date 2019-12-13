package org.obridge.command.example;

import org.obridge.command.CommandHandler;
import org.obridge.command.CommandResult;
import org.obridge.command.ResultMessage;

public class RegisterNewUserCommandHandler implements CommandHandler<RegisterNewUserCommand> {

    @Override
    public CommandResult<RegisterNewUserCommand> handle(final RegisterNewUserCommand cmd) {

        System.out.println(cmd.getUUID());
        System.out.println("Registering user: " + cmd.getUserName() + " -> " + cmd.getEmailAddress() + " "
                + cmd.getPasswordHash());

        return new CommandResult.OK(cmd)
                                        .addMessage(
                                                    ResultMessage.MessageLevel.INFO,
                                                    "Successfull");

    }

}
