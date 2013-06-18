/**
 * StudentService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

public interface StudentService extends javax.xml.rpc.Service {
    public java.lang.String getstudentAddress();

    public connection.Student getstudent() throws javax.xml.rpc.ServiceException;

    public connection.Student getstudent(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
