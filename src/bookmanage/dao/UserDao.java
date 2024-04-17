package bookmanage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bookmanage.model.User;
import bookmanage.utils.DbUtil;

/**
 * @Description 连接数据库工具类
 * @author com.javayihao.top
 */
public class UserDao {
	/**
	 * 根据用户账号查询用户
	 *
	 * @param accout
	 *            入参:用户账号
	 * @return 查找的用户
	 */
	public User getUserByAccout(String accout) {
		Connection connection = DbUtil.getConnection();
		String sql = "select accout,pass from t_user where accout=?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, accout);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {// 存在用户，封装用户返回
				User user = new User(rs.getString("accout"), rs.getString("pass"));
				DbUtil.close(connection, ps);// 关闭连接
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean insertUser(User user) {
		Connection connection = DbUtil.getConnection();// 获得数据库连接对象
		String sql = "insert into t_user(accout,pass)values(?,?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getAccout());
			ps.setString(2, user.getPass());
			if (!ps.execute()) {// 成功
				DbUtil.close(connection, ps);// 关闭连接
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;// 失败
	}

	public ArrayList<User> getUserByAccout() {
		ArrayList<User> users = new ArrayList   <User>();
		Connection connection = DbUtil.getConnection();
		String sql = "select accout,pass from t_user";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {// 存在用户，封装用户返回
				User user = new User(rs.getString("accout"), rs.getString("pass"));
				users.add(user);// 关闭连接
			}
			DbUtil.close(connection, ps);
			return users;
		} catch (SQLException e) {

		}
		return null;
	}
}
