package org.obridge.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class SimpleCommandBus<T extends Command> implements CommandBus<T> {

    private Map<String, CommandHandler> handlers = new HashMap<>();

    @Override
    public void registerHandler(CommandHandler<T> ch) {
        final Class<? extends CommandHandler> aClass = ch.getClass();
        final ParameterizedType genericSuperclass = (ParameterizedType) aClass.getGenericInterfaces()[0];
        final Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        String command = actualTypeArguments[0].getTypeName();
        handlers.put(command, ch);
        System.out.println("Registered handler: " + ch.getClass() + " to " + command);
    }

    @Override
    public CommandResult<T> handle(T command) {

        CommandHandler commandHandler = null;

        if (handlers.containsKey(command.getClass()
                                        .getName())) {
            commandHandler = handlers.get(command.getClass()
                                                 .getName());
        } else {
            for (Class<?> anInterface : command.getClass()
                                               .getInterfaces()) {

                if (handlers.containsKey(anInterface.getName())) {
                    commandHandler = handlers.get(anInterface.getName());
                    break;
                }

            }

        }

        return commandHandler.handle(command);
    }
}
