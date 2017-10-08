package edu.osu.cse.projectmaximo;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Maximo extends AppCompatActivity {
    public static String userIdentity = "";

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        SensorGaugeReaderDbHelper sDbHelper = new SensorGaugeReaderDbHelper(getApplicationContext());
        WorkItemsDbHelper wDbHelper = new WorkItemsDbHelper(getApplicationContext());

        // Gets the data repository in write mode
        SQLiteDatabase dbWriteable = mDbHelper.getWritableDatabase();
        SQLiteDatabase sdbWriteable = sDbHelper.getWritableDatabase();
        SQLiteDatabase wdbWriteable = wDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERID, "1");
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, "userone");
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME, "Mike");
//        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME, "Rowsoft");
//
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId = dbWriteable.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
//
//        //Sensor Gauge Sensor 1
//        values = new ContentValues();
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "1");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 1");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "500");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "325");
//
//        newRowId = dbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);
//
//
//        //Sensor Gauge Sensor 2
//        values = new ContentValues();
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "2");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 2");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "700");
//        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "405");
//
//        newRowId = dbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);

        // Work Items
        values = new ContentValues();
        values.put(WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM, "item1");

        long newRowId = wdbWriteable.insert(WorkItemsContract.WorkItemsEntry.TABLE_NAME, null, values);


        //Get Readable versions of both databases
        SQLiteDatabase dbReadable = mDbHelper.getReadableDatabase();
        SQLiteDatabase sdbReadable = sDbHelper.getReadableDatabase();
        SQLiteDatabase wdbReadable = wDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
//        String[] projection = {
//                FeedReaderContract.FeedEntry._ID,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME,
//                FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME
//        };
//
//        // Filter results WHERE "title" = 'My Title'
//        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME + " = ?";
//        String[] selectionArgs = {"User"};
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID + " ASC";
//
//        Cursor cursor = dbReadable.query(
//                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
//                projection,                               // The columns to return
//                selection,                                // The columns for the WHERE clause
//                selectionArgs,                            // The values for the WHERE clause
//                null,                                     // don't group the rows
//                null,                                     // don't filter by row groups
//                sortOrder                                 // The sort order
//        );
//
//        // Read in the rows with the cursor
//        List<String> itemIds = new ArrayList<String>();
//        while (cursor.moveToNext()) {
//            String itemId = cursor.getString(
//                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME));
//            itemIds.add(itemId);
//        }
//        cursor.close();
//        userIdentity = " " + itemIds.get(0);

        //read work items
        String[] workItems_projection = {
                WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM
        };
        String selection = WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM + " = ?";
       // String[] workitem_selectionArgs = {"Current Items"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM + " DESC";

        Cursor cursor = wdbReadable.query(
                WorkItemsContract.WorkItemsEntry.TABLE_NAME,                     // The table to query
                workItems_projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                null,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        List workitem_list = new ArrayList<>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(WorkItemsContract.WorkItemsEntry._ID));
            workitem_list.add(itemId);
        }
        cursor.close();
        //Log.i("work item read",workitem_list.toString());
        Log.i("work item read",workitem_list.toString());

        // We're calling this last so the name can be pulled before
        // the screen is created
        setContentView(R.layout.activity_maximo);

        //set intent
        Button media_dash = (Button) findViewById(R.id.MediaDashboardBtn);
        media_dash.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View view) {

                        Intent intent_to_media = new Intent(view.getContext(), MediaDashboardActivity.class);
                        startActivity(intent_to_media);
                    }
                }
        );
        //displayWorkItems();

    }

    public void displayWorkItems() {
        //get work items list
        String[] work_items = {"item1", "item2"};
        ArrayAdapter<String> work_item_adapter = new ArrayAdapter<String>(this, R.layout.activity_maximo, work_items);
        /////////// cannot find work_item_list
        ListView work_item_list = (ListView) findViewById(R.id.WorkItemList);
        work_item_list.setAdapter(work_item_adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maximo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
