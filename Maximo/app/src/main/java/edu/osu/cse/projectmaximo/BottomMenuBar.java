package edu.osu.cse.projectmaximo;

import android.content.Intent;
import android.view.View;

import edu.osu.cse.projectmaximo.ChatBot.ChatBotActivity;

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
            case R.id.home_button: // Home
                v.getContext().startActivity(new Intent(v.getContext(), Maximo.class));
                break;
            case R.id.media_dashboard_btn: // Media
                v.getContext().startActivity(new Intent(v.getContext(), MediaDashboardActivity.class));
                break;
            case R.id.chat_btn: // Chat
                v.getContext().startActivity(new Intent(v.getContext(), ChatBotActivity.class));
                break;
            default:
                break;
        }
    }

}
