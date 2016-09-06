package ua.kiev.prog;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetClientList", urlPatterns = "/getList")
public class GetClientList extends HttpServlet {
    private RegisteredList regList = RegisteredList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        for (Cookie c : req.getCookies()) {
            if ("Session".equalsIgnoreCase(c.getName())){
                if ("true".equalsIgnoreCase(c.getValue())){
                    resp.getOutputStream().print(regList.maptoString());
                } else {
                    resp.getOutputStream().print("Please login");
                }
            }
        }
    }
}
