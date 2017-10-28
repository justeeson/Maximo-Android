package app.edutechnologic.projectmaximo.ChatBot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotHistoryContract;
import app.edutechnologic.projectmaximo.FeedReaderContract;

public class ChatBotConversationHistory{
    private SQLiteDatabase chatDbReadable;

    public static void fetchHistory(){
        SQLiteDatabase chatDbReadable = ChatBotActivity.chatDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ChatBotHistoryContract.ChatBotHistoryEntry._ID,
                ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE,
                ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE,
                ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP
        };

        Cursor cursor = chatDbReadable.rawQuery("select * from " + ChatBotHistoryContract.ChatBotHistoryEntry.TABLE_NAME + " order by " +
                ChatBotHistoryContract.ChatBotHistoryEntry._ID + " asc",null);

        List<String> itemIds = new ArrayList<String>();
        while (cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE));
            itemIds.add(itemId);
        }

        cursor.close();

        for(String s : itemIds){
            System.out.println(s);
        }

        cursor.close();
    }
}
