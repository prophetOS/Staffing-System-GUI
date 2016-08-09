package myframe;

import java.util.Vector;
/**
 * 表格行集合
 * @author ibm
 *
 */
public class LovoVector extends Vector{
	/**主键，唯一标识id，但在窗体中不显示*/
	private int key;
	
	public LovoVector(int key){
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
