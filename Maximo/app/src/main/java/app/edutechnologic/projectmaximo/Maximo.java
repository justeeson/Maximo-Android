package app.edutechnologic.projectmaximo;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Maximo extends AppCompatActivity {
    public static String userIdentity = "";
    public static ArrayList<String> sensorGaugeNames;
    public static ArrayList<Integer> sensorGaugeOpStatuses;
    public static ArrayList<Integer> sensorGaugeTotalVals;
    public static ArrayList<Integer> sensorGaugeActualVals;

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

        //Initialize chat bot variables

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        SensorGaugeReaderDbHelper sDbHelper = new SensorGaugeReaderDbHelper(getApplicationContext());


        // Gets the data repository in write mode
        SQLiteDatabase dbWriteable = mDbHelper.getWritableDatabase();
        SQLiteDatabase sdbWriteable = sDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERID, "1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, "userone");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME, "Mike");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME, "Rowsoft");

        // Insert the new row
        dbWriteable.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        //Sensor Gauge Sensor 1
        values = new ContentValues();
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "1");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 1");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORSTATUS, "1");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "500");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "435");

        sdbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);


        //Sensor Gauge Sensor 2
        values = new ContentValues();
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID, "2");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME, "Sensor 2");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORSTATUS, "0");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE, "700");
        values.put(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE, "0");

        sdbWriteable.insert(SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null, values);

        /*
         * Get Readable versions of databases
         * */
        SQLiteDatabase dbReadable = mDbHelper.getReadableDatabase();
        SQLiteDatabase sdbReadable = sDbHelper.getReadableDatabase();


        /*
         * Define a projection that specifies which columns from the database
         * you will actually use after this query.
         */

        //User table
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME
        };

        /*
         Filter results WHERE "title" = 'My Title'
          */
        //Look for users with first name = Mike
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME + " = ?";
        String[] selectionArgs = {"Mike"};

        // How you want the results sorted in the resulting user cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID + " ASC";
        Cursor cursor = dbReadable.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        // Read in the rows with the user cursor
        List<String> itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME));
            itemIds.add(itemId);
        }
        cursor.close();

        //store the user's name (Mike) in userIdentity
        userIdentity = " " + itemIds.get(0);

        //sensor gauge cursor
        Cursor sensorGaugeCursor = sdbReadable.rawQuery("select * from " + SensorGaugeReaderContract.FeedEntry.TABLE_NAME, null);

        // Read in the rows with the sensor gauge cursor
        sensorGaugeNames = new ArrayList<>();
        sensorGaugeOpStatuses = new ArrayList<>();
        sensorGaugeTotalVals = new ArrayList<>();
        sensorGaugeActualVals = new ArrayList<>();
        while (sensorGaugeCursor.moveToNext()) {
            //Add in sensor names, operational status, total value, and actual values to
            //appropriate list
            String sensorGaugeName = sensorGaugeCursor.getString(
                    sensorGaugeCursor.getColumnIndexOrThrow(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME));
            sensorGaugeNames.add(sensorGaugeName);
            Integer sensorGaugeOpStatus = sensorGaugeCursor.getInt(
                    sensorGaugeCursor.getColumnIndexOrThrow(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORSTATUS));
            sensorGaugeOpStatuses.add(sensorGaugeOpStatus);
            Integer sensorGaugeTotalVal = sensorGaugeCursor.getInt(
                    sensorGaugeCursor.getColumnIndexOrThrow(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE));
            sensorGaugeTotalVals.add(sensorGaugeTotalVal);
            Integer sensorGaugeActualVal = sensorGaugeCursor.getInt(
                    sensorGaugeCursor.getColumnIndexOrThrow(SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE));
            sensorGaugeActualVals.add(sensorGaugeActualVal);
        }
        sensorGaugeCursor.close();

        // We're calling this last so the name can be pulled before
        // the screen is created

        setContentView(R.layout.activity_maximo);

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
