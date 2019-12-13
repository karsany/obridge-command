package org.obridge.command;

import java.util.UUID;

public abstract class AbstractCommand implements Command, Identifiable {

    private UUID uuid = UUID.randomUUID();

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}
