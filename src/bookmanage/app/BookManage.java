package bookmanage.app;

import bookmanage.view.UserFrame;

/**
 * @Description 图书启动类
 * @author com.javayihao.top
 */
public class BookManage {
	public static void main(String[] args) {
		//实例化登陆界面 调用初始化方法init()
		UserFrame bf = new UserFrame();
		bf.init();
	}
}
