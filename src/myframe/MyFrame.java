package myframe;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.Enumeration;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;


public class MyFrame extends JFrame{
	private TitlePanel tp;
	private AddEmpPanel addp;
	private AlterPabel altp;
	private Container contentP;
	//布局管理器
	private CardLayout card = new CardLayout();
	
	public MyFrame() {
		this.init();
		
		tp = new TitlePanel(this);
		addp = new AddEmpPanel(this);
		altp = new AlterPabel(this);
		
		contentP.add(tp,"TitlePanel");
		contentP.add(addp,"AddEmpPanel");
		contentP.add(altp,"AlterPabel");

		this.setVisible(true);
	}

	/**初始化主窗体*/
	public void init(){
		//设置全局字体
		this.initGlobalFontSetting(new Font("Dialog",0,12));
		//获得面板
		this.contentP = this.getContentPane();
		this.setLayout(card);
		this.setTitle("T88员工管理系统");
		//设置窗体大小不能被改变
//		this.setResizable(false);
		//设置JFrame的布局管理器为CardLayout
		this.setSize(500, 300);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
	}
	/**
	 * 用于切换面板
	 * */
	public void switchPanel(String panelName){
		//卡片布局管理器中切换面板的方法，第一个参数，是容器面板
		card.show(this.getContentPane(), panelName);
	}
	//设置全局字体
	public static void initGlobalFontSetting(Font fnt)
	{
		FontUIResource fontRes = new FontUIResource(fnt);
		for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MyFrame();
	}

	public TitlePanel getTp() {
		return tp;
	}

	public void setTp(TitlePanel tp) {
		this.tp = tp;
	}

	public Container getContentP() {
		return contentP;
	}

	public void setContentP(Container contentP) {
		this.contentP = contentP;
	}

	public CardLayout getCard() {
		return card;
	}

	public void setCard(CardLayout card) {
		this.card = card;
	}

	public AddEmpPanel getAddp() {
		return addp;
	}

	public void setAddp(AddEmpPanel addp) {
		this.addp = addp;
	}

	public AlterPabel getAltp() {
		return altp;
	}

	public void setAltp(AlterPabel altp) {
		this.altp = altp;
	}
}
