package myframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import emp.Employee;
import emp.EmployeeService;
import emp.EmployeeServiceImpl;


public class TitlePanel extends JPanel {
	private JPanel jp;
	private MyFrame frame;
	private LovoTable table = new LovoTable(this,new String[]{"姓名","性别","地址","类型","工资"});
	private EmployeeService service;
	private List<Employee> empList;
	private Vector<LovoVector> data;
	public TitlePanel(final MyFrame frame) {
		this.frame = frame;

		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		this.setSize(500, 300);
		//调用业务组件
		service = new EmployeeServiceImpl();
		empList = service.listAllEmployees();

		data = this.getVector(empList);

		//完成表格数据的设置
		table.updateLovoTable(data);

		table.setSizeAndLocation(0,0,500,230);

		jp = new JPanel();
		JButton jb_add = new JButton("添加");
		jb_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.switchPanel("AddEmpPanel");
			}
		});
		JButton jb_edit = new JButton("修改");
		jb_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.switchPanel("AlterPabel");
			}
		});
		JButton jb_del = new JButton("删除");
		jb_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int swicthint = JOptionPane.showOptionDialog(null, "是否删除？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(swicthint == 0){
					System.out.println(table.getSelectedRow());
					service.delEmployee(empList.get(table.getSelectedRow()));
					table.removeKey();
				}
			}
		});
		JButton jb_exit = new JButton("退出");
		jb_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int swicthint = JOptionPane.showOptionDialog(null, "是否退出？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if(swicthint == 0){
					System.exit(0);
				}
			}
		});
		jp.add(jb_add);
		jp.add(jb_edit);
		jp.add(jb_del);
		jp.add(jb_exit);
		this.add(jp,BorderLayout.SOUTH);
	}
	//完成List到Vector的转换
	private Vector<LovoVector> getVector(List<Employee> list){
		Vector<LovoVector> vector = new Vector<LovoVector>();

		for(Employee emp : list){
			LovoVector v = new LovoVector(emp.getEmployeeID());

			v.add(emp.getEmployeeName());
			v.add(emp.getGender());
			v.add(emp.getAddress());
			v.add(emp.getEmployeeType());
			v.add(emp.getSalary());

			vector.add(v);
		}

		return vector;
	}
	public JPanel getJp() {
		return jp;
	}
	public void setJp(JPanel jp) {
		this.jp = jp;
	}
	public MyFrame getFrame() {
		return frame;
	}
	public void setFrame(MyFrame frame) {
		this.frame = frame;
	}
	public LovoTable getTable() {
		return table;
	}
	public void setTable(LovoTable table) {
		this.table = table;
	}
	public EmployeeService getService() {
		return service;
	}
	public void setService(EmployeeService service) {
		this.service = service;
	}
	public List<Employee> getEmpList() {
		return empList;
	}
	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	public Vector<LovoVector> getData() {
		return data;
	}
	public void setData(Vector<LovoVector> data) {
		this.data = data;
	}

}
