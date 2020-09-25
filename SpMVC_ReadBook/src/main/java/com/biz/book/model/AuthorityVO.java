package com.biz.book.model;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Alias("Authority")
public class AuthorityVO {

	private long seq;
	private String m_userid;
	private String m_role;

}
