package bookmanage.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bookmanage.dao.BookDao;
import bookmanage.model.Book;

/**
 * 自定义图书列表面板
 * @author com.javayihao.top
 */
public class ListPanel extends JPanel {
	// 从数据库中取出信息
	// rowData用来存放行数据
	// columnNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// 构造函数
	public ListPanel() {
		ArrayList<Book> books = new BookDao().getBookList();
		columnNames = new Vector();
		// 设置列名
		columnNames.add("图书编号");
		columnNames.add("图书名称");
		columnNames.add("图书价格");
		columnNames.add("图书数量");
		columnNames.add("图书类型");
		rowData = new Vector();
		for (int i = 0; i < books.size(); i++) {
			//实例化每一行数据
			Vector hang = new Vector();
			hang.add(books.get(i).getId());
			hang.add(books.get(i).getName());
			hang.add(books.get(i).getPrice());
			hang.add(books.get(i).getNum());
			hang.add(books.get(i).getType());
			// 加入到rowData
			rowData.add(hang);
		}
		// 初始化Jtable
		jt = new JTable(rowData, columnNames);
		// 初始化 jsp
		jsp = new JScrollPane(jt);
		this.add(jsp);
	}
}
