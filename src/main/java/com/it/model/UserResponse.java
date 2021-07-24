package com.it.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer userId;
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
	private String tmId;
	private String zipCode;
	private String roleId;
	private TreatmentResponse treatment;
}
