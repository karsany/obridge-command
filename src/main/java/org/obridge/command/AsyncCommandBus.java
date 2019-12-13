package org.obridge.command;

public interface AsyncCommandBus<T extends Command & Identifiable> extends CommandBus<T> {

    void handle(T command, CommandResultCallback<T> cr);

}
