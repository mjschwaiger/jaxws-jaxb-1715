# jaxws-jaxb-1715

Sample project to illustrate the incompatibility in jaxb-ri introduced in version 4.0.3 with issue https://github.com/eclipse-ee4j/jaxb-ri/issues/1715.

Reproduction:
-------------
1. Start the server by running MyServer (using jaxb-impl/jaxb-runtime version 4.0.3, see jaxws-server\build.gradle)
2. Wait until server is started
3. Start the client by running MyClient (using jaxb-impl/jaxb-runtime version 4.0.2, see jaxws-client\build.gradle)
4. Returned fault detail message: **null** :x: (should be myDetailMessage and not null)

Compatibility
-------------
Can be reproduced using com.sun.xml.bind:jaxb-impl and/or org.glassfish.jaxb:jaxb-runtime, if version of jaxb-impl and/or jaxb-runtime does not match for server and client.
| - | Server jaxb-impl <=4.0.2 | Server jaxb-runtime <=4.0.2 | Server jaxb-impl 4.0.3 | Server jaxb-runtime 4.0.3 |
| ---- | :----: | :----: | :----: | :----: |
| Client jaxb-impl <=4.0.2 | :white_check_mark: | :white_check_mark: | :x: | :x: |
| Client jaxb-runtime <=4.0.2 | :white_check_mark: | :white_check_mark: | :x: | :x: |
| Client jaxb-impl 4.0.3 | :x: | :x: | :white_check_mark: | :white_check_mark: |
| Client jaxb-runtime 4.0.3 | :x: | :x: | :white_check_mark: | :white_check_mark: |

Debug server
------------
Set breakpoint in the following method and evaluate value of Packet:
```
com.sun.xml.ws.server.sei.TieHandler.serializeResponse()
```

Difference
----------
* Packet in server jaxb-impl/jaxb-runtime 4.0.2:
```
<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><S:Fault xmlns:ns4="http://www.w3.org/2003/05/soap-envelope"><faultcode>S:Server</faultcode><faultstring>myMessage</faultstring><detail><ns2:MyFault xmlns:ns2="http://example.org/"><detailMessage>myDetailMessage</detailMessage></ns2:MyFault></detail></S:Fault></S:Body></S:Envelope>
```

* Packet in server using jaxb-impl/jaxb-runtime 4.0.3:
```
<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><S:Fault xmlns:ns4="http://www.w3.org/2003/05/soap-envelope"><faultcode>S:Server</faultcode><faultstring>myMessage</faultstring><detail><ns2:MyFault xmlns:ns2="http://example.org/"><ns2:detailMessage>myDetailMessage</ns2:detailMessage></ns2:MyFault></detail></S:Fault></S:Body></S:Envelope>
```

Difference in detailMessage:
* Using jaxb-impl/jaxb-runtime 4.0.2:
```
<detailMessage>myDetailMessage</detailMessage>
```
* Using jaxb-impl/jaxb-runtime 4.0.3:
```
<ns2:detailMessage>myDetailMessage</ns2:detailMessage>
```
