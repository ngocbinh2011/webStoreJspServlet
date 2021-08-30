package com.webapp.Model;

import java.util.Date;

public class SessionRecord {
    private String sessionID;
    private Account account;
    private Date nearLest;

    public SessionRecord() {

    }

    public SessionRecord(String sessionID, Account account, Date nearLest) {
        this.sessionID = sessionID;
        this.account = account;
        this.nearLest = nearLest;
    }

    public SessionRecord(String sessionID, Account account) {
        this.sessionID = sessionID;
        this.account = account;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getNearLest() {
        return nearLest;
    }

    public void setNearLest(Date nearLest) {
        this.nearLest = nearLest;
    }
}
