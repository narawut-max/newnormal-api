package com.it.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String userId;
	private String userUsername;
	private String userPassword;
	private String userCardId;
	private String userTitle;
	private String userFirstname;
	private String userLastname;
	private String userGender;
	private String userBirthday;
	private String userBlood;
	private String userDisease;
	private String userAllergy;
	private String userDepartment;
	private String userGraduate;
	private String userProfession;
	private String userPosition;
	private String userPhone;
	private String userEmail;
	private String userStatus;
	private String userAddrass;
	private String zipCode;
	private String roleId;
	
	//Get-Set
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserUsername() {
		return userUsername;
	}
	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserCardId() {
		return userCardId;
	}
	public void setUserCardId(String userCardId) {
		this.userCardId = userCardId;
	}
	public String getUserTitle() {
		return userTitle;
	}
	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}
	public String getUserFirstname() {
		return userFirstname;
	}
	public void setUserFirstname(String userFirstname) {
		this.userFirstname = userFirstname;
	}
	public String getUserLastname() {
		return userLastname;
	}
	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserBlood() {
		return userBlood;
	}
	public void setUserBlood(String userBlood) {
		this.userBlood = userBlood;
	}
	public String getUserDisease() {
		return userDisease;
	}
	public void setUserDisease(String userDisease) {
		this.userDisease = userDisease;
	}
	public String getUserAllergy() {
		return userAllergy;
	}
	public void setUserAllergy(String userAllergy) {
		this.userAllergy = userAllergy;
	}
	public String getUserDepartment() {
		return userDepartment;
	}
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	public String getUserGraduate() {
		return userGraduate;
	}
	public void setUserGraduate(String userGraduate) {
		this.userGraduate = userGraduate;
	}
	public String getUserProfession() {
		return userProfession;
	}
	public void setUserProfession(String userProfession) {
		this.userProfession = userProfession;
	}
	public String getUserPosition() {
		return userPosition;
	}
	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserAddrass() {
		return userAddrass;
	}
	public void setUserAddrass(String userAddrass) {
		this.userAddrass = userAddrass;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	
}
