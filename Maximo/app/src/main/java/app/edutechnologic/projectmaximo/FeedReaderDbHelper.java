package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "FeedReader.db";

    public FeedReaderDbHelper(Context context) {
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
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_USERID + " INTEGER UNIQUE," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_USERNAME + " TEXT UNIQUE," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_FIRSTNAME + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_LASTNAME + " TEXT)";

    // This string is used to delete the columns in the database
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;
}