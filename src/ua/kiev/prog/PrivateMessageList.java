package ua.kiev.prog;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessageList {
    private static final PrivateMessageList prvtMsgList = new PrivateMessageList();

    private final List<Message> list = new ArrayList<>();

    public static PrivateMessageList getInstance() {
        return prvtMsgList;
    }

    private PrivateMessageList() {
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(String user, int n) {
        List<Message> res = new ArrayList<>();
        for (int i = n; i < list.size(); i++)

            if (list.get(i).getFrom().equals(user) || list.get(i).getTo().equals(user)) {
                res.add(list.get(i));
            }

        if (res.size() > 0) {
            Gson gson = new GsonBuilder().create();
            return gson.toJson(res.toArray());
        } else
            return null;
    }
}
