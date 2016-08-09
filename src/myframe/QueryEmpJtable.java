package myframe;

import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;
import java.util.Vector;
//声明各个列名
//String[] col_names = {"姓名",
//		"性别",
//		"地址",
//		"员工类型",
//		"基本工资"};
////数据初始化并存储在二维数组
//Object[][] tableData = {
//		{"Kathy", "Smith","Snowboarding", new Integer(5), new Boolean(false)},
//		{"John", "Doe","Rowing", new Integer(3), new Boolean(true)},
//		{"Sue", "Black","Knitting", new Integer(2), new Boolean(false)},
//		{"Jane", "White","Speed reading", new Integer(20), new Boolean(true)},
//		{"Joe", "Brown", "Pool", new Integer(10), new Boolean(false)}
//};
////接着表格使用这些数据和列名构造一个表格
//tableModel = new DefaultTableModel(tableData,col_names);
//table = new JTable(tableModel);
//table.setRowHeight(20);
////向容器添加表格
//JScrollPane scrollPane = new JScrollPane(table);
//table.setFillsViewportHeight(true);
//this.add(scrollPane);
////		this.add(table.getTableHeader(), BorderLayout.PAGE_START);
////		this.add(table,BorderLayout.CENTER);
//
////改变每一列的宽度
//TableColumn column = null;
//for (int i = 0; i < 5; i++) {
//	column = table.getColumnModel().getColumn(i);
//	if (i == 2) {
//		column.setPreferredWidth(150);
//	} else {
//		column.setPreferredWidth(50);
//	}
//}
//TableColumn sportColumn = table.getColumnModel().getColumn(3);
//JComboBox comboBox = new JComboBox();
//comboBox.addItem("年薪制员工");
//comboBox.addItem("月薪制员工");
//comboBox.addItem("临时工");
//sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

public class QueryEmpJtable {
	public static void main(String args []) throws SQLException{
		// A 注册oracle jdbc 驱动
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		// B 创建数据库连接
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","tiger");
		// C 创建 Statment 对象
		Statement stmt = conn.createStatement();
		// D 执行 select 指令,查询结果放在ResultSet对象
		ResultSet rset = stmt.executeQuery("select * FROM employee");
		// E 将查询结果显示于JTable
		DisplayResult(rset);
		rset.close();
		stmt.close();
		conn.close();
	}
	public static void DisplayResult(ResultSet rset)throws SQLException{
		JFrame frame = new JFrame();
		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		JScrollPane resultPan = new JScrollPane(table);
		ResultSetMetaData md = rset.getMetaData();
		Vector col_names = new Vector();
		// 取得 ResultSet 对象内字段数量
		int no_cols = md.getColumnCount();
		System.out.println(no_cols);
		//取得 ResultSet 对象内字段的名称
		for(int i=0;i<no_cols;i++)
			col_names.add(md.getColumnLabel(i+1));
			System.out.println(col_names);
		Vector tableData = new Vector();
		//取出每个数据
		while(rset.next())
		{
			Vector rowData = new Vector();
			for(int i=1; i<=no_cols;i++){
				rowData.add(rset.getString(i));
			}
			// 将每笔数据 rowData 加入tableData
			tableData.add(rowData);
		}
		tableModel.setDataVector(tableData,col_names);
		frame.setTitle("Employees information");
		frame.getContentPane().add(resultPan);
		frame.pack();
		frame.setVisible(true);
	}
	public QueryEmpJtable() {
	}
}