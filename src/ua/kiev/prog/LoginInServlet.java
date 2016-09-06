package ua.kiev.prog;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "LoginIn", urlPatterns = "/loginIn")
public class LoginInServlet extends HttpServlet {
    private RegisteredList regList = RegisteredList.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Вычитываем логин и пароль
        BufferedReader is = req.getReader();
        String login = is.readLine();
        String pass = is.readLine();


        if (regList.checkRegLogin(login)) { //Логин зарегистрирован
            Client clt = regList.checkRegPassword(login, pass); // Вернет клиента, если пароли совпали, если нет - null
            if ((clt) != null) {
//            Отправляем gson Client зарегистрированного клиента и куки

                resp.addCookie(new Cookie("SessionId","true"));
                clt.setStatus(true);
                resp.getOutputStream().print("Welcome!!!" + "\n" + clt.toJSON());
            } else {
//                Не совпали пароли
                resp.getOutputStream().print("Wrong pass" + "\n");
            }
        } else {
            resp.getOutputStream().print("No such user" + "\n");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        boolean result = regList.checkLogStatus(login);
        resp.getOutputStream().print(result);
    }
}
