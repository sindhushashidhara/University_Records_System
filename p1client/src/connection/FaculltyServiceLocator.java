/**
 * FaculltyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

public class FaculltyServiceLocator extends org.apache.axis.client.Service implements connection.FaculltyService {

    public FaculltyServiceLocator() {
    }


    public FaculltyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FaculltyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for facullty
    private java.lang.String facullty_address = "http://localhost:8080/p1/services/facullty";

    public java.lang.String getfaculltyAddress() {
        return facullty_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String faculltyWSDDServiceName = "facullty";

    public java.lang.String getfaculltyWSDDServiceName() {
        return faculltyWSDDServiceName;
    }

    public void setfaculltyWSDDServiceName(java.lang.String name) {
        faculltyWSDDServiceName = name;
    }

    public connection.Facullty getfacullty() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(facullty_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getfacullty(endpoint);
    }

    public connection.Facullty getfacullty(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            connection.FaculltySoapBindingStub _stub = new connection.FaculltySoapBindingStub(portAddress, this);
            _stub.setPortName(getfaculltyWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setfaculltyEndpointAddress(java.lang.String address) {
        facullty_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (connection.Facullty.class.isAssignableFrom(serviceEndpointInterface)) {
                connection.FaculltySoapBindingStub _stub = new connection.FaculltySoapBindingStub(new java.net.URL(facullty_address), this);
                _stub.setPortName(getfaculltyWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("facullty".equals(inputPortName)) {
            return getfacullty();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://connection", "faculltyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://connection", "facullty"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("facullty".equals(portName)) {
            setfaculltyEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
