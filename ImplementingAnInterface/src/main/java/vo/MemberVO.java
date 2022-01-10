package vo;

public class MemberVO {
	
	private int num;
	private String id;
	private String pass;
	private String name;
	
	public MemberVO() {}

	public MemberVO(String id, String pass, String name) {
		this.id = id;
		this.pass = pass;
		this.name = name;
	}
	
	public MemberVO(int num, String id, String pass, String name) {
		super();
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
	}


	
	// getter & setter & toString
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name+"]";
	}
	
}









