package Action;

import dao.LoginInfoDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "VisitorServlet")
public class VisitorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String total = "0";
        try {
            total = LoginInfoDao.getTotal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //可能要把这句放后面
        String number = String.valueOf(request.getSession().getServletContext().getAttribute("number"));
        System.out.println("total = "+ total);
        String res = number+","+total;
        response.getWriter().write(res);
    }
}
