package app.edutechnologic.projectmaximo;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import app.edutechnologic.projectmaximo.ChatBot.ChatBotActivity;

public class MaximoUtility{
    public static String conversation_username;
    public static String conversation_password;
    public static String TTS_username;
    public static String TTS_password;
    public static String STT_username;
    public static String STT_password;
    public static String workspace_id;
    public static Activity chatbotActivity;

    public static void initialize(){
        conversation_username = "3072f50f-4f30-424d-83a4-1a3cd970a4af";
        conversation_password = "flEyamA3drcv";
        TTS_username = "894dc105-4f3b-406f-bd5e-021a096b6a35";
        TTS_password = "VLOHR4BWSRLa";
        STT_username = "c96da939-81fe-4057-a026-4f8465076666";
        STT_password = "mPnVQaebCMrP";
        workspace_id = "fcd7dbf9-47c6-4f2e-8384-d358db00a087";
   }
}


