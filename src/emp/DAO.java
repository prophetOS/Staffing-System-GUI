package emp;
import java.util.List;



/**
 * @author Administrator
 * @version 1.0
 * @created 04-ÈýÔÂ-2012 16:49:12
 */
public interface DAO {

	/**
	 * 
	 * @param e
	 */
	public void addEmployee(Employee e);

	/**
	 * 
	 * @param e
	 */
	public void delEmployee(Employee e);

	/**
	 * 
	 * @param e
	 */
	public void editEmployee(Employee e);

	public List<Employee> listAllEmployees();

}