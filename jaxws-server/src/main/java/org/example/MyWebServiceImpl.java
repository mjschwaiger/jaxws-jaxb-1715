package org.example;

import jakarta.jws.WebService;

@WebService(endpointInterface = "org.example.MyWebService")
public class MyWebServiceImpl implements MyWebService {
    public String myMethod() throws MyFaultException {
        System.out.println("Method myMethod called -> throwing MyFaultException");
        throw new MyFaultException("myMessage", new MyFault("myDetailMessage"));
    }
}
