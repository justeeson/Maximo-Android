package app.edutechnologic.projectmaximo;


public class MaximoUtility {
    private final String conversation_username;
    private final String conversation_password;
    private final String TTS_username;
    private final String TTS_password;
    private final String STT_username;
    private final String STT_password;
    private final String workspace_id;

    public MaximoUtility() {
        conversation_username = "190135b9-77f2-4547-a230-61e457edd715";
        conversation_password = "8fOXKNKfKx24";
        TTS_username = "1e66e0ee-2cb3-4c69-adde-069debe5f4be";
        TTS_password = "JJp2Jl3k64jb";
        STT_username = "3d0e419f-1196-4e17-a000-bfb9f86ca83b";
        STT_password = "YWNVcAVrGxzT";
        workspace_id = "63a97876-099c-4c11-ae5f-fefd2dbc7952";
    }

    public String getConversationUsername() {
        return this.conversation_username;
    }

    public String getConversationPassword() {
        return this.conversation_password;
    }

    public String getTTSUsername() {
        return this.TTS_username;
    }

    public String getTTSPassword() {
        return this.TTS_password;
    }

    public String getSTTUsername() {
        return this.STT_username;
    }

    public String getSTTPassword() {
        return this.STT_password;
    }

    public String getWorkspaceID() {
        return this.workspace_id;
    }

}


