package app.edutechnologic.projectmaximo.ChatBot;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotHistoryContract;
import app.edutechnologic.projectmaximo.FeedReaderContract;
import app.edutechnologic.projectmaximo.R;

public class ChatBotConversationHistory{
    private static List<ChatMessage> itemIds;

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

        itemIds = new ArrayList<ChatMessage>();
        while (cursor.moveToNext()) {

            ChatMessage message = new ChatMessage(null);
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_MESSAGE));
            message.setMessage(itemId);

            String messageType = cursor.getString(
                    cursor.getColumnIndexOrThrow(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_USERTYPE));
            if(messageType.equals("bot")){
                message.setIsResponse(true);
            }
            else if(messageType.equals("user")){
                message.setIsResponse(false);
            }

            long messageTimeStamp = cursor.getLong(cursor.getColumnIndexOrThrow(ChatBotHistoryContract.ChatBotHistoryEntry.COLUMN_NAME_TIMESTAMP));
            message.setTime(messageTimeStamp);

            itemIds.add(message);
        }
        cursor.close();

        for(ChatMessage message : itemIds){
            final String messageAsString = message.getMessage();
            LinearLayout convoHistory = ChatBotActivity.appActivity.findViewById(R.id.chat_message_history);
            ChatMessageView view = new ChatMessageView(ChatBotActivity.appContext, message);

            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ChatBotHandler.textToSpeech(messageAsString);
                }
            });
            convoHistory.addView(view);
        }
    }

}