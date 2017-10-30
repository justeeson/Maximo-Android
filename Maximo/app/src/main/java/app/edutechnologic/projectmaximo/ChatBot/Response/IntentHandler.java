package app.edutechnologic.projectmaximo.ChatBot.Response;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Class for generating response to a user's intent.
 * Used with the chat bot.
 */
public class IntentHandler {
    private static ActionPovider actionProvider;

    // TODO: Set a default intent handler

    // Process intent handling capabilities
    public static String handleIntent(MessageResponse response) {
        if (response.getIntents().contains("FetchWorkItems")) {
            return "Work item1: ... // TODO: implement this";
        }
        // If is work items
            // Fetch from the DB and return the work items.
        return response.getText().get(0);
        //throw new NotImplementedException();
    }

    // This would allow the pogram to execute an action provider through setting.
    // SetActionProviderr

    public static ActionPovider getActionProvider() {
        return actionProvider;
    }

    public static void setActionProvider(ActionPovider actionProvider) {
        IntentHandler.actionProvider = actionProvider;
    }
}

