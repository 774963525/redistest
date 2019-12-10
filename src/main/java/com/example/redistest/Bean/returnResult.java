package com.example.redistest.Bean;

public class returnResult {
    private boolean status;
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public returnResult() {
    }

    public returnResult(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "returnResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                '}';
    }
}
