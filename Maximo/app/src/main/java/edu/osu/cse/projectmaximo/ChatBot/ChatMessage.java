package edu.osu.cse.projectmaximo.ChatBot;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Represents a message put into the chat.
 */
public class ChatMessage {
    private String message;

    private long time;

    private boolean isResponse = false;

    public ChatMessage(String message, long timeInMillis) {
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
    public long getTime() { return this.time; }

    /**
     * Sets the time the message was created.
     * @param timeInMillis The time created.
     */
    public void setTime(long timeInMillis) { this.time = timeInMillis; }

    /**
     * Gets whether or not the message
     * @return True if the message is a response. False if it is a request.
     */
    public boolean getIsResponse() { return this.isResponse; }

    /**
     * Sets if the message is a response from the chat bot.
     * @param isResponse Use false if a request to the chat bot. Use true if response.
     */
    public void setIsResponse(boolean isResponse) {this.isResponse = isResponse; }
}
