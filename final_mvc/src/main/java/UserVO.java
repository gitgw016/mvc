
public class UserVO {
	
	private String name;
	private int age;
	
	public UserVO() {}
	
	public UserVO(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// getter setter toString
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserVO [name=" + name + ", age=" + age + "]";
	}
}	
