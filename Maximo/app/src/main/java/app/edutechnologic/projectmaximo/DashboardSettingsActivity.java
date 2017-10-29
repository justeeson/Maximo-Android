package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static app.edutechnologic.projectmaximo.R.id.dashboard_nav_btn;

public class DashboardSettingsActivity extends AppCompatActivity {

    static ArrayList<String> changes = new ArrayList<String>();
    static ArrayList<String> previous_settings = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_settings);

        final CheckBox inbox_checkbox = (CheckBox) findViewById(R.id.inboxCheckbox);
        final CheckBox bulletin_board_checkbox = (CheckBox) findViewById(R.id.bulletinCheckbox);
        final CheckBox gauge_checkbox = (CheckBox) findViewById(R.id.gaugeCheckbox);
        final CheckBox status_window_checkbox = (CheckBox) findViewById(R.id.statusWindowCheckbox);
        //final CheckBox work_orders_checkbox = (CheckBox) findViewById(R.id.work);

        setCheckboxAtStart();

        /**
         * check the status of each dashboard checkbox
         * if checked, it would be displayed
         * if not, set it as invisiable
         */

        //introduction pane check box
        inbox_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    changes.add("inbox");
                } else {
                    changes.remove("inbox");

                }
            }
        });


        //bulletin_board check box
        bulletin_board_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    changes.add("bulletin_board");
                } else {
                    changes.remove("bulletin_board");

                }
            }
        });

        //gauge check box
        gauge_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    changes.add("gauge");
                } else {
                    changes.remove("gauge");

                }
            }
        });

        //status_window  check box
        status_window_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    changes.add("status_window");
                } else {
                    changes.remove("status_window");

                }
            }
        });

//        //work_orders check box
//        work_orders_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (((CheckBox) v).isChecked()) {
//                    changes.add("work_orders");
//                } else {
//                    changes.remove("work_orders");
//
//                }
//            }
//        });


        // save changes by clicking save btn
        // store the changes in internal storage so it keeps the change next time while running
        final Button save = findViewById(R.id.SaveDashboardSettingBtn);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveToFile();
            }

        });


        //bottom navbar menu button functionality
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button mediaButton = findViewById(dashboard_nav_btn);
        mediaButton.setOnClickListener(new View.OnClickListener() {
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

    }

    public void setCheckboxAtStart() {
        /**
         * * Default setting is to display all dashboards
         * if user has made any change before,
         * follow the change to setup
         */
        CheckBox inbox_checkbox = (CheckBox) findViewById(R.id.inboxCheckbox);
        CheckBox bulletin_checkbox = (CheckBox) findViewById(R.id.bulletinCheckbox);
        CheckBox sensor_gauge_checkbox = (CheckBox) findViewById(R.id.gaugeCheckbox);
        CheckBox status_checkbox = (CheckBox) findViewById(R.id.statusWindowCheckbox);
        //CheckBox work_item_checkbox = (CheckBox) findViewById(R.id.workItemCheckbox);
        File file = new File(getFilesDir(), "dashboardSetting.txt");

        if (!file.exists()) {
            previous_settings.clear();
            previous_settings.add("inbox");
            previous_settings.add("bulletin_board");
            previous_settings.add("gauge");
            previous_settings.add("status_window");
            //previous_settings.add("inbox");

        } else {
            readFromFile();
            // set checked status for eadch checkbox
            if(previous_settings.contains("inbox")){
                inbox_checkbox.setChecked(true);
            }else{
                inbox_checkbox.setChecked(false);
            }

            if(previous_settings.contains("bulletin_board")){
                bulletin_checkbox.setChecked(true);
            }else{
                bulletin_checkbox.setChecked(false);
            }

            if(previous_settings.contains("gauge")){
                sensor_gauge_checkbox.setChecked(true);
            }else{
                sensor_gauge_checkbox.setChecked(false);
            }

            if(previous_settings.contains("status_window")){
                status_checkbox.setChecked(true);
            }else{
                status_checkbox.setChecked(false);
            }
        }



    }

    public static ArrayList<String> getData(){
        return DashboardSettingsActivity.changes;
    }
    /**
     * save dashboard settings change
     *
     * @return true  if saved successfully
     * false  if changes not saved
     */
    public boolean saveToFile() {
        // clear previous settings
        File file = new File(getFilesDir(), "dashboardSetting.txt");
        file.delete();
        if(file.exists()){
            Toast.makeText(this, "File exists already.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "File not exist.", Toast.LENGTH_SHORT).show();
        }


        try {
            FileOutputStream fos = openFileOutput("dashboardSetting.txt", Context.MODE_PRIVATE);

            for (String str : changes) {
                str = str + "\n";
                fos.write(str.getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    public void readFromFile() {
        String FILENAME = "dashboardSetting.txt";

        try {
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String sLine = null;
            String out = "";
            previous_settings.clear();
            while ((sLine = br.readLine()) != null) {
                previous_settings.add(sLine);
                // Toast.makeText(this, sLine, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



