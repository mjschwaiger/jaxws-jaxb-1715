package org.example;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.ws.WebFault;

import java.io.Serial;

@XmlRootElement(name = "Test")
@WebFault(name = "MyFault")
public class MyFaultException extends Exception {
    @Serial
    private static final long serialVersionUID = 5599474953533908988L;

    private MyFault fault;

    public MyFaultException() {
        super();
    }

    public MyFaultException(final String pMessage, final MyFault pFault) {
        super(pMessage);
        fault = pFault;
    }

    public MyFault getFaultInfo() {
        return fault;
    }
}
