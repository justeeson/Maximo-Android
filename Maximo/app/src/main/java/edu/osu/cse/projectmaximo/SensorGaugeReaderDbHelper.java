package edu.osu.cse.projectmaximo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.osu.cse.projectmaximo.FeedReaderContract;


public class SensorGaugeReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SensorGaugeReader.db";

    public SensorGaugeReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SensorGaugeReaderContract.FeedEntry.TABLE_NAME + " (" +
                    SensorGaugeReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORID + " INTEGER UNIQUE," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_SENSORNAME + " TEXT UNIQUE," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_STATUS + " TEXT," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_TOTALVALUE + " TEXT," +
                    SensorGaugeReaderContract.FeedEntry.COLUMN_NAME_ACTUALVALUE + " TEXT)";
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SensorGaugeReaderContract.FeedEntry.TABLE_NAME;
}