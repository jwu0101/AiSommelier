package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class members_c1 {
	
	public static void main(String[] args) {
		
	}
	
	private String email;
	private String name;
	private String password;
	private String mobile;
	private String address;
	private LocalDate bday;
	private String type;
	private String status;
	private String createUser;
	private LocalDateTime createTime;
	private String updateUser;
	private LocalDateTime updateTime;
	
	public members_c1() {}
	public members_c1(String email, String name, String password, String mobile, String address, LocalDate bday,
			String type, String status, String createUser, LocalDateTime createTime, String updateUser,
			LocalDateTime updateTime) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.mobile = mobile;
		this.address = address;
		this.bday = bday;
		this.type = type;
		this.status = status;
		this.createUser = createUser;
		this.createTime = createTime;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
	}

	public String toString() {
		return email+"  "+name+"  "+mobile+"  "+address+"  "+
				"  "+bday+"  "+type+"  "+status+"  "+createUser+
				"  "+createTime+"  "+updateUser+"  "+updateTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LocalDate getBday() {
		return bday;
	}
	public void setBday(LocalDate bday) {
		this.bday = bday;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		DateTimeFormatter f = null;
		f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		createTime.format(f);
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		//System.out.println(updateTime);
		DateTimeFormatter f = null;
		f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		updateTime.format(f);
		//System.out.println(updateTime);
		this.updateTime = updateTime;
	}

	
	
	
	
}
