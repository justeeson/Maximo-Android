package app.edutechnologic.projectmaximo;

import android.provider.BaseColumns;

final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {
    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "UsersDB";
        public static final String COLUMN_NAME_USERID = "users";
        public static final String COLUMN_NAME_USERNAME = "usernames";
        public static final String COLUMN_NAME_FIRSTNAME = "firstnames";
        public static final String COLUMN_NAME_LASTNAME = "lastnames";
    }
}
