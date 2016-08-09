package emp;

import java.util.List;



/**
 * @author Administrator
 * @version 1.0
 * @created 04-ÈýÔÂ-2012 16:49:11
 */
public class EmployeeServiceImpl implements EmployeeService {
	private DAO dao = new DAoImpl();

	/**
	 * 
	 * @param e
	 */
	public void addEmployee(Employee e){
		this.dao.addEmployee(e);
	}

	/**
	 * 
	 * @param e
	 */
	public void delEmployee(Employee e){
		this.dao.delEmployee(e);
	}

	/**
	 * 
	 * @param e
	 */
	public void editEmpployee(Employee e){
		this.dao.editEmployee(e);
	}

	public DAO getDAO(){
		return this.dao;
	}

	public List<Employee> listAllEmployees(){
		return this.dao.listAllEmployees();
	}

	/**
	 * 
	 * @param d
	 */
	public void setDAO(DAO d){
		this.dao = d;
	}

}