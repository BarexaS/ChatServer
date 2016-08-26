package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MessageList {

    private static MessageList msgList = new MessageList();

    private final List<Message> list = new ArrayList<>();

    private static final Map<String, MessageList> rooms = new TreeMap<>();

    static {
        rooms.put("public", msgList);
    }

    public static MessageList getInstance(String roomName) {
        return rooms.get(roomName);
    }

    public static boolean addNewRoom(String roomName) {
        if (!rooms.containsKey(roomName)) {
            rooms.put(roomName, new MessageList());
            return true;
        } else {
            return false;
        }
    }

    private MessageList() {
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(int n) {
        List<Message> res = new ArrayList<Message>();
        for (int i = n; i < list.size(); i++)
            res.add(list.get(i));
        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res.toArray());
        } else
            return null;
    }

    public static String maptoString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, MessageList> entry : rooms.entrySet()) {
            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
}
