package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotHistoryContract;

public class ChatBotHistoryDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ChatBotHistory.db";

    public ChatBotHistoryDbHelper(Context context) {
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
            "CREATE TABLE " + ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME + " (" +
                    ChatBotHistoryContract.ChatBotHistoryEntry._ID + " INTEGER PRIMARY KEY," +
                    ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE + " INTEGER," +
                    ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE + " TEXT," +
                    ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP + " TEXT)";

    // This string is used to delete the columns in the database
    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME;
}