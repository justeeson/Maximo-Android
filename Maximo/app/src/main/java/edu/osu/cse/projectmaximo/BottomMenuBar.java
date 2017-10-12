package edu.osu.cse.projectmaximo;

import android.content.Intent;
import android.view.View;

/**
 * Created by Alex on 10/12/2017.
 * Bottom navigation bar for each dashboard.
 */

public class BottomMenuBar extends Maximo implements View.OnClickListener{

    /**
     * Handles click events for the bottom navigation bar.
     * @param v the pressed navbar button
     */
    @Override
    public void onClick(View v) {
        // Find which button was clicked. Call appropriate activity
        switch (v.getId()) {
            case R.id.home_button: // Home
                startActivity(new Intent(this, Maximo.class));
                break;
            case R.id.media_dashboard_btn: // Media
                startActivity(new Intent(this, MediaDashboardActivity.class));
                break;
            case R.id.chat_btn: // Chat
                startActivity(new Intent(this, MainActivity.class));
                break;
            default:
                break;
        }
    }

}
