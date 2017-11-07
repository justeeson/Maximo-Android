package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 11/5/2017.
 * Helper class for the Work Order Task DB
 */

public class WorkOrderTaskDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "WorkOrderTask.db";

    public WorkOrderTaskDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This function creates the SQLite database
     *
     * @param db the SQLiteDatabase to be created
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     * This function upgrades the database to a newer version number
     *
     * @param db         the SQLiteDatabase to be created
     * @param oldVersion the version number of the old database
     * @param newVersion the version number of the new database
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * This function downgrades the database to an older version number
     *
     * @param db         the SQLiteDatabase to be created
     * @param oldVersion the version number of the old database
     * @param newVersion the version number of the new database
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // This string is used to create the columns in the database
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME + " (" +
                    WorkOrderTaskContract.WorkOrderTaskEntry._ID + " INTEGER PRIMARY KEY," +
                    WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_WO_NUMBER + " INTEGER," +
                    WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_NUMBER + " INTEGER," +
                    WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_SUMMARY + " TEXT," +
                    WorkOrderTaskContract.WorkOrderTaskEntry.COLUMN_NAME_STATUS + " TEXT)";

    // This string is used to delete the columns in the database
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WorkOrderTaskContract.WorkOrderTaskEntry.TABLE_NAME;
}
