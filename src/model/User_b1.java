package model;

import java.util.Date;

public class User_b1 {
private String email;
private String name;
private String password;
private String mobile;
private String address;
private String bday;
private String type;
private String status;
private String createUser;
private String createrTime;
private String updateUser;
private String updateTime;

public User_b1() {}
	
public User_b1(String email,String name,String password,String mobile,String address,String bday) {
	this.email=email;
	this.name=name;
	this.password=password;
	this.mobile=mobile;
	this.address=address;
	this.bday = bday;
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

public String getCreaterTime() {
	return createrTime;
}

public void setCreaterTime(String createrTime) {
	this.createrTime = createrTime;
}

public String getUpdateUser() {
	return updateUser;
}

public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
}

public String getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(String updateTime) {
	this.updateTime = updateTime;
}

public String getBday() {
	// TODO Auto-generated method stub
	return bday;
}
public void setBday(String bday) {
	this.bday= bday;
}
}
