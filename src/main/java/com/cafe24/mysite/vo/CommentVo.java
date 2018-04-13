package com.cafe24.mysite.vo;

public class CommentVo {
	private long no;
	private String content;
	private String password;
	private String regdate;
	private long groupNo;
	private long orderNo;
	private long depth;
	private UserVo user;
	private long boardNo;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(long groupNo) {
		this.groupNo = groupNo;
	}
	public long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}
	public long getDepth() {
		return depth;
	}
	public void setDepth(long depth) {
		this.depth = depth;
	}
	public UserVo getUser() {
		return user;
	}
	public void setUser(UserVo user) {
		this.user = user;
	}
	public long getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}
	
	@Override
	public String toString() {
		return "CommentVo [no=" + no + ", content=" + content + ", password=" + password + ", regdate=" + regdate
				+ ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", user=" + user + ", boardNo="
				+ boardNo + "]";
	}
	
}
