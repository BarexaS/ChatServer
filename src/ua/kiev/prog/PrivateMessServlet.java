package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "PrivateMess", urlPatterns = "/privateMess")
public class PrivateMessServlet extends HttpServlet {

    private PrivateMessageList prvtMsgList = PrivateMessageList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        for (String line = ""; (line = reader.readLine()) != null; ) {
            sb.append(line);
        }
        Message msg = Message.fromJSON(sb.toString());
        if (msg != null)
            prvtMsgList.add(msg);
        else
            resp.setStatus(400); // Bad request
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String from = req.getParameter("from");

        String json = prvtMsgList.toJSON(user, Integer.parseInt(from));
        if (json != null) {
            OutputStream os = resp.getOutputStream();
            os.write(json.getBytes());
            os.flush();
        }
    }
}

