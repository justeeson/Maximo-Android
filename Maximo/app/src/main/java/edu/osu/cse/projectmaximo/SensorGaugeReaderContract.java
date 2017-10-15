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
        public static final String COLUMN_NAME_SENSORSTATUS = "sensorstatuses";
        public static final String COLUMN_NAME_SENSORTOTALVALUE = "sensortotalvalues";
        public static final String COLUMN_NAME_SENSORACTUALVALUE = "sensoractualvalues";
    }
}
