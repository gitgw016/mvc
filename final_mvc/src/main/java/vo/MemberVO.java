package vo;

import java.util.Date;

public class MemberVO {

	private int num;
	private String id;
	private String pass;
	private String name;
	private int age;
	private String gender;
	private String joinYN;
	private Date regdate;
	private Date updatedate;

	public MemberVO() {}

	public MemberVO(String id, String pass, String name, int age, String gender) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	public MemberVO(int num, String id, String pass, String name, int age, String gender, String joinYN, Date regdate,
			Date updatedate) {
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.joinYN = joinYN;
		this.regdate = regdate;
		this.updatedate = updatedate;
	}
	
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJoinYN() {
		return joinYN;
	}
	public void setJoinYN(String joinYN) {
		this.joinYN = joinYN;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name + ", age=" + age
				+ ", gender=" + gender + ", joinYN=" + joinYN + ", regdate=" + regdate + ", updatedate=" + updatedate
				+ "]";
	}
	
}
