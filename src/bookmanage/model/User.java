package bookmanage.model;
import java.util.ArrayList;
/**
 * @Description 用户实体类
 * @author com.javayihao.top
 */
public class User extends ArrayList<Book>{
	private String accout;//账号
	private String pass;//密码
	//空参构造方法
	public User() {
		super();
	}
	//全参构造方法
	public User(String accout, String pass) {
		super();
		this.accout = accout;
		this.pass = pass;
	}
	//set get方法
	public String getAccout() {
		return accout;
	}
	public void setAccout(String accout) {
		this.accout = accout;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
