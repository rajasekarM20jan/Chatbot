package com.example.chatbot;

public class chatBotModel {
    String message;
    String message_code;

    public chatBotModel(String message, String message_code) {
        this.message = message;
        this.message_code = message_code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage_code() {
        return message_code;
    }

    public void setMessage_code(String message_code) {
        this.message_code = message_code;
    }
}
