package edu.osu.cse.projectmaximo;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents a message put into the chat.
 */
public class ChatMessage {
    private final String message;

    private final Date time;

    public ChatMessage(String message, Date time) {
        this.message = message;
        this.time = time;
    }

    /**
     * Gets the text of the message.
     * @return String of text.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gets the time the message was created.
     * @return THe Date the message was created.
     */
    public Date getTime() {
        return this.time;
    }
}
