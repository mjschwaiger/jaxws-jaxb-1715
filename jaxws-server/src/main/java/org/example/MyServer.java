package org.example;

import jakarta.xml.ws.Endpoint;

public class MyServer {
    public static void main(final String[] args) {
        System.out.println("Starting MyServer");
        String url = "http://localhost:4434/mywebservice";
        System.out.println("Publish MyWebServiceImpl (" + url + ")");
        Endpoint.publish(url, new MyWebServiceImpl());
    }
}
