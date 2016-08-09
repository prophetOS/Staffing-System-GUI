package emp;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Administrator
 * @version 1.0
 * @created 04-三月-2012 16:49:12
 */
public class DAoImpl implements DAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String strUrl = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private CallableStatement proc = null;
	private String sql = "select * FROM employee";
	/**
	 * 连接数据库
	 * */
	public Connection getConnection() {
		try {
			//加载驱动程序
			Class.forName(driver);
			//获得连接对象
			conn = DriverManager.getConnection(strUrl, "scott", "tiger");
			System.out.println("数据库连接成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//返回连接对象
		return conn;
	}

	/**
	 * 关闭数据库
	 * */
	public void closeConnection(Connection conn, CallableStatement proc, ResultSet rs) {
		if (rs != null) {
			try {
				//立即释放此 ResultSet 对象的数据库和 JDBC 资源
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (proc != null) {
			try {
				//立即释放此 Statement对象的数据库和 JDBC资源
				proc.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				//立即释放此 Connection 对象的数据库和 JDBC 资源
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param e
	 */
	public void addEmployee(Employee e){
		try{
			conn = getConnection();
			proc = conn.prepareCall("{ call add_emp(?,?,?,?,?)}");
			proc.setString(1, e.getEmployeeName());
			proc.setString(2, e.getGender());
			proc.setString(3, e.getAddress());
			proc.setString(4, e.getEmployeeType());
			proc.setInt(5, e.getSalary());
			proc.execute();
			
		}catch(SQLException ex2){
			ex2.printStackTrace();
		}catch(Exception ex2){
			ex2.printStackTrace();
		}finally{
			try{
				closeConnection(conn, proc, rs);
			}catch(Exception ex){}
		}
	}

	/**
	 * 
	 * @param e
	 */
	public void delEmployee(Employee e){
		try{
			conn = getConnection();
			proc = conn.prepareCall("{ call del_emp(?)}");
			proc.setInt(1, e.getEmployeeID());
			proc.execute();
		}catch(SQLException ex2){
			ex2.printStackTrace();
		}catch(Exception ex2){
			ex2.printStackTrace();
		}finally{
			try{
				closeConnection(conn, proc, rs);
			}catch(Exception ex){}
		}
	}

	/**
	 * 
	 * @param e
	 */
	public void editEmployee(Employee e){
		try{
			conn = getConnection();
			proc = conn.prepareCall("{ call edit_emp(?,?,?,?,?,?)}");
			proc.setString(1, e.getEmployeeName());
			proc.setString(2, e.getGender());
			proc.setString(3, e.getAddress());
			proc.setString(4, e.getEmployeeType());
			proc.setInt(5, e.getSalary());
			proc.setInt(6, e.getEmployeeID());
			proc.execute();
		}catch(SQLException ex2){
			ex2.printStackTrace();
		}catch(Exception ex2){
			ex2.printStackTrace();
		}finally{
			try{
				closeConnection(conn, proc, rs);
			}catch(Exception ex){}
		}
	}

	public List<Employee> listAllEmployees(){
		List<Employee> emplist = new ArrayList();
		
		try{
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(sql);
			while(this.rs.next()){
				Employee emp = new Employee();
				emp.setEmployeeID(rs.getInt("id"));
				emp.setEmployeeName(rs.getString("name"));
				emp.setGender(rs.getString("gender"));
				emp.setEmployeeType(rs.getString("type"));
				emp.setAddress(rs.getString("address"));
				emp.setSalary(rs.getInt("salary"));
				
				emplist.add(emp);
			}
		}catch(Exception e){
			
		}finally{
			this.closeConnection(conn, proc, rs);
		}
		return emplist;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getStrUrl() {
		return strUrl;
	}

	public void setStrUrl(String strUrl) {
		this.strUrl = strUrl;
	}

	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public ResultSet getRs() {
		return rs;
	}

	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public CallableStatement getProc() {
		return proc;
	}

	public void setProc(CallableStatement proc) {
		this.proc = proc;
	}
	
}