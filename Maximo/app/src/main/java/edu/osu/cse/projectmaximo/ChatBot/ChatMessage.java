package edu.osu.cse.projectmaximo.ChatBot;

import java.util.Date;

/**
 * Represents a message put into the chat.
 */
public class ChatMessage {
    private String message;

    private Date time;

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
     * Sets the text of the message.
     * @param message The new message.
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Gets the time the message was created.
     * @return THe Date the message was created.
     */
    public Date getTime() {
        return this.time;
    }

    /**
     * Sets the time the message was created.
     * @param time The time created.
     */
    public void setTime(Date time) { this.time = time; }
}
