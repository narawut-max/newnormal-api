package com.it.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class UserEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userUsername;
	private String userPassword;
	private String userCardId;
	private String userHnId;
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
	private String userProfessionId;
	private String userProfession;
	private String userPosition;
	private String userPhone;
	private String userEmail;
	private String userStatus;
	private String userAddrass;
	private String zipCode;
	private String roleId;
	
}
