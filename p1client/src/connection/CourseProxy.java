package connection;

public class CourseProxy implements connection.Course {
  private String _endpoint = null;
  private connection.Course course = null;
  
  public CourseProxy() {
    _initCourseProxy();
  }
  
  public CourseProxy(String endpoint) {
    _endpoint = endpoint;
    _initCourseProxy();
  }
  
  private void _initCourseProxy() {
    try {
      course = (new connection.CourseServiceLocator()).getcourse();
      if (course != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)course)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)course)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (course != null)
      ((javax.xml.rpc.Stub)course)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public connection.Course getCourse() {
    if (course == null)
      _initCourseProxy();
    return course;
  }
  
  public java.lang.String del(java.lang.String cid) throws java.rmi.RemoteException{
    if (course == null)
      _initCourseProxy();
    return course.del(cid);
  }
  
  public java.lang.String add(java.lang.String cid, java.lang.String cname, java.lang.String ctime, java.lang.String cloc, int empid) throws java.rmi.RemoteException{
    if (course == null)
      _initCourseProxy();
    return course.add(cid, cname, ctime, cloc, empid);
  }
  
  public java.lang.String search(java.lang.String id, java.lang.String name) throws java.rmi.RemoteException{
    if (course == null)
      _initCourseProxy();
    return course.search(id, name);
  }
  
  public java.lang.String update(java.lang.String cid, java.lang.String cname, java.lang.String ctime, java.lang.String cloc, int empid) throws java.rmi.RemoteException{
    if (course == null)
      _initCourseProxy();
    return course.update(cid, cname, ctime, cloc, empid);
  }
  
  public java.lang.String searchc(java.lang.String id) throws java.rmi.RemoteException{
    if (course == null)
      _initCourseProxy();
    return course.searchc(id);
  }
  
  
}