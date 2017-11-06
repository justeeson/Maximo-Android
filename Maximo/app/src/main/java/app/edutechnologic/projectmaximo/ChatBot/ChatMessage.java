package app.edutechnologic.projectmaximo.ChatBot;

import java.util.GregorianCalendar;

/**
 * Represents a message put into the chat.
 */
class ChatMessage {
    private String message = "";

    private long time = 0;

    private boolean isResponse = false;

    public ChatMessage(String message) {
        this.message = message;
        this.time = new GregorianCalendar().getTimeInMillis();
    }

    /**
     * Sets the text of the message.
     * @param message      The message to be passed on to Watson API
     * @param timeInMillis Holds the current time in milliseconds
     */
    public ChatMessage(String message, long timeInMillis) {
        this.message = message;
        this.time = timeInMillis;
    }

    /**
     * Gets the text of the message.
     *
     * @return String of text.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Sets the text of the message.
     *
     * @param message The new message.
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * Gets the time the message was created.
     *
     * @return thee Date the message was created.
     */
    public long getTime() { return this.time; }

    /**
     * Sets the time the message was created.
     *
     * @param timeInMillis the time created.
     */
    public void setTime(long timeInMillis) { this.time = timeInMillis; }

    /**
     * Gets whether or not the message is a request
     *
     * @return      true if the message is a response. False if it is a request.
     */
    public boolean getIsResponse() { return this.isResponse; }

    /**
     * Sets if the message is a response from the chat bot.
     *
     * @param isResponse use false if a request to the chat bot, true if response
     */
    public void setIsResponse(boolean isResponse) {this.isResponse = isResponse; }
}
