/**
 * StudentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

public class StudentServiceLocator extends org.apache.axis.client.Service implements connection.StudentService {

    public StudentServiceLocator() {
    }


    public StudentServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public StudentServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for student
    private java.lang.String student_address = "http://localhost:8080/p1/services/student";

    public java.lang.String getstudentAddress() {
        return student_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String studentWSDDServiceName = "student";

    public java.lang.String getstudentWSDDServiceName() {
        return studentWSDDServiceName;
    }

    public void setstudentWSDDServiceName(java.lang.String name) {
        studentWSDDServiceName = name;
    }

    public connection.Student getstudent() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(student_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getstudent(endpoint);
    }

    public connection.Student getstudent(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            connection.StudentSoapBindingStub _stub = new connection.StudentSoapBindingStub(portAddress, this);
            _stub.setPortName(getstudentWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setstudentEndpointAddress(java.lang.String address) {
        student_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (connection.Student.class.isAssignableFrom(serviceEndpointInterface)) {
                connection.StudentSoapBindingStub _stub = new connection.StudentSoapBindingStub(new java.net.URL(student_address), this);
                _stub.setPortName(getstudentWSDDServiceName());
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
        if ("student".equals(inputPortName)) {
            return getstudent();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://connection", "studentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://connection", "student"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("student".equals(portName)) {
            setstudentEndpointAddress(address);
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
