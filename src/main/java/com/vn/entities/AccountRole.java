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
@Table(name = "AccountRole", schema = "movietheater")
public class AccountRole implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "account_id", referencedColumnName = "account_id")
    private Account account;

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "role_id", referencedColumnName = "role_id")
    private Role role;
   
}
