/**
 * Facullty.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

public interface Facullty extends java.rmi.Remote {
    public java.lang.String del(int eid) throws java.rmi.RemoteException;
    public java.lang.String add(int eid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ofhrs, java.lang.String instrctd, java.lang.String cinstrct) throws java.rmi.RemoteException;
    public java.lang.String update(int eid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ofhrs, java.lang.String instrctd, java.lang.String cinstrct) throws java.rmi.RemoteException;
    public java.lang.String searchf(int eid) throws java.rmi.RemoteException;
}
