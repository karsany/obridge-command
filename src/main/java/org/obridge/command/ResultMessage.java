package org.obridge.command;

public interface ResultMessage {

    MessageLevel getLevel();

    String getMessage();

    Object getAdditionalInfo();

    enum MessageLevel {
        DEBUG, NOTICE, INFO, WARNING, ERROR, CRITICAL
    }
}
