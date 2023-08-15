package org.example;

import java.io.Serial;
import java.io.Serializable;

public class MyFault implements Serializable {

    @Serial
    private static final long serialVersionUID = 7241307296053178278L;
    private String detailMessage;

    public MyFault() {
    }

    public MyFault(
            final String pDetailMessage) {
        detailMessage = pDetailMessage;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String pDetailMessage) {
        detailMessage = pDetailMessage;
    }
}
