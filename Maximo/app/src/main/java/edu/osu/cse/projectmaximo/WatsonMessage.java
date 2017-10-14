package edu.osu.cse.projectmaximo;

/**
 * Created by DarkShanks on 10/13/2017.
 */

public class WatsonMessage {
    private String message;

    public WatsonMessage(){
        message = "";
    }

    public WatsonMessage(String passedString){
        message = passedString;
    }

    public void setWatsonMessage(String passedString){
        message = passedString;
    }

    public String getMessageAsString(){
        return message;
    }
}
