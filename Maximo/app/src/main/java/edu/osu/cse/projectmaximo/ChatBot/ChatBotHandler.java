package edu.osu.cse.projectmaximo.ChatBot;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.ibm.mobilefirstplatform.clientsdk.android.analytics.api.Analytics;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.BMSClient;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.Response;
import com.ibm.mobilefirstplatform.clientsdk.android.core.api.ResponseListener;
import com.ibm.mobilefirstplatform.clientsdk.android.logger.api.Logger;
import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneHelper;
import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneInputStream;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.android.library.audio.utils.ContentType;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.osu.cse.projectmaximo.WatsonMessage;


public class ChatBotHandler{

    private static ConversationService service;
    private static String username;
    private static String password;
    private static String TTS_username;
    private static String TTS_password;
    private static String workspaceId;
    private static TextToSpeech textToSpeech;
    private static Map<String,Object> contextMap;
    private static RecyclerView recyclerView;
    private static StreamPlayer streamPlayer;
    private static WatsonMessage responseFromWatson;
    private static String responseFromWatsonAsString;

    static {
        initialize();
    }

    private static void initialize(){
        username = "633ae577-2173-451d-b54f-aebf71c1c97a";
        password = "LTshbwXUFevy";
        TTS_username = "050f005d-938d-4dd0-86db-ace397b9f839";
        TTS_password = "CIvPNzSqYdMo";
        workspaceId = "914d9594-adee-472e-90c4-c987f2d489be";
        service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
        service.setUsernameAndPassword(username, password);
        contextMap = new HashMap<>();
        responseFromWatson = new WatsonMessage();

    }

    public static String sendMessage(String messageToWatson){
        final String messagedToBePassed = messageToWatson;
        responseFromWatson.setWatsonMessage(messagedToBePassed);

        new Thread(new Runnable() {
            @Override public void run() {
                try {
                        String replyFromWatson = "";
                        // Build and send a message to the Watson API
                        MessageRequest newMessage = new MessageRequest.Builder()
                                .inputText(messagedToBePassed)
                                .context(contextMap)
                                .build();

                        MessageResponse response = service
                                .message(workspaceId, newMessage)
                                .execute();

                        System.out.println(response);

                        // Obtain response from Watson API
                        ArrayList responseList = (ArrayList) response.getOutput().get("text");
                        if (null != responseList && responseList.size() > 0) {
                            replyFromWatson = ((String) responseList.get(0));
                        }
                        responseFromWatson.setWatsonMessage(replyFromWatson);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        responseFromWatsonAsString = responseFromWatson.getMessageAsString();
        return responseFromWatsonAsString;
    }

    public static void textToSpeech(String messageToWatson){
        final String message = messageToWatson;
        // Initialize necessary variables
        textToSpeech = new TextToSpeech();
        textToSpeech.setUsernameAndPassword(TTS_username, TTS_password);

        // Run the stream player on a separate thread to prevent resource locking
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    streamPlayer = new StreamPlayer();
                    streamPlayer.playStream(textToSpeech.synthesize(message, Voice.EN_LISA).execute());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void speechToText()
    {
        //TODO:
    }
}








