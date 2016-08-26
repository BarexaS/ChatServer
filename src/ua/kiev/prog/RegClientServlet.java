package ua.kiev.prog;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "RegClient", urlPatterns = "/RegClient")
public class RegClientServlet extends HttpServlet {
    private RegisteredList regList = RegisteredList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        for (String line = ""; (line = reader.readLine()) != null; ) {
            sb.append(line);
        }
        regList.addNewClient(Client.fromJSON(sb.toString()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        regList.exit(login);
    }
}
