package app.edutechnologic.projectmaximo;

import android.content.Intent;
import android.view.View;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotActivity;

/**
 * Created by Alex on 10/12/2017.
 * Bottom navigation bar for each dashboard.
 */

public class BottomMenuBar{

    /**
     * Handles click events for the bottom navigation bar.
     * @param v the pressed navbar button
     */
    public static void menuClick(View v) {
        // Find which button was clicked. Call appropriate activity
        switch (v.getId()) {
            case R.id.home_nav_btn: // Home
                v.getContext().startActivity(new Intent(v.getContext(), Maximo.class));
                break;
            case R.id.dashboard_nav_btn: // Media Assets
                v.getContext().startActivity(new Intent(v.getContext(), MediaDashboardActivity.class));
                break;
            case R.id.chat_nav_btn: // Chat
                v.getContext().startActivity(new Intent(v.getContext(), ChatBotActivity.class));
                break;
            case R.id.work_orders_nav_btn: //Work Orders
                v.getContext().startActivity(new Intent(v.getContext(), WorkOrdersActivity.class));
            default:
                break;
        }
    }

}
