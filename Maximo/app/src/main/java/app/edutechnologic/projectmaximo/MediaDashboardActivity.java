package app.edutechnologic.projectmaximo;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MediaDashboardActivity extends AppCompatActivity {

    private ListView media_assets_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_dashboard);

        //bottom navbar menu button functionality
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button chatButton = findViewById(R.id.chat_nav_btn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });

        populateMediaAssetsList();
    }

    /**
     * This function populates the list with the required media assets
     */
    public void populateMediaAssetsList(){
        // get files' names from asset directory
        // if the file is empty, an alert message would display
        String[] list, final_media_assets = {"No available media asset!"};
        ArrayList<String> media_assets = new ArrayList<String>();
        ArrayList<String> filetype = new ArrayList<String>();
        ArrayList<String> size = new ArrayList<String>();
        try {
            AssetManager assetManager = getAssets();
            list = assetManager.list("");
            // remove all the built-in files in Android, only show user's own assets
            if (list.length > 0) {
                for(int i = 0; i < list.length; i++){
                    if(!list[i].equals("images") && !list[i].equals("sounds") && !list[i].equals("webkit")){
                        //put filename in {media_assets}
                        String file = list[i];
                        media_assets.add(list[i].substring(0, list[i].indexOf('.')));
                        //put filetype in {filetype}
                        filetype.add(list[i].substring(list[i].indexOf('.')+1,list[i].length()));
                    }
                }
                final_media_assets = media_assets.toArray(new String[media_assets.size()]);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // display media asset files name on screen
        ArrayAdapter<String> media_adapter = new ArrayAdapter<String>(this, R.layout.media_assets_list, final_media_assets);
        media_assets_list = (ListView)findViewById(R.id.media_list);
        media_assets_list.setAdapter(media_adapter);
    }
}
