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
public class RoleResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String roleId;
	private String roleName;
	private String roleDescription;
	private String roleStatus;
}
