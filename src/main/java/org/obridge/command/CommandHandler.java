package org.obridge.command;

public interface CommandHandler<T extends Command> {

    CommandResult<T> handle(T t);

}
