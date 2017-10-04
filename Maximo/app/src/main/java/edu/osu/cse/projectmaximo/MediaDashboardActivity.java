package edu.osu.cse.projectmaximo;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.IOException;

public class MediaDashboardActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_media_dashboard);

        // get files' names from asset directory
        // if the file is empty, an alert message would display
        String[] list, media_asset = {"No available media asset!"};
        try {
            list = getAssets().list("");
            if (list.length > 0) {
                media_asset = list;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // display media asset files name on screen
        ArrayAdapter<String> media_adapter = new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, media_asset);
        getListView().setAdapter(media_adapter);
    }
}
