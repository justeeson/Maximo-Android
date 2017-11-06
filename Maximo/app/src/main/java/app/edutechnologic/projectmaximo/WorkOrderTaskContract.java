package app.edutechnologic.projectmaximo;

import android.provider.BaseColumns;

/**
 * Created by Alex on 11/5/2017.
 * Contract for the work order tasks database table.
 */

public final class WorkOrderTaskContract {
    /*
    To prevent someone from accidentally instantiating the contract class, make the
    constructor private.
    */
    private WorkOrderTaskContract() {}

    //Inner class that defines the table contents
    public static class WorkOrderTaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "WorkOrderTasksDB";
        public static final String COLUMN_NAME_WO_NUMBER = "workordernumbers";
        public static final String COLUMN_NAME_NUMBER = "numbers";
        public static final String COLUMN_NAME_SUMMARY = "summaries";
        public static final String COLUMN_NAME_STATUS = "statuses";
    }
}
