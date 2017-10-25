package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static app.edutechnologic.projectmaximo.R.id.dashboard_nav_btn;

public class DashboardSettingsActivity extends Maximo {

    ArrayList<String> changes = new ArrayList<String>();
    ArrayList<String> previous_settings = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_settings);

        final CheckBox intro_pane_checkbox = (CheckBox) findViewById(R.id.introPaneCheckbox);

        setCheckboxAtStart();

        /**
         * check the status of each dashboard checkbox
         * if checked, it would be displayed
         * if not, set it as invisiable
         */

        //introduction pane check box
        intro_pane_checkbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    changes.add("introTextView");
                } else {
                    changes.remove("introTextView");

                }
            }
        });

        // save changes by clicking save btn
        // store the changes in internal storage so it keeps the change next time while running
        final Button save = findViewById(R.id.SaveDashboardSettingBtn);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saveToFile();
                //LinearLayout intro_pane = (LinearLayout) findViewById(R.id.intro);
                TextView intro_pane = (TextView) findViewById((R.id.introTextView2));
                if (changes.contains("introTextView")) {
                    intro_pane.setVisibility(View.VISIBLE);
                } else {
                    intro_pane.setVisibility(View.GONE);
                }

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
        CheckBox intro_pane_checkbox = (CheckBox) findViewById(R.id.introPaneCheckbox);
        CheckBox bulletin_checkbox = (CheckBox) findViewById(R.id.bulletinCheckbox);
        CheckBox sensor_gauge_checkbox = (CheckBox) findViewById(R.id.sensorGaugeCheckbox);
        CheckBox status_checkbox = (CheckBox) findViewById(R.id.statusCheckbox);
        CheckBox work_item_checkbox = (CheckBox) findViewById(R.id.workItemCheckbox);
        File file = new File(getFilesDir(), "dashboardSetting.txt");

        //file.delete();
        if (!file.exists()) {
            previous_settings.clear();
            previous_settings.add("introTextView");
//            previous_settings.add("");
//            previous_settings.add("");
//            previous_settings.add("");
//            previous_settings.add("");

        } else {
            readFromFile();
            Toast.makeText(this, "File exists already.", Toast.LENGTH_SHORT).show();
        }

        TextView intro_pane = (TextView) findViewById((R.id.introTextView2));
        if (previous_settings.contains("introTextView")) {
            intro_pane.setVisibility(View.VISIBLE);
            intro_pane_checkbox.setChecked(true);
            Toast.makeText(this, "introduction Pane Show.", Toast.LENGTH_SHORT).show();
        } else {
            intro_pane.setVisibility(View.GONE);
            intro_pane_checkbox.setChecked(false);
        }

    }

    /**
     * save dashboard settings change
     *
     * @return true  if saved successfully
     * false  if changes not saved
     */
    public boolean saveToFile() {

        String FILENAME = "dashboardSetting.txt";
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);

            for (String str : changes) {
                str = str + "\n";
                fos.write(str.getBytes());
            }
            fos.close();

            Toast.makeText(this, "Changes saved.", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, sLine, Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



