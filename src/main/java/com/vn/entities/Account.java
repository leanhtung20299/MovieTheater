package com.vn.entities;



import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Account", schema = "movietheater", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),
		@UniqueConstraint(columnNames = { "identity_card" }) })
public class Account implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;
	
	@Column(name = "address", columnDefinition = "NVARCHAR(255)")
	private String address;
	
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "email", columnDefinition = "VARCHAR(255)")
	private String email;
	
	@Column(name = "full_name", columnDefinition = "NVARCHAR(255)")
	private String fullName;
	
	@Column(name = "gender", columnDefinition = "NVARCHAR(255)")
	private String gender;
	
	@Column(name = "identity_card", columnDefinition = "VARCHAR(255)")
	private String identityCard;
	// Need edit
	@Column(name = "image", columnDefinition = "VARCHAR(255)")
	private String image;
	
	@Column(name = "password", columnDefinition = "VARCHAR(255)")
	private String password;
	
	@Column(name = "phone_number", columnDefinition = "VARCHAR(255)")
	private String phoneNumber;
	
	@Column(name = "register_date", updatable = false)
	private LocalDate registerDate;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "user_name", columnDefinition = "VARCHAR(255)", unique = true)
	private String userName;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Invoice> invoices;
	
	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AccountRole> accountRoles;

}
