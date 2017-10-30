package app.edutechnologic.projectmaximo.ChatBot.Response;

import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Class for generating response to a user's intent.
 * Used with the chat bot.
 */
public class IntentHandler {
    private static ActionPovider actionProvider;

    // Process intent handling capabilities
    public static String handleIntent(MessageResponse response) {
        throw new NotImplementedException();
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

