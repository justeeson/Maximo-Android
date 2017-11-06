package app.edutechnologic.projectmaximo;

import android.provider.BaseColumns;

/**
 * Created by Alex on 10/26/2017.
 * Contract for the work orders database table.
 */

final class WorkOrderContract {
    /*
    To prevent someone from accidentally instantiating the contract class, make the
    constructor private.
     */
    private WorkOrderContract() {
    }

    //Inner class that defines the table contents
    public static class WorkOrderEntry implements BaseColumns {
        public static final String TABLE_NAME = "WorkOrdersDB";
        public static final String COLUMN_NAME_NUMBER = "numbers";
        public static final String COLUMN_NAME_DESCRIPTION = "descriptions";
        public static final String COLUMN_NAME_ASSETNUMBER = "assetnumbers";
        public static final String COLUMN_NAME_LOCATION = "locations";
        public static final String COLUMN_NAME_REPORTEDDATE = "reporteddates";
        public static final String COLUMN_NAME_STATUS = "statuses";
    }
}
