package app.edutechnologic.projectmaximo.ChatBot;

class WatsonMessage {
    private String message;

    public WatsonMessage() {
        this.message = "";
    }

    public void setWatsonMessage(String passedString) {
        this.message = passedString;
    }

    public String getMessageAsString() {
        return this.message;
    }

}
