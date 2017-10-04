package edu.osu.cse.projectmaximo;

import android.provider.BaseColumns;

public final class SensorGaugeReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private SensorGaugeReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "SensorsDB";
        public static final String COLUMN_NAME_SENSORID = "sensors";
        public static final String COLUMN_NAME_SENSORNAME = "sensornames";
        public static final String COLUMN_NAME_STATUS = "sensorstatuses";
        public static final String COLUMN_NAME_TOTALVALUE = "sensortotalvalues";
        public static final String COLUMN_NAME_ACTUALVALUE = "sensoractualvalues";
    }
}
