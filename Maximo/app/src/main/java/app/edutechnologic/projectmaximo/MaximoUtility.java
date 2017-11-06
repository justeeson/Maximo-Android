package app.edutechnologic.projectmaximo;


import android.app.Activity;

public class MaximoUtility{
    private String conversation_username;
    private String conversation_password;
    private String TTS_username;
    private String TTS_password;
    private String STT_username;
    private String STT_password;
    private String workspace_id;

    public MaximoUtility(){
        conversation_username = "3072f50f-4f30-424d-83a4-1a3cd970a4af";
        conversation_password = "flEyamA3drcv";
        TTS_username = "894dc105-4f3b-406f-bd5e-021a096b6a35";
        TTS_password = "VLOHR4BWSRLa";
        STT_username = "c96da939-81fe-4057-a026-4f8465076666";
        STT_password = "mPnVQaebCMrP";
        workspace_id = "fcd7dbf9-47c6-4f2e-8384-d358db00a087";
    }

    public String getConversationUsername(){
        return this.conversation_username;
    }

    public String getConversationPassword(){
        return this.conversation_password;
    }

    public String getTTSUsername(){
        return this.TTS_username;
    }

    public String getTTSPassword(){
        return this.TTS_password;
    }

    public String getSTTUsername(){
        return this.STT_username;
    }

    public String getSTTPassword(){
        return this.STT_password;
    }

    public String getWorkspaceID(){
        return this.workspace_id;
    }

}


