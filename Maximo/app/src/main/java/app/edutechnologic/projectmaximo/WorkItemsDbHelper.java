package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by momoliu on 10/6/17.
 */

public class WorkItemsDbHelper extends SQLiteOpenHelper{
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WorkItemsDb.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkItemsContract.WorkItemsEntry.TABLE_NAME + " (" +
                    WorkItemsContract.WorkItemsEntry._ID + " INTEGER PRIMARY KEY," +
                    WorkItemsContract.WorkItemsEntry.COLUMN_NAME_ITEM + " TEXT)";

    // Use this to drop the table completely.
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkItemsContract.WorkItemsEntry.TABLE_NAME;

    /**
     * Generates the database with the mentioned name and version number
     *
     * @param  context the context from which the function was called
     */
    public WorkItemsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

     @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL(SQL_CREATE_ENTRIES);}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}
