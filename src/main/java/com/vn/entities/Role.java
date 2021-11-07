package com.vn.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Role", schema = "movietheater")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "role_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column(name = "role_name", columnDefinition = "VARCHAR(255)")
	private String roleName;
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
	private List<AccountRole> accountRoles;
	

}
