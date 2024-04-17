package bookmanage.model;
/**
 * @Description 图书实体类
 * @author com.javayihao.top
 */
public class Book {
	private String id;//图书编号
	private String name;//图书名称
	private int num;//数量
	private float price;//价格
	private String type;//图书
	//无参构造方法
	public Book() {
		super();
	}
	//全参构造方法
	public Book(String id, String name, int num, float price, String type) {
		super();
		this.id = id;
		this.name = name;
		this.num = num;
		this.price = price;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
