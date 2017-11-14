package app.edutechnologic.projectmaximo.ChatBot;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.edutechnologic.projectmaximo.ChatBot.Response.IntentHandler;
import app.edutechnologic.projectmaximo.SpeakerLabelsDiarization;


public class ChatBotHandler {

    private static ConversationService service;
    private static Map<String, Object> contextMap;
    private static StreamPlayer streamPlayer;
    private static WatsonMessage responseFromWatson;
    private static SpeechToText speechToTextService;
    private static MicrophoneInputStream capture;
    private static TextToSpeech textToSpeechService;
    private static SpeakerLabelsDiarization.RecoTokens recoTokens;
    private static Map<String, String> headers = new HashMap<>();
    private static MicrophoneHelper microphoneHelper;
    private static EditText inputBox;
    private static Boolean listening;

    private static String conversation_username;
    private static String conversation_password;
    private static String TTS_username;
    private static String TTS_password;
    private static String STT_username;
    private static String STT_password;
    private static String workspace_id;


    /**
     * This function initializes the necessary variables
     */
    public static void initialize() {
        conversation_username = "190135b9-77f2-4547-a230-61e457edd715";
        conversation_password = "8fOXKNKfKx24";
        TTS_username = "1e66e0ee-2cb3-4c69-adde-069debe5f4be";
        TTS_password = "JJp2Jl3k64jb";
        STT_username = "3d0e419f-1196-4e17-a000-bfb9f86ca83b";
        STT_password = "YWNVcAVrGxzT";
        workspace_id = "63a97876-099c-4c11-ae5f-fefd2dbc7952";

        if(checkInternetConnection()) {
            service = new ConversationService(ConversationService.VERSION_DATE_2017_02_03);
            service.setUsernameAndPassword(conversation_username, conversation_password);
            if(service == null)
            {
                System.out.println("service is null");
            }

            contextMap = new HashMap<>();
            responseFromWatson = new WatsonMessage();

            microphoneHelper = new MicrophoneHelper(ChatBotActivity.getActivity());

            speechToTextService = new SpeechToText();
            speechToTextService.setUsernameAndPassword(STT_username, STT_password);
            speechToTextService.setDefaultHeaders(headers);

            Thread modelThread = new Thread() {
                public void run() {
                    SpeechModel model = speechToTextService.getModel("en-US_BroadbandModel").execute();
                }
            };

            modelThread.start();

            textToSpeechService = new TextToSpeech();
            textToSpeechService.setUsernameAndPassword(TTS_username, TTS_password);
            headers.put("X-Watson-Learning-Opt-Out", "true");
        }
    }

    /**
     * This function sends a string to the Watson API to get a text response
     *
     * @param messageToWatson the string message that is being passed to Watson
     * @return string response from Watson API
     */
    public static String sendMessage(String messageToWatson) {
        responseFromWatson.setWatsonMessage("");
        if (checkInternetConnection()) {
            final String messagedToBePassed = messageToWatson;
            responseFromWatson.setWatsonMessage("Sorry, the watson service is unavailable right now.");
            Thread networkThread = new Thread() {
                public void run() {
                    try {
                        String replyFromWatson;
                        // Send message to Watson and obtain a response
                        MessageRequest newMessage = new MessageRequest.Builder()
                                .inputText(messagedToBePassed)
                                .context(contextMap)
                                .build();

                        MessageResponse response = service
                                .message(workspace_id, newMessage)
                                .execute();

                        ArrayList responseList = (ArrayList) response.getOutput().get("text");
                        if (null != responseList && responseList.size() > 0) {
                            replyFromWatson = ((String) responseList.get(0));
                            responseFromWatson.setWatsonMessage(replyFromWatson + "\n");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };

            networkThread.start();
            try {
                networkThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return responseFromWatson.getMessageAsString();
    }

    /**
     * This function sends a string to the Watson API to get a voice response
     *
     * @param messageToWatson the string message that is being passed to Watson
     */
    public static void textToSpeech(String messageToWatson) {
        if(checkInternetConnection()) {
            final String message = messageToWatson;
            // Run the stream player on a separate thread to prevent resource locking
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        streamPlayer = new StreamPlayer();
                        streamPlayer.playStream(textToSpeechService.synthesize(message, Voice.EN_LISA).execute());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    // TODO: Hint: logic returns to the onclick listener only after this function finishes execution
    // Main logic for speech to text
    public static void speechToText(Boolean status, EditText messageBox) {
        if(checkInternetConnection()) {
            listening = status;
            inputBox = messageBox;
            if (!listening) {
                capture = microphoneHelper.getInputStream(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            speechToTextService.recognizeUsingWebSocket(capture, getRecognizeOptions(), new MicrophoneRecognizeDelegate());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Toast.makeText(ChatBotActivity.getAppContext(), "Listening....Click to Stop", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    microphoneHelper.closeInputStream();
                    Toast.makeText(ChatBotActivity.getAppContext(), "Stopped Listening....Click to Start", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            if (speechResults.getResults() != null && !speechResults.getResults().isEmpty()) {
                String text = speechResults.getResults().get(0).getAlternatives().get(0).getTranscript();
                if (text != null) {
                    ChatBotActivity.updateMessageBox(text);
                }
            }
        }

        @Override
        public void onConnected() {

        }

        @Override
        public void onError(Exception e) {
            e.printStackTrace();
        }

        @Override
        public void onDisconnected() {

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

    /**
     * This function checks if the device is connected to the internet.
     *
     * @return Boolean value depending on whether internet connection was found or not.
     */
    public static boolean checkInternetConnection() {
        // get Connectivity Manager object to check connection
        ConnectivityManager cm =
                (ConnectivityManager)ChatBotActivity.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Check for network connections
        if (isConnected){
            return true;
        }
        else {
            Toast.makeText(ChatBotActivity.getAppContext(), " No Internet Connection available ", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    private static void endMicListener(){
        listening = true;
    }
}










