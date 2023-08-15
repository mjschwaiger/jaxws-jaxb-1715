package org.example;

import jakarta.jws.WebService;

@WebService
public interface MyWebService {
    String myMethod() throws MyFaultException;
}
