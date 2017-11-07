package app.edutechnologic.projectmaximo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alex on 11/5/2017.
 * Activity for viewing tasks for a work order.
 */

public class WorkOrderTasksActivity extends AppCompatActivity {

    //Array Lists for work order task information
    private TableLayout tableLayout;
    private final ArrayList<String> task_numbers = new ArrayList<>();
    private final ArrayList<String> task_summaries = new ArrayList<>();
    private final ArrayList<String> task_statuses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_order_tasks);

        tableLayout = findViewById(R.id.taskTable);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String workOrderNumber = intent.getStringExtra("num");
        //Set the text at the top of the page
        TextView header = findViewById(R.id.workOrderTasksHeader);
        header.setText(getString(R.string.work_order_number, workOrderNumber));

        // Gets the data repository in write mode
        WorkOrderTaskDbHelper DbHelper = new WorkOrderTaskDbHelper(getApplicationContext());
        SQLiteDatabase dbWriteable = DbHelper.getWritableDatabase();

        //Create a few work order task entries

        //First Entry values
        ContentValues values = new ContentValues();
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_WO_NUMBER, "1214");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_NUMBER, "10");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_SUMMARY, "Check pump operation.");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_STATUS, "WSCH");
        // Insert the new row, returning the primary key value of the new row
        long newRowId = dbWriteable.insert(WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME, null, values);

        //Second Entry values
        values = new ContentValues();
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_WO_NUMBER, "1214");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_NUMBER, "20");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_SUMMARY, "Check pump float switch.");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_STATUS, "WSCH");
        // Insert the new row, returning the primary key value of the new row
        newRowId = dbWriteable.insert(WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME, null, values);

        //Third Entry values
        values = new ContentValues();
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_WO_NUMBER, "1214");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_NUMBER, "30");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_SUMMARY, "Check housing for leaks.");
        values.put(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_STATUS, "WSCH");
        // Insert the new row, returning the primary key value of the new row
        newRowId = dbWriteable.insert(WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME, null, values);

        /*
         * Define a projection that specifies which columns from the database
         * you will actually use after this query.
        */
        String[] projection = {
                WorkOrderContract.WorkOrderEntry._ID,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_NUMBER,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_DESCRIPTION,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_ASSETNUMBER,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_LOCATION,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_REPORTEDDATE,
                WorkOrderContract.WorkOrderEntry.COLUMN_NAME_STATUS
        };

        //readable version of the database
        SQLiteDatabase dbReadable = DbHelper.getReadableDatabase();

        // read tasks for the selected work order
         Cursor cursor = dbReadable.rawQuery("select * from " + WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME + " where " + WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_WO_NUMBER + " = " + workOrderNumber, null);

        while (cursor.moveToNext()) {
            String task_number = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_NUMBER));
            task_numbers.add(task_number);

            String task_summary = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_SUMMARY));
            task_summaries.add(task_summary);

            String task_status = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_STATUS));
            task_statuses.add(task_status);
        }
        cursor.close();

        //add table rows for each work order task
        TableRow[] tableRow = new TableRow[task_numbers.size()];
        TextView[] textView = new TextView[3];
        for (int i = 0; i < task_numbers.size(); i++) {
            tableRow[i] = new TableRow(this);
            tableRow[i].setBackgroundResource(R.drawable.border2);

            // set text for columns
            textView[0] = new TextView(this);
            textView[0].setText(task_numbers.get(i));
            textView[0].setGravity(Gravity.CENTER);
            textView[0].setTextColor(Color.BLACK);

            textView[1] = new TextView(this);
            textView[1].setText(task_summaries.get(i));
            textView[1].setGravity(Gravity.CENTER);
            textView[1].setTextColor(Color.BLACK);

            textView[2] = new TextView(this);
            textView[2].setText(task_statuses.get(i));
            textView[2].setGravity(Gravity.CENTER);
            textView[2].setTextColor(Color.BLACK);

            // add columns in the current row
            for (int j = 0; j < 3; j++) {
                tableRow[i].addView(textView[j], new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f));
            }

            // add current in the table
            tableLayout.addView(tableRow[i]);
        }

        //back button functionality
        final Button backButton = findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //return to the work orders dashboard
                Intent intent = new Intent(v.getContext(), WorkOrdersActivity.class);
                startActivity(intent);
            }
        });

        //bottom navbar menu button functionality
        final Button chatButton = findViewById(R.id.chat_nav_btn);
        chatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button workOrdersButton = findViewById(R.id.work_orders_nav_btn);
        workOrdersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
        final Button homeButton = findViewById(R.id.home_nav_btn);
        homeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomMenuBar.menuClick(v);
            }
        });
    }
}
