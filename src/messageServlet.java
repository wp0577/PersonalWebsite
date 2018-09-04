import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

public class messageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String mail = request.getParameter("email");
        String content = request.getParameter("message");
        Date d=new java.util.Date();
        try {
            //get datasource by c3p0
            DataSource ds = DBUtil.getDataSource();
            //建立连接
            QueryRunner queryRunner = new QueryRunner(ds);
            String sql = "INSERT INTO message(name, email, content, date) VALUES (?,?,?,?)";
            queryRunner.update(sql, name, mail, content, d);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.getWriter().write("Thank you "+ name +" for your submitting, Have a good one!!!");

        /*System.out.println("test for servlet");
        String name = request.getParameter("name");
        String mail = request.getParameter("email");
        String content = request.getParameter("message");
        Date d=new java.util.Date();
        FileWriter fw = new FileWriter("/Users/wupan/Documents/workspace/personalInfo/src/data.txt", true);
        fw.write("\r\n");
        fw.write(" name: " + name+ ",");
        fw.write(" mail: "+mail+",");
        fw.write(" content: " + content +",");
        fw.write(" date： " + d +".");
        fw.close();*/
    }
}
