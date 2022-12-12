package com.project.letterOfHeart.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "USERS_SEQ_GENERATOR"
	    , sequenceName = "USERS_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class Users {

    @Id @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "USERS_SEQ_GENERATOR")
	@Column(name = "USERS_ID")
    private Long id;

    private String u_Id;                // 유저 아이디
    private String password;            // 유저 비밀번호
    
    @Column(length = 8)
    private String nickname;            // 유저 닉네임
    private LocalDateTime createDate;   // 가입 일자
    
    @OneToMany(mappedBy = "users")
    private List<Tree> tree = new ArrayList<Tree>();
    
    public void addTree(Tree tree) {
    	tree.setUsers(this);
    	this.tree.add(tree);
    }
}
