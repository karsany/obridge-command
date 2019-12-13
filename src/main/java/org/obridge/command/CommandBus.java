package org.obridge.command;

public interface CommandBus<Q extends Command> {

    void registerHandler(CommandHandler<Q> ch);

    CommandResult<Q> handle(Q command);

}
