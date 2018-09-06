package listener;

import Domain.LoginInfo;
import dao.LoginInfoDao;
import util.NetworkUtil;
import util.SessionUtil;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;


public class RequestListener implements ServletRequestListener {


    private ArrayList<LoginInfo> userList=new ArrayList<LoginInfo>();  //在线用户Id

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("进入request ---------------");
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String remoteAddr = null;
        LoginInfo u= null;
        try {
            //获得ip地址
            remoteAddr = NetworkUtil.getIpAddress(request);
            System.out.println("ip address = "+remoteAddr);
            //if(remoteAddr.equals("0:0:0:0:0:0:0:1")) return;
            u = LoginInfoDao.getLoginByIp(remoteAddr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //创建用户list在servlectcontext域对象中
        userList=(ArrayList<LoginInfo>) sre.getServletContext().getAttribute("userList");
        if(userList==null){
            userList=new ArrayList<LoginInfo>();
        }

        //获得用户信息之sessionid
        String id=request.getSession().getId();
        if (SessionUtil.getUserBySessionId(userList, id) == null) {
            System.out.println("number ="+sre.getServletContext().getAttribute("number")+"Ip = "+remoteAddr);
            if (u == null) {
                u = new LoginInfo();
                u.setIpString(request.getRemoteAddr());
                u.setTotalCount(1);
                u.setFirstTimeString(new Timestamp(new Date().getTime()));
                LoginInfoDao.save(u);
            } else {
                //设置最后登陆时间
                u.setLastTimeString(new Timestamp(new Date().getTime()));
                //如果已经存在ip地址，则更新total
                u.setTotalCount(u.getTotalCount() + 1);
                LoginInfoDao.update(u);
            }
            //无论是否存在都存在sessionId作为判重的要求
            u.setSessionIdString(id);
            userList.add(u);
            //iLoginInfoService.save(u);
        }
        sre.getServletContext().setAttribute("userList", userList);
        System.out.println("结束"+id+"request ---------------");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }



}
