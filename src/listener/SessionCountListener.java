package listener;


import Domain.LoginInfo;
import util.SessionUtil;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

public class SessionCountListener implements HttpSessionListener {


    //在线用户数
    private int number;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("进入session————————————————");
/*
        ILoginInfoDao iLoginInfoDao=(ILoginInfoDao) SessionUtil.getObjectFromApplication(se.getSession().getServletContext(), "ILoginInfoDaoImpl");
        List<LoginInfo> all = iLoginInfoDao.getAll();
        List<LoginInfo> userList = (List<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
*/
        //设置session失效时间，为调试用
        //se.getSession().setMaxInactiveInterval(60*1);
        ArrayList<LoginInfo> userList = (ArrayList<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
        if(userList==null || SessionUtil.getUserBySessionId(userList, se.getSession().getId())==null) {
            number++;
        }
        //在线用户的数量存储到域对象ServletContext的number中
        se.getSession().getServletContext().setAttribute("number", number);
        System.out.println("number =" +number);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //list是存储在域对象ServletContext中，用于记录用户的的日志信息
        ArrayList<LoginInfo> list=
                (ArrayList<LoginInfo>) se.getSession().getServletContext().getAttribute("userList");
        //根据域中的session取得number
        number = Integer.valueOf(se.getSession().getServletContext().getAttribute("number").toString());
        //根据list去删session中的number
        //根据sessionid删除将要推出的用户信息
        if(SessionUtil.remove(list,se.getSession().getId())){
            //if(list!=null || list.size()>0) number=(number<=0)?0:number-1;
            number=(number<=0)?0:number-1;
            se.getSession().getServletContext().setAttribute("number", number);
        }
        se.getSession().getServletContext().setAttribute("userList", list);
        System.out.println("after = "+ number);
    }
}
