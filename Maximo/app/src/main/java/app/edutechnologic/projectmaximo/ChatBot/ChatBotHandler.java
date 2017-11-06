package app.edutechnologic.projectmaximo.ChatBot;

import android.widget.EditText;
import android.widget.Toast;

import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneHelper;
import com.ibm.watson.developer_cloud.android.library.audio.MicrophoneInputStream;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.developer_cloud.android.library.audio.utils.ContentType;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechModel;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.RecognizeCallback;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import app.edutechnologic.projectmaximo.ChatBot.Response.IntentHandler;
import app.edutechnologic.projectmaximo.Maximo;
import app.edutechnologic.projectmaximo.MaximoUtility;
import app.edutechnologic.projectmaximo.R;
import app.edutechnologic.projectmaximo.SpeakerLabelsDiarization;


public class ChatBotHandler{

    private static ConversationService service;
    private static String username;
    private static String password;
    private static String TTS_username;
    private static String TTS_password;
    private static String STT_username;
    private static String STT_password;
    private static String workspaceId;
    private static boolean listening = false;
    private static Map<String,Object> contextMap;
    private static StreamPlayer streamPlayer;
    private static WatsonMessage responseFromWatson;
    private static SpeechToText speechToTextService;
    private static MicrophoneInputStream capture;
    private static TextToSpeech textToSpeechService;
    private static SpeakerLabelsDiarization.RecoTokens recoTokens;
    private static Map<String, String> headers = new HashMap<>();
    private static MicrophoneHelper microphoneHelper;
    private static EditText inputBox;


    /**
     * This function initializes the necessary variables
     */
    public static void initialize(){
        MaximoUtility utilityClass = new MaximoUtility();
        username = utilityClass.getConversationUsername();
        password = utilityClass.getConversationPassword();
        TTS_username = utilityClass.getTTSUsername();
        TTS_password = utilityClass.getTTSPassword();
        STT_username = utilityClass.getSTTUsername();
        STT_password = utilityClass.getSTTPassword();
        workspaceId = utilityClass.getWorkspaceID();

        service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
        service.setUsernameAndPassword(username, password);
        contextMap = new HashMap<>();
        responseFromWatson = new WatsonMessage();

        microphoneHelper = new MicrophoneHelper(ChatBotActivity.appActivity);

        speechToTextService = new SpeechToText();
        speechToTextService.setUsernameAndPassword(STT_username,  STT_password);
        speechToTextService.setDefaultHeaders(headers);
        Thread modelThread = new Thread(){
            public void run(){
                SpeechModel model = speechToTextService.getModel("en-US_BroadbandModel").execute();
            }
        };

        modelThread.start();

        textToSpeechService = new TextToSpeech();
        textToSpeechService.setUsernameAndPassword(TTS_username, TTS_password);
        headers.put("X-Watson-Learning-Opt-Out", "true");
    }

    /**
     * This function sends a string to the Watson API to get a text response
     * @param messageToWatson the string message that is being passed to Watson
     * @return                string response from Watson API
     */
    public static String sendMessage(String messageToWatson){
        final String messagedToBePassed = messageToWatson;
        responseFromWatson.setWatsonMessage("Sorry, the watson service is unavailable right now.");
        Thread networkThread = new Thread(){
            public void run(){
                try {
                    String replyFromWatson;
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
                        String localResponse = IntentHandler.handleIntent(response);
                        // FIXME: This is gross. Call a method to add something to chat.
                        responseFromWatson.setWatsonMessage(replyFromWatson + "\n" + localResponse);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        networkThread.start();
        try {
            networkThread.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }

        return responseFromWatson.getMessageAsString();
    }

    /**
     * This function sends a string to the Watson API to get a voice response
     * @param messageToWatson the string message that is being passed to Watson
     */
    public static void textToSpeech(String messageToWatson){
        final String message = messageToWatson;
        // Run the stream player on a separate thread to prevent resource locking
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    streamPlayer = new StreamPlayer();
                    streamPlayer.playStream(textToSpeechService.synthesize(message, Voice.EN_LISA).execute());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // Main logic for speech to text
    public static void speechToText(Boolean status, EditText messageBox)
    {
        inputBox = messageBox;
        listening = status;
        if(listening != true) {
            capture = microphoneHelper.getInputStream(true);
            new Thread(new Runnable() {
                @Override public void run() {
                    try {
                        speechToTextService.recognizeUsingWebSocket(capture, getRecognizeOptions(), new MicrophoneRecognizeDelegate());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            listening = true;
            Toast.makeText(ChatBotActivity.appContext,"Listening....Click to Stop", Toast.LENGTH_SHORT).show();
        } else {
            try {
                microphoneHelper.closeInputStream();
                listening = false;
                Toast.makeText(ChatBotActivity.appContext,"Stopped Listening....Click to Start", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // Method defining options for speech to text
    private static RecognizeOptions getRecognizeOptions() {
        return new RecognizeOptions.Builder()
                .continuous(true)
                .contentType(ContentType.OPUS.toString())
                .interimResults(true)
                .inactivityTimeout(2000)
                .speakerLabels(true)
                .build();
    }

    //Watson Speech to Text Methods.
    private static class MicrophoneRecognizeDelegate implements RecognizeCallback {
        @Override
        public void onTranscription(SpeechResults speechResults) {
            recoTokens = new SpeakerLabelsDiarization.RecoTokens();
            if(speechResults.getResults() != null && !speechResults.getResults().isEmpty()) {
                String text = speechResults.getResults().get(0).getAlternatives().get(0).getTranscript();
                if(text != null) {
                    ChatBotActivity.updateMessageBox(text);
                }
            }
        }

        @Override public void onConnected() {

        }

        @Override public void onError(Exception e) {
            e.printStackTrace();
        }

        @Override public void onDisconnected() {

        }

        @Override
        public void onInactivityTimeout(RuntimeException runtimeException) {

        }

        @Override
        public void onListening() {

        }

        @Override
        public void onTranscriptionComplete() {

        }
    }
    }










