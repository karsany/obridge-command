package org.obridge.command.example;

import org.obridge.command.Command;
import org.obridge.command.Identifiable;

public interface RegisterNewUserCommand extends Command, Identifiable {

    String getUserName();

    String getPasswordHash();

    String getEmailAddress();

}
