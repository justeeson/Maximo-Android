package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SensorGaugeReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "SensorGaugeReader.db";

    public SensorGaugeReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This function creates the necessary database
     *
     * @param db         the SQLiteDatabase to be created
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
     * This function upgrades the database to an older version number
     *
     * @param db         the SQLiteDatabase to be created
     * @param oldVersion the version number of the old database
     * @param newVersion the version number of the new database
     */
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    // This String specifies the SQL command to generate the necessary table
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SensorGaugeReaderContract.FeedEntry.TABLE_NAME + " (" +
                    SensorGaugeReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID + " INTEGER UNIQUE," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME + " TEXT UNIQUE," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORSTATUS + " INTEGER," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORTOTALVALUE + " INTEGER," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORACTUALVALUE + " INTEGER)";

    // This String specifies the SQL command to delete the table
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SensorGaugeReaderContract.FeedEntry.TABLE_NAME;
}