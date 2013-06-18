package connection;

public class FaculltyProxy implements connection.Facullty {
  private String _endpoint = null;
  private connection.Facullty facullty = null;
  
  public FaculltyProxy() {
    _initFaculltyProxy();
  }
  
  public FaculltyProxy(String endpoint) {
    _endpoint = endpoint;
    _initFaculltyProxy();
  }
  
  private void _initFaculltyProxy() {
    try {
      facullty = (new connection.FaculltyServiceLocator()).getfacullty();
      if (facullty != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)facullty)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)facullty)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (facullty != null)
      ((javax.xml.rpc.Stub)facullty)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public connection.Facullty getFacullty() {
    if (facullty == null)
      _initFaculltyProxy();
    return facullty;
  }
  
  public java.lang.String del(int eid) throws java.rmi.RemoteException{
    if (facullty == null)
      _initFaculltyProxy();
    return facullty.del(eid);
  }
  
  public java.lang.String add(int eid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ofhrs, java.lang.String instrctd, java.lang.String cinstrct) throws java.rmi.RemoteException{
    if (facullty == null)
      _initFaculltyProxy();
    return facullty.add(eid, fname, lname, street, city, state, zip, dept, ofhrs, instrctd, cinstrct);
  }
  
  public java.lang.String update(int eid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ofhrs, java.lang.String instrctd, java.lang.String cinstrct) throws java.rmi.RemoteException{
    if (facullty == null)
      _initFaculltyProxy();
    return facullty.update(eid, fname, lname, street, city, state, zip, dept, ofhrs, instrctd, cinstrct);
  }
  
  public java.lang.String searchf(int eid) throws java.rmi.RemoteException{
    if (facullty == null)
      _initFaculltyProxy();
    return facullty.searchf(eid);
  }
  
  
}