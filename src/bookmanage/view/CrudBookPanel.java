package bookmanage.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bookmanage.dao.BookDao;
import bookmanage.model.Book;

/**
 * @Description 用于图书增删改查面板
 * @author com.javayihao.top
 */
public class CrudBookPanel extends JPanel implements ActionListener {
	// 定义首页按钮、图书列表按钮、 其他功能按钮,增加图书、删除图书、修改图书、查询图书
	private JButton addBtn, deleteBtn, updateBtn, findBtn;
	// 定义标签 底部信息标签、 图书编号、 图书名称、 图书数量、 图书价格
	private JLabel idLabel, nameLabel, numLabel, priceLabel,typeLabel;
	// 定义图书编号、 名称、 数量、 价格文本框
	private JTextField idJTextField, nameJTextField, numJTextField, priceJTextField,typeJTextField;
	// 定义文本对象
	private String bookIdText;
	private String bookNameText;
	private String bookNumText;
	private String bookPriceText;
	private String bookTypeText;
	// 图书数量和价格
	private Integer numBook;
	private Float priceBook;
	// 定义对象BookDao
	private BookDao bookDao;

	public CrudBookPanel() {
		bookDao = new BookDao();//实例化图书操作对象
		// 实例化增删改查按钮
		addBtn = new JButton("增加图书");
		addBtn.addActionListener(this);// 设置图书增加按钮监听
		addBtn.setActionCommand("addbook");
		deleteBtn = new JButton("删除图书");
		deleteBtn.addActionListener(this);// 设置图书删除按钮监听
		deleteBtn.setActionCommand("deletebook");
		updateBtn = new JButton("修改图书");
		updateBtn.addActionListener(this);// 设置图书修改按钮监听
		updateBtn.setActionCommand("updatebook");
		findBtn = new JButton("查询图书");
		findBtn.addActionListener(this);// 设置图书查询按钮监听
		findBtn.setActionCommand("findbook");
		// 实例化图书编号 名称 数量 价格标签
		idLabel = new JLabel("图书编号");
		nameLabel = new JLabel("图书名称");
		priceLabel = new JLabel("图书价格");
		numLabel = new JLabel("图书数量");
		typeLabel = new JLabel("图书类型");
		// 实例化文本框
		idJTextField = new JTextField(10);
		nameJTextField = new JTextField(10);
		numJTextField = new JTextField(10);
		priceJTextField = new JTextField(10);
		typeJTextField = new JTextField(10);
		this.setLayout(new GridLayout(8, 2, 2, 2));
		// 给增删改查面板添加图书编号 名称 数量 价格标签以及文本框
		this.add(idLabel);
		this.add(idJTextField);
		this.add(nameLabel);
		this.add(nameJTextField);
		this.add(priceLabel);
		this.add(priceJTextField);
		this.add(numLabel);
		this.add(numJTextField);
		this.add(typeLabel);
		this.add(typeJTextField);
		// 给增删改查面板添加图书编号 名称 数量 价格按钮
		this.add(addBtn);
		this.add(deleteBtn);
		this.add(updateBtn);
		this.add(findBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("addbook")) {// 添加图书
			addbook();
		} else if (e.getActionCommand().equals("deletebook")) {// 删除图书
			deleteBook();
		} else if (e.getActionCommand().equals("updatebook")) {// 修改图书
			updateBook();
		} else if (e.getActionCommand().equals("findbook")) {// 查询图书
			findBook();
		}
	}

	/**
	 * 查询图书
	 */
	private void findBook() {
		bookIdText = idJTextField.getText().trim().toString();
		Book book = bookDao.getBookById(bookIdText);
		if (bookIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书编号不能为空");
		} else if (book != null) {// 当前输入的图书编号存在
			try {
				nameJTextField.setText(book.getName());// 填充图书名称文本框
				numJTextField.setText(book.getNum() + "");// 将数字类型转成字符串并填充文本框
				priceJTextField.setText(book.getPrice() + "");// 将数字类型转成字符串并填充文本框
				typeJTextField.setText(book.getType() + "");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "图书查询异常  请联系管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "图书不存在");
		}

	}

	/**
	 * 修改图书
	 */
	private void updateBook() {
		bookIdText = idJTextField.getText().trim().toString();
		bookNameText = nameJTextField.getText().trim().toString();
		bookNumText = numJTextField.getText().trim().toString();
		bookPriceText = priceJTextField.getText().trim().toString();
		bookTypeText = typeJTextField.getText().trim().toString();
		if (bookIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书编号不能为空");
		} else if (bookNameText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书名称不能为空");
		} else if (bookNumText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书数量不能为空");
		} else if (bookPriceText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书价格不能为空");
		} else if (bookTypeText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书类型不能为空");
		} else {
			// 将图书数量和图书价格转成对应的数字类型
			if (bookDao.getBookById(bookIdText) == null) {// 图书不存在
				JOptionPane.showMessageDialog(this, "输入正确的图书编号");
			} else {
				try {
					numBook = Integer.parseInt(bookNumText);
					priceBook = Float.parseFloat(bookPriceText);
					bookDao.updateBook(new Book(bookIdText, bookNameText, numBook, priceBook, bookTypeText));
					JOptionPane.showMessageDialog(this, "图书修改成功");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "输入正确的图书数量和价格");
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 删除图书
	 */
	private void deleteBook() {
		bookIdText = idJTextField.getText().trim().toString();
		if (bookIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书编号不能为空");
		} else if (bookDao.getBookById(bookIdText) != null) {// 当前输入的图书编号是否存在
			try {
				bookDao.deleteBootByid(bookIdText);
				JOptionPane.showMessageDialog(this, "图书删除成功");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "图书删除异常  请联系管理员");
			}
		} else {
			JOptionPane.showMessageDialog(this, "图书不存在");
		}

	}

	/**
	 * 增加图书
	 */
	private void addbook() {
		bookIdText = idJTextField.getText().trim().toString();
		bookNameText = nameJTextField.getText().trim().toString();
		bookNumText = numJTextField.getText().trim().toString();
		bookPriceText = priceJTextField.getText().trim().toString();
		bookTypeText = typeJTextField.getText().trim().toString();
		if (bookIdText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书编号不能为空");
		} else if (bookNameText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书名称不能为空");
		} else if (bookNumText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书数量不能为空");
		} else if (bookPriceText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书价格不能为空");
		} else if (bookTypeText.equals("")) {
			JOptionPane.showMessageDialog(this, "图书类型不能为空");
		} else {
			// 将图书数量和图书价格转成对应的数字类型
			if (bookDao.getBookById(bookIdText) != null) {// 编号重复
				JOptionPane.showMessageDialog(this, "图书编号重复");
			} else {
				try {
					numBook = Integer.parseInt(bookNumText);
					priceBook = Float.parseFloat(bookPriceText);
					bookDao.insertBoot(new Book(bookIdText, bookNameText, numBook, priceBook,bookTypeText));
					JOptionPane.showMessageDialog(this, "图书增加成功");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(this, "输入正确的图书数量和价格");
					e.printStackTrace();
				}
			}
		}

	}
}
