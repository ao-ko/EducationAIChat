package com.example.chatuiplaygroud;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import android.os.Handler;
import android.os.Looper;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        List<dev.langchain4j.data.message.ChatMessage> messageList = new ArrayList<>();
        String messageTextStart = "Welcome to use the education AI tool. Please paste the article and the test questions will appear.";
        ChatMessage messageFirst = new ChatMessage(messageTextStart, new Date().getTime(), ChatMessage.Type.RECEIVED);
        chatView.addMessage(messageFirst);
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
                            messageList.add(UserMessage.from(chatMessage.getMessage()));
                            AiMessage aiMessage = openAiChatModel.generate(messageList).content();
                            messageList.add(aiMessage);

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ChatMessage messageAns = new ChatMessage(aiMessage.text(), new Date().getTime(), ChatMessage.Type.RECEIVED);
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
}

