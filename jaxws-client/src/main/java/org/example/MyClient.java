package org.example;

import jakarta.xml.ws.Service;

import javax.xml.namespace.QName;
import java.net.URL;

public class MyClient {
    public static void main(final String[] args) throws Exception {
        System.out.println("Starting MyClient");
        String url = "http://localhost:4434/mywebservice";
        System.out.println("Connect to " + url);
        Service service = Service.create(
                new URL(url + "?wsdl"),
                new QName("http://example.org/", "MyWebServiceImplService"));
        MyWebService myWebService = service.getPort(MyWebService.class);
        try {
            System.out.println("Call MyWebService.myMethod()");
            myWebService.myMethod();
        } catch (MyFaultException e) {
            System.out.println("Returned fault detail message: " + e.getFaultInfo().getDetailMessage());
        }
    }
}
