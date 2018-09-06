package Domain;

import java.sql.Timestamp;

public class LoginInfo {
    private String id;

    //仅作为判断是否是相同对话的标示，不储存到数据库中
    private String sessionIdString;

    private String ipString;
    //record how many times visit website within each ip address.
    private Integer totalCount;

    private Timestamp lastTimeString;
    private Timestamp firstTimeString;

    public Timestamp getLastTimeString() {
        return lastTimeString;
    }

    public void setLastTimeString(Timestamp lastTimeString) {
        this.lastTimeString = lastTimeString;
    }



    public String getSessionIdString() {
        return sessionIdString;
    }

    public void setSessionIdString(String sessionIdString) {
        this.sessionIdString = sessionIdString;
    }

    public String getIpString() {
        return ipString;
    }

    public void setIpString(String ipString) {
        this.ipString = ipString;
    }



    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getFirstTimeString() {
        return firstTimeString;
    }

    public void setFirstTimeString(Timestamp firstTimeString) {
        this.firstTimeString = firstTimeString;
    }
}
