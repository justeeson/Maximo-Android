package app.edutechnologic.projectmaximo.ChatBot.Response;

import com.ibm.watson.developer_cloud.conversation.v1.model.Intent;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;

import java.util.ArrayList;
import java.util.List;

import app.edutechnologic.projectmaximo.Maximo;

/**
 * Class for generating response to a user's intent.
 * Used with the chat bot.
 */
public class IntentHandler {
    private static ActionPovider actionProvider;

    // TODO: Set a default intent handler

    // Process intent handling capabilities
    public static String handleIntent(MessageResponse response) {
        for (Intent intent : response.getIntents()) {
            if (intent.getIntent().equals("FetchWorkOrders")) {
                // FIXME: This is now broken.
//                ArrayList<WorkItem> workItems = Maximo.workitem_list;
//                if (workItems != null) return "You have " + workItems.size() + " work orders.";
                return "You have 0 work items" ;
            }
        }
        // If is work items
        // Fetch from the DB and return the work items.
        List<Intent> intents = response.getIntents();
        if (intents.size() > 0) {
            return intents.get(0).getIntent();
        }
        return "Called handleIntent";
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

