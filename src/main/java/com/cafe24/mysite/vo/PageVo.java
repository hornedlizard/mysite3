package com.cafe24.mysite.vo;

public class PageVo {
	private int page;
	private int totalData;
	private int dataPerPage = 5;
	private int displayPage = 5;
	private int totalPage;
	private int startData;
	private int endData;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	public PageVo() {
		this.page = 1;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public int getTotalData() {
		return totalData;
	}
	public void setTotalData(int totalData) {
		this.totalData = totalData;
	}
	public int getDataPerPage() {
		return dataPerPage;
	}
	public void setDataPerPage(int dataPerPage) {
		this.dataPerPage = dataPerPage;
	}
	public int getDisplayPage() {
		return displayPage;
	}
	public void setDisplayPage(int displayPage) {
		this.displayPage = displayPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartData() {
		return startData;
	}
	public void setStartData(int startData) {
		this.startData = startData;
	}
	public int getEndData() {
		return endData;
	}
	public void setEndData(int endData) {
		this.endData = endData;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	
	public void paging() {
		this.totalPage = (int) Math.ceil(totalData / (double)dataPerPage);
		this.startData = (page-1) * dataPerPage;
		this.endData = page * dataPerPage;
		this.endPage = (int) (Math.ceil(page/(double)displayPage) * displayPage);
		this.startPage = endPage - displayPage + 1;
		this.prev = startPage == 1 ? false : true;
		this.next = endPage * dataPerPage >= totalData ? false : true; 
	}
	
}
