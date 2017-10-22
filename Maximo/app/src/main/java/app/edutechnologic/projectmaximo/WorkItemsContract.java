package app.edutechnologic.projectmaximo;

import android.provider.BaseColumns;

/**
 * Created by momoliu on 10/4/17.
 */

public class WorkItemsContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private WorkItemsContract() {}

    public static class WorkItemsEntry implements BaseColumns {
        public static final String COLUMN_NAME_ITEM = "work_items";
        public static final String TABLE_NAME = "WorkItems";

    }
}
