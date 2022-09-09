package com.newsManager.entity;

import java.io.Serializable;

/**
 * @author XCDD  邮箱：2207346898@qq.com
 * @description
 * @vision
 */
public class Topic implements Serializable {
    private static final long serialVersionUID = 2786883833042050721L;
    private int tid;
	private String tname;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
