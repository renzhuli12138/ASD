package bookmanage.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import bookmanage.dao.BookDao;
import bookmanage.dao.UserDao;

/**
 * @Description 图书管理界面类
 * @author com.javayihao.top
 */
//定义窗体类BookFrame继承JFrame，实现ActionListener接口
public class BookFrame extends JFrame implements ActionListener {
	// 定义顶部菜单栏
	private JMenuBar menuBar;
	// 定义顶部菜单 首页图书管理、关于系统两个菜单（读者可以根据自己需求增加）
	private JMenu menuUser,menuManage, menuAbout;
	// 定义菜单项 图书列表 、关于、以及退出
	private JMenuItem itemUserlist,itemList, itemBook, itemAbout, itemExit;
	//定义容器
	private Container container;
	//定义卡片布局
	private CardLayout cardlayout;
	//定义首页、图书更新、图书列表三个面板
	private CrudBookPanel crudBookPanel;
	private ListUser listUser;
	private ListPanel listPanel;
	private IndexPanel indexPanel;
	// 定义图书操作对象并实例化
	private BookDao bookDao = new BookDao();
	private UserDao userDao = new UserDao();
	/**
	 * 初始化窗体
	 */
	public void init() {
		// 调用用于初始化内容窗体的方法
		initFrame();
		// 窗口标题
		this.setTitle("图书系统-图书管理");
		// 窗体大小
		this.setSize(600, 400);
		// 设置图标
		this.setIconImage((new ImageIcon("images/logo.jpg")).getImage());
		// 设置可关闭进程
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 获得屏幕宽度
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		// 获得屏幕高度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		// 居中显示
		this.setLocation((width - 500) / 2, (height - 400) / 2);
		// 设置窗体可见
		this.setVisible(true);
		// 可改变窗体大小
		this.setResizable(false);
	}

	/**
	 * 初始化窗体内容
	 */
	public void initFrame() {
		// 实例化菜单栏
		menuBar = new JMenuBar();
		menuUser = new JMenu("用户管理");
		menuManage = new JMenu("图书管理");
		menuAbout = new JMenu("关于我们");
		// 实例化菜单项图书列表、退出、关于并添加事件监听
		itemUserlist = new JMenuItem("用户列表");
		itemUserlist.addActionListener(this);
		itemUserlist.setActionCommand("listuser");

		itemBook = new JMenuItem("图书更新");
		itemBook.addActionListener(this);// 设置监听
		itemBook.setActionCommand("itembook");

		itemList = new JMenuItem("图书列表");
		itemList.addActionListener(this);// 设置监听
		itemList.setActionCommand("listbook");

		itemExit = new JMenuItem("退出");
		itemExit.addActionListener(this);// 设置监听
		itemExit.setActionCommand("exit");

		itemAbout = new JMenuItem("关于");
		itemAbout.addActionListener(this);// 设置监听
		itemAbout.setActionCommand("about");

		menuUser.add(itemUserlist);
		// 菜单图书管理添加图书列表和退出项目
		menuManage.add(itemList);
		menuManage.add(itemBook);
		menuManage.add(itemExit);
		// 关于系统添加关于菜单项
		menuAbout.add(itemAbout);
		// 菜单栏添加菜单那图书管理以及关于系统
		menuBar.add(menuUser);
		menuBar.add(menuManage);
		menuBar.add(menuAbout);
		// 把菜单栏添加到窗体
		this.setJMenuBar(menuBar);
		// 实例化增删改查面板
		indexPanel = new IndexPanel();
		// 实例化卡片布局
		cardlayout = new CardLayout();
		// 窗口容器中添加組件（使用边界布局）
		container = getContentPane();
		container.setLayout(cardlayout);
		// 将首页面板放进内容面板
		container.add(indexPanel, "indexPanel");
	}

	/*
	 * 监听按钮的事件方法
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// 事件监听
		if (e.getActionCommand().equals("about"))
		{// 关于
			JOptionPane.showMessageDialog(BookFrame.this, "广告位招租");
		} else if (e.getActionCommand().equals("otherBtn")) {
			JOptionPane.showMessageDialog(BookFrame.this, "贷款");
		} else if (e.getActionCommand().equals("exit")) {// 退出
			System.exit(0);
		} else if (e.getActionCommand().equals("listbook")) {
			//实例化列表面板，将其放进容器 使用卡片布局展示
			listPanel = new ListPanel();
			container.add(listPanel, "listPanel");
			cardlayout.show(container, "listPanel");
		} else if (e.getActionCommand().equals("itembook")) {
			//实例化增删改查面板，将其放进容器 使用卡片布局展示
			crudBookPanel = new CrudBookPanel();
			container.add(crudBookPanel, "crudBookPanel");
			cardlayout.show(container, "crudBookPanel");
		}else if (e.getActionCommand().equals("listuser")){
			listUser = new ListUser();
			container.add(listUser, "listUser");
			cardlayout.show(container, "listUser");
		}
	}
}
