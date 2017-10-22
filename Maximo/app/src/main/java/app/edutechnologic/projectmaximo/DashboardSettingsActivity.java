package app.edutechnologic.projectmaximo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class DashboardSettingsActivity extends AppCompatActivity {

    ArrayList<String> selectionForDashboard = new ArrayList<String>();
    TextView final_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_settings);
        final_text = (TextView)findViewById(R.id.selectionForDashboard);
        final_text.setEnabled(false);

        //bottom navbar menu button functionality
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button mediaButton = findViewById(R.id.dashboard_nav_btn);
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

    /**
     * */
    public void selectItem(View view){
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.mediaDashboardCheckbox:
                if(checked){
                    selectionForDashboard.add("Media Dashboard");
                }else{
                    selectionForDashboard.remove("Media Dashboard");
                }
                break;
            case R.id.chatBotCheckbox:
                if(checked){
                    selectionForDashboard.add("Chat Bot");
                }else{
                    selectionForDashboard.remove("Chat Bot");
                }
                break;
        }


    }

    public void finalSelection(View view){
        String final_dashboard_selection = "";
        for (String str:selectionForDashboard){
            final_dashboard_selection += str + "\n";
        }

        final_text.setText(final_dashboard_selection);
        final_text.setEnabled(true);

    }
}
