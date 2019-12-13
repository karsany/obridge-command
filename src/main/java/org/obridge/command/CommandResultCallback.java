package org.obridge.command;

public interface CommandResultCallback<T extends Command> {

    void callback(CommandResult<T> commandResult);

}
