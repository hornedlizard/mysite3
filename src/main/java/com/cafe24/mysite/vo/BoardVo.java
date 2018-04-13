package com.cafe24.mysite.vo;

public class BoardVo {
	private long no;
	private String title;
	private String content;
	private String regdate;
	private long hits;
	private long groupNo;
	private long orderNo;
	private long depth;
	private UserVo userVo;
	private boolean isDelete;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public long getHits() {
		return hits;
	}
	public void setHits(long hits) {
		this.hits = hits;
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
	public UserVo getUserVo() {
		return userVo;
	}
	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
	public boolean getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regdate=" + regdate + ", hits="
				+ hits + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userVo=" + userVo
				+ ", isDelete=" + isDelete + "]";
	}
	
}
