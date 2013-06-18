package connection;

public class StudentProxy implements connection.Student {
  private String _endpoint = null;
  private connection.Student student = null;
  
  public StudentProxy() {
    _initStudentProxy();
  }
  
  public StudentProxy(String endpoint) {
    _endpoint = endpoint;
    _initStudentProxy();
  }
  
  private void _initStudentProxy() {
    try {
      student = (new connection.StudentServiceLocator()).getstudent();
      if (student != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)student)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)student)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (student != null)
      ((javax.xml.rpc.Stub)student)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public connection.Student getStudent() {
    if (student == null)
      _initStudentProxy();
    return student;
  }
  
  public java.lang.String del(int sid) throws java.rmi.RemoteException{
    if (student == null)
      _initStudentProxy();
    return student.del(sid);
  }
  
  public java.lang.String add(int sid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ccmpleted, java.lang.String ctaken) throws java.rmi.RemoteException{
    if (student == null)
      _initStudentProxy();
    return student.add(sid, fname, lname, street, city, state, zip, dept, ccmpleted, ctaken);
  }
  
  public java.lang.String update(int sid, java.lang.String fname, java.lang.String lname, java.lang.String street, java.lang.String city, java.lang.String state, int zip, java.lang.String dept, java.lang.String ccmpleted, java.lang.String ctaken) throws java.rmi.RemoteException{
    if (student == null)
      _initStudentProxy();
    return student.update(sid, fname, lname, street, city, state, zip, dept, ccmpleted, ctaken);
  }
  
  public java.lang.String searchf(int sid) throws java.rmi.RemoteException{
    if (student == null)
      _initStudentProxy();
    return student.searchf(sid);
  }
  
  
}