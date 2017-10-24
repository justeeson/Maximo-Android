package app.edutechnologic.projectmaximo.ChatBot;

/**
 * Created by DarkShanks on 10/13/2017.
 */

public class WatsonMessage {
    private String message;
    private String timestamp;

    public WatsonMessage(){
        this.message = "";
    }

    public WatsonMessage(String passedString){
        this.message = passedString;
    }

    public void setWatsonMessage(String passedString){
        this.message = passedString;
    }

    public String getMessageAsString(){
        return this.message;
    }

    public void setTimeStamp(String time) {
        this.timestamp = time;
    }

    public String getTimeStamp() {
        return this.timestamp;
    }
}
