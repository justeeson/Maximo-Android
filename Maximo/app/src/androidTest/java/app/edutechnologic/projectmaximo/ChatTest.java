package app.edutechnologic.projectmaximo;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotHandler;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class ChatTest {

    @Test
    public void sendMessageTest() throws Exception {
        ChatBotHandler.initialize();
        assertNotNull(ChatBotHandler.sendMessage("Hello"));
    }
}
