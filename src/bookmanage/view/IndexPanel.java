package bookmanage.view;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 系统欢迎界面
 *@author com.javayihao.top
 */

public class IndexPanel extends JPanel {
	private JLabel imgLabel;//图片标签
	public IndexPanel() {
		//首页标签设置图片
		imgLabel = new JLabel(new ImageIcon("images/index.jpg"));
		this.add(imgLabel);
	}
}
