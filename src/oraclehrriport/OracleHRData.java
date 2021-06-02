    package oraclehrriport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import org.w3c.dom.Element;

public class OracleHRData {
  private int employeeID, salary, employeeManagerID, departmentID,
    departmentManagerID, minSalary, maxSalary;
  private String employeeName, email, jobTitle, departmentName;
  private GregorianCalendar hireData;

  private OracleHRData(int employeeID, int salary, int employeeManagerID,
      int departmentID, int departmentManagerID, int minSalary, int maxSalary,
      String employeeName, String email, String jobTitle, String departmentName,
      GregorianCalendar hireData) {
    this.employeeID=employeeID;
    this.salary=salary;
    this.employeeManagerID=employeeManagerID;
    this.departmentID=departmentID;
    this.departmentManagerID=departmentManagerID;
    this.minSalary=minSalary;
    this.maxSalary=maxSalary;
    this.employeeName=employeeName;
    this.email=email;
    this.jobTitle=jobTitle;
    this.departmentName=departmentName;
    this.hireData=hireData;
  }
  
  private static String getItem(Element element, String tagName) {
    return element.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
  }

  public static OracleHRData createOracleHRData(Element element) {
    int employeeID=Integer.parseInt(getItem(element, "EmployeeID"));
    int salary=Integer.parseInt(getItem(element, "Salary"));
    int employeeManagerID=0;
    try { 
      employeeManagerID=Integer.parseInt(getItem(element, "EmployeeManagerID"));
    }
    catch(NumberFormatException | NullPointerException e) {
      //e.printStackTrace(); //1 fő vezetőnek nincs vezetője
    }
    int departmentID=Integer.parseInt(getItem(element, "DepartmentID"));
    int departmentManagerID=Integer.parseInt(getItem(element, "DepartmentManagerID"));
    int minSalary=Integer.parseInt(getItem(element, "MinSalary"));
    int maxSalary=Integer.parseInt(getItem(element, "MaxSalary"));
    String employeeName=getItem(element, "EmployeeName");
    String email=getItem(element, "Email");
    String jobTitle=getItem(element, "JobTitle");
    String departmentName=getItem(element, "DepartmentName");
    GregorianCalendar hireDate=new GregorianCalendar();
    try {      
      hireDate.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getItem(element, "HireDate")));
    }
    catch(ParseException e) {
      //e.printStackTrace(); //ideális állapotot feltételezve, mivel az adatok SQL2XML-ből származnak
    }
    return new OracleHRData(employeeID, salary, employeeManagerID,
      departmentID, departmentManagerID, minSalary, maxSalary,
      employeeName, email, jobTitle, departmentName,
      hireDate);
  }
/*<Data>
    <EmployeeID>100</EmployeeID>
    <EmployeeName>Steven King</EmployeeName>
    <Email>SKING</Email>
    <HireDate>1987-06-17</HireDate>
    <JobTitle>President</JobTitle>
    <Salary>24000</Salary>
    <EmployeeManagerID /> //vezetőnek null
    <DepartmentID>90</DepartmentID>
    <DepartmentName>Executive</DepartmentName>
    <DepartmentManagerID>100</DepartmentManagerID>
    <MinSalary>20000</MinSalary>
    <MaxSalary>40000</MaxSalary>
  </Data>
*/  

  public int getEmployeeID() {
    return employeeID;
  }

  public int getSalary() {
    return salary;
  }

  public int getEmployeeManagerID() {
    return employeeManagerID;
  }

  public int getDepartmentID() {
    return departmentID;
  }

  public int getDepartmentManagerID() {
    return departmentManagerID;
  }

  public int getMinSalary() {
    return minSalary;
  }

  public int getMaxSalary() {
    return maxSalary;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public String getEmail() {
    return email;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public GregorianCalendar getHireData() {
    return hireData;
  }  
}