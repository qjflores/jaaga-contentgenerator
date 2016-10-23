package in.jaaga.learning.bots.skillbot;

import java.util.HashMap;

import in.jaaga.learning.api.ChatItem;

public interface Problem {
    public String getPrompt();
    public ChatItem getPromptChatItem();
    public boolean checkAnswer(String answer);
    public Problem next();
    public void save(HashMap<String, String> session);
    public void restore(HashMap<String, String> session);
}
