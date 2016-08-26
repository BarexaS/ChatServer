package ua.kiev.prog;

import java.util.Map;
import java.util.TreeMap;

public class RegisteredList {
    private static final RegisteredList regList = new RegisteredList();

    //    Ключ - логин, обьект - Клиент
    private final Map<String, Client> map = new TreeMap<>();

    private RegisteredList() {
        map.put("admin", new Client("admin", "admin"));
    }

    public static RegisteredList getInstance() {
        return regList;
    }

    public synchronized boolean checkRegLogin(String login) {
        return map.containsKey(login);
    }

    public synchronized Client checkRegPassword(String login, String pass) {
        if (map.get(login).getPass().equals(pass)) {
            return map.get(login);
        } else {
            return null;
        }
    }

    public synchronized void exit(String login) {
        map.get(login).setStatus(false);
    }

    public synchronized boolean checkLogStatus(String login) {
        return map.get(login).isStatus();
    }

    public synchronized void addNewClient(Client clt) {
        map.put(clt.getLogin(), clt);
    }

    public String maptoString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Client> entry : map.entrySet()) {
            sb.append(entry.getKey() + " = ");
             if (entry.getValue().isStatus()) {
              sb.append("Online");
             } else {
                 sb.append("Offline");
             }
             sb.append("\n");
        }
        return sb.toString();
    }
}
