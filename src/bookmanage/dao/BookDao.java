package bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmanage.model.Book;
import bookmanage.utils.DbUtil;

/**
 * @Description 连接数据库工具类
 * @author com.javayihao.top
 */
public class BookDao {
	/**
	 * 根据图书编号查询图书
	 * 
	 * @param id
	 *            入参：图书编号
	 * @return 返回当前查询到的图书实体
	 */
	public Book getBookById(String id) {
		Connection connection = DbUtil.getConnection();
		String sql = "select id,name,num,price,type from t_book where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// 存在图书，封装返回
				Book book = new Book(rs.getString("id"), rs.getString("name"), rs.getInt("num"), rs.getFloat("price"),rs.getString("type"));
				DbUtil.close(connection, ps);// 关闭连接
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有图书
	}

	/**
	 * 根据图书id删除图书
	 * 
	 * @param id
	 *            入参：图书编号
	 * @return 返回是否删除成功
	 */
	public boolean deleteBootByid(String id) {
		Connection connection = DbUtil.getConnection();
		String sql = "delete from t_book where id=?";

		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			if (!ps.execute()) {// 删除成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询所有图书
	 * 
	 * @return 返回图书列表
	 */
	public ArrayList<Book> getBookList() {
		ArrayList<Book> books = new ArrayList<Book>();// 用户存放图书的集合
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "select id,name,num,price,type from t_book";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {// 存在图书，封装返回
				Book book = new Book(rs.getString("id"), rs.getString("name"), rs.getInt("num"), rs.getFloat("price"),rs.getString("type"));
				books.add(book);
			}
			DbUtil.close(connection, ps);// 关闭连接
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;// 没有图书
	}

	/**
	 * 插入图书
	 * 
	 * @param book
	 *            入参
	 * @return 是否成功
	 */
	public boolean insertBoot(Book book) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into t_book(id,name,num,price,type)values(?,?,?,?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, book.getId());
			ps.setString(2, book.getName());
			ps.setInt(3, book.getNum());
			ps.setFloat(4, book.getPrice());
			ps.setString(5, book.getType());
			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	/**
	 * 修改图书信息
	 * 
	 * @param book
	 *            入参
	 * @return 是否修改完成
	 */
	public boolean updateBook(Book book) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "update t_book set name=?,num=?,price=?,type=? where id=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, book.getName());
			ps.setInt(2, book.getNum());
			ps.setFloat(3, book.getPrice());
			ps.setString(4, book.getType());
			ps.setString(5, book.getId());

			if (!ps.execute()) {
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}
}
