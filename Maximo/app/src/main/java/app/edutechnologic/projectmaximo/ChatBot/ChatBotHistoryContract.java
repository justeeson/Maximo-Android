package app.edutechnologic.projectmaximo.ChatBot;

import android.provider.BaseColumns;

final class ChatBotHistoryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private ChatBotHistoryContract() {}

    /* Inner class that defines the table contents */
    public static class ChatBotHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "ChatBotHistoryDB";
        public static final String COLUMN_NAME_USERTYPE = "usertype";
        public static final String COLUMN_NAME_MESSAGE = "message";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";
    }
}
