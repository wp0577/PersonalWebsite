package util;

import Domain.LoginInfo;
import javax.servlet.ServletContext;
import java.util.ArrayList;

public class SessionUtil {
    /*
     * 根据sessionID查找用户
     */
    public static LoginInfo getUserBySessionId(ArrayList<LoginInfo> list,String id){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSessionIdString().equals(id)){
                return list.get(i);
            }
        }
        return null;
    }
    /*
     * 根据sessionID删除用户
     */
    public static boolean remove(ArrayList<LoginInfo> list,String id) {
        //用作判断是否有sessionId被查到并删除
        boolean flag = false;
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSessionIdString().equals(id)){
                list.remove(list.get(i));
                flag = true;
            }
        }
        return flag;
    }

}
