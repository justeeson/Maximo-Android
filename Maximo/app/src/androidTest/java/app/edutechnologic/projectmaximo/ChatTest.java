package app.edutechnologic.projectmaximo;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.Button;

import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotHandler;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ChatTest {

    @Test
    public void sendMessageTest() throws Exception {
        MaximoUtility.initialize();
        ChatBotHandler.initialize();
        assertNotNull(ChatBotHandler.sendMessage("Hello"));
    }
}
