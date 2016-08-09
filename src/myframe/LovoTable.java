package myframe;

import java.awt.Container;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 * 表格组件
 * @author ibm
 *
 */
public class LovoTable extends JTable {
	/**表头*/
	private Vector<String> headVector = new Vector<String>();
	/**数据*/
	private Vector<LovoVector> dataVector = new Vector<LovoVector>();
	
	private JScrollPane jsp = new JScrollPane(this);
	
	/**
	 * 构造方法
	 * @param jf 容器或窗体
	 * @param headArray 表头字段
	 */
	public LovoTable(Container jf,String[] headArray){
		jf.add(jsp);
		
		for(String str : headArray){
			this.headVector.add(str);
		}
		this.updateTable();
		
		JTableHeader jth=this.getTableHeader();
		jth.setReorderingAllowed(false);//设置表头不可拖动重排列
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置表格一次只能选择一行
	}
	
	private void updateTable(){
		this.setModel(new DefaultTableModel(this.dataVector,this.headVector));
	}
	
	/**
	 * 更新表格数据
	 * @param v
	 */
	public void updateLovoTable(Vector<LovoVector> v){
		this.dataVector = v;
		this.updateTable();
	}
	
	/**
	 * 添加数据
	 * @param v
	 */
	public void addData(LovoVector v){
		this.dataVector.add(v);
		this.updateTable();
	}
	
	/**
	 * 删除选中行
	 * @return 删除的行号，没有选中返回-1
	 */
	public int removeKey(){
		int index = this.getSelectedRow();
		if(index != -1){
			this.dataVector.remove(index);
			this.updateTable();
		}
		return index;
	}
	
	/**
	 * 得到选中项，没有选中表格返回为null
	 * @return
	 */
	public LovoVector getKey(){
		int index = this.getSelectedRow();
		
		if(index != -1){
			LovoVector lv = this.dataVector.get(index);
			return lv;
		}
		else{
			return null;
		}
	}
	
	/**
	 * 修改选中项，如果没选中返回-1
	 * @return 
	 */
	public int updateData(LovoVector data){
		int index = this.getSelectedRow();
		
		if(index != -1){
			this.dataVector.set(index,data);
			this.updateTable();
			return index;
		}
		else{
			return -1;
		}
	}
	
	/**
	 * 设置大小和位置
	 * @param x X坐标
	 * @param y Y坐标
	 * @param width 宽度
	 * @param height 高度
	 */
	public void setSizeAndLocation(int x,int y,int width,int height){
		this.jsp.setBounds(x, y, width, height);
	}
	
	/**
	 * 设置表格不可编辑
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isCellEditable(int row,int column){
		return false;
	}
	
//	public TableCellRenderer getCellRenderer(int row, int column) {
//        TableCellRenderer renderer = super.getCellRenderer(row, column);
//        if (renderer instanceof JLabel) {
//            ((JLabel) renderer).setHorizontalAlignment(JLabel.CENTER);
//        }
//        return renderer;
//    }
}
