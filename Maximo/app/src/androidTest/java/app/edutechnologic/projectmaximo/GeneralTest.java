package app.edutechnologic.projectmaximo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeneralTest {

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("edu.osu.cse.app.edutechnologic.projectmaximo", appContext.getPackageName());
    }
}
