package com.donum.accountservice.Model;


public class MailRequest {

    private String URL = "http://localhost:8020/verify-account/";
    private String accesscode;
    private String firstname;
    private String to;
    private String from;
    private String subject;

    public MailRequest() {
    }

    public MailRequest(String accesscode, String firstname, String to, String from, String subject) {
        this.accesscode = accesscode;
        this.firstname = firstname;
        this.to = to;
        this.from = from;
        this.subject = subject;
    }

    public String getURL() {
        return URL + accesscode;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getAccesscode() {
        return accesscode;
    }

    public void setAccesscode(String accesscode) {
        this.accesscode = accesscode;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
