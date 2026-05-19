package jp.co.sfrontier.ss3.game.entity.user;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ユーザ管理テーブルのエンティティ
 */
@Getter
@Setter
@Entity
@Table(name="user_information_tbl")
@NoArgsConstructor
public class UserInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // 自動採番
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="mail_address")
	private String mailAddress;
	
	@Column(name="password")
	private String password;
	
	@Column(name="status")
	private int status;
	
	@Column(name="create_datetime")
	private LocalDateTime createDatetime;
	
	@Column(name="update_datetime")
	private LocalDateTime updateDatetime;
	
	@Column(name="version")
	private Integer version;

}
