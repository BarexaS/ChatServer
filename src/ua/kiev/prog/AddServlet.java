package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class AddServlet extends HttpServlet {

    private MessageList msgList;

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        for (String line = ""; (line = reader.readLine()) != null; ) {
            sb.append(line);
        }
        Message msg = Message.fromJSON(sb.toString());
        msgList = MessageList.getInstance(msg.getRoomName());
        if (msg != null)
            msgList.add(msg);
        else
            resp.setStatus(400); // Bad request

    }
}
