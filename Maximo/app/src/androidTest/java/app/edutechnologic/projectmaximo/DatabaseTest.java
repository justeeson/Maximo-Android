package app.edutechnologic.projectmaximo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    @Test
    public void checkDatabaseCreation() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(appContext);
        SensorGaugeReaderDbHelper sDbHelper = new SensorGaugeReaderDbHelper(appContext);
        WorkOrderDbHelper wDbHelper = new WorkOrderDbHelper(appContext);
        assertNotNull(mDbHelper);
        assertNotNull(sDbHelper);
        assertNotNull(wDbHelper);
    }

    @Test
    public void databaseInsertionCheck() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(appContext);
        SQLiteDatabase dbWriteable = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERID, "1");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME, "userone");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME, "Mike");
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME, "Rowsoft");

        dbWriteable.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);

        SQLiteDatabase dbReadable = mDbHelper.getReadableDatabase();

        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME + " = ?";
        String[] selectionArgs = { "Mike" };

        // How you want the results sorted in the resulting Cursor
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

        // Read in the rows with the cursor
        List<String> itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME));
            itemIds.add(itemId);
        }
        cursor.close();
        assertEquals("Mike", itemIds.get(0));
    }
}
