package com.example.chatuiplaygroud;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    private  OpenAiChatModel openAiChatModel;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*Toast.makeText(this, "service is on", Toast.LENGTH_LONG).show();*/
        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        ChatMessage message1 = new ChatMessage("msg1", 12345678, ChatMessage.Type.SENT);
        ChatMessage message2 = new ChatMessage("msg12", 12345679, ChatMessage.Type.RECEIVED);
        chatView.addMessage(message1);
        chatView.addMessage(message2);
        Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_LONG).show();
        executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {

                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String apiKey = "demo";
                            if (openAiChatModel == null) {
                                openAiChatModel = OpenAiChatModel.withApiKey(apiKey);
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "model ready", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            String answer = openAiChatModel.generate(chatMessage.getMessage());

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ChatMessage messageAns = new ChatMessage(answer, new Date().getTime(), ChatMessage.Type.RECEIVED);
                                    chatView.addMessage(messageAns);
                                    //Toast.makeText(getApplicationContext(), answer, 10 * Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                return true;
            }
        });
            }
    /*
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
    */
    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyService.class));
    }
}

