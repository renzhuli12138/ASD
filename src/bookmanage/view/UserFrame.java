package bookmanage.view;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import bookmanage.dao.UserDao;
import bookmanage.model.User;

/**
 * @Description 登陆主界面
 * @author com.javayihao.top
 */
public class UserFrame extends JFrame implements ActionListener {
	// 定义变量
	private JLabel jLabelAccout, jlLabelpass;// 账号 密码标签
	private JButton loginBtn, registBtn,reset;// 登陆 注册按钮
	private JTextField jFieldAccout;// 账号 密码文本框
	private JPasswordField  jFieldpass;
	private UserDao userDao = new UserDao();// 定义用户操作对象
	// 初始化窗体

	public void init() {
		FlowLayout f = new FlowLayout();// 流式佈局
		f.setAlignment(FlowLayout.LEFT);// 设置组件的对齐方式，默认为居中显示组件
		f.setHgap(30);// 设置组件的水平间距
		f.setVgap(30);// 设计组件的垂直间距，水平和垂直间距默认为5像素
		jLabelAccout = new JLabel("账  号");
		add(jLabelAccout);
		jFieldAccout = new JTextField(15);// 输入框 参数代表长度
		add(jFieldAccout);
		jlLabelpass = new JLabel("密  码");
		add(jlLabelpass);
		jFieldpass = new JPasswordField(15);// 输入框 参数代表长度
		add(jFieldpass);
		loginBtn = new JButton(" 登陆 ");// 直接在创建时候给定参数
		loginBtn.addActionListener(this);// 设置事件监听
		loginBtn.setActionCommand("login");// 设置监听识别命令
		add(loginBtn);
		registBtn = new JButton();
		registBtn.setText(" 注册 ");// 也可以单独给名称
		registBtn.addActionListener(this);// 设置事件监听
		registBtn.setActionCommand("regist");// 设置监听识别命令
		add(registBtn);
		reset =new JButton();
		reset.setText("重置");
		reset.addActionListener(this);
		reset.setActionCommand("reset");
		add(reset);
		setLayout(f);// 设置组件容器采用流布局管理器
		// 初始化窗口

		this.setTitle("图书系统-登陆");// 窗口标题
		this.setSize(300, 220);// 窗体大小
		this.setIconImage((new ImageIcon("images/logo.jpg")).getImage());// 设置图标
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置可关闭进程
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;// 获得屏幕宽度
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;// 获得屏幕高度
		this.setLocation((width - 500) / 2, (height - 400) / 2);// 剧中显示
		this.setResizable(true);// 可改变窗体大小
		this.setVisible(true);// 设置窗体可见
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String accout = jFieldAccout.getText().trim().toString();// 获取账户，去掉后面得空格
		String pass = jFieldpass.getText().trim().toString();// 获取密码，去掉后面得空格
		if (e.getActionCommand().equals("login")) {// 登陆
			User user = userDao.getUserByAccout(accout); // 查询
			if (user != null) {// 用户存在,验证密码和输入得是否相等
				if (user.getPass().equals(pass)) {
					this.dispose();//关闭当前窗口
					//this.setVisible(false);// 关闭当前窗口
					new BookFrame().init();// 打开图书主窗体
				} else {
					JOptionPane.showMessageDialog(null, "密码错误", "信息", JOptionPane.INFORMATION_MESSAGE);
				}

			} else {// 用户不存在
				JOptionPane.showMessageDialog(null, "用户不存在", "信息", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getActionCommand().equals("regist")) {
			if (accout.equals("") || pass.equals("")) {
				JOptionPane.showMessageDialog(null, "用户名和密码不能为空", "信息", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// 账户是否重复
				if (userDao.getUserByAccout(accout) != null) {
					JOptionPane.showMessageDialog(null, "账户重复", "信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					User user = new User(accout, pass);
					boolean flag = userDao.insertUser(user);
					if (flag) {
						JOptionPane.showMessageDialog(null, "注册成功，请登陆", "信息", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "注册异常", "信息", JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		}
			else if (e.getActionCommand().equals("reset")){
			jFieldAccout.setText("");
			jFieldpass.setText("");
		}

	}
}
