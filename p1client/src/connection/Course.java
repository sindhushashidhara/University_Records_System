/**
 * Course.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package connection;

public interface Course extends java.rmi.Remote {
    public java.lang.String del(java.lang.String cid) throws java.rmi.RemoteException;
    public java.lang.String add(java.lang.String cid, java.lang.String cname, java.lang.String ctime, java.lang.String cloc, int empid) throws java.rmi.RemoteException;
    public java.lang.String search(java.lang.String id, java.lang.String name) throws java.rmi.RemoteException;
    public java.lang.String update(java.lang.String cid, java.lang.String cname, java.lang.String ctime, java.lang.String cloc, int empid) throws java.rmi.RemoteException;
    public java.lang.String searchc(java.lang.String id) throws java.rmi.RemoteException;
}
