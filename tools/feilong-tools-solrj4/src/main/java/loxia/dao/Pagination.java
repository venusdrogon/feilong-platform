package loxia.dao;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 520741651051423364L;

	public Pagination(){}
	public Pagination(List<T> items, long count){
		this.items = items;
		this.count = count;
	}
	public Pagination(List<T> items, long count, int currentPage, int totalPages, int start, int size){
		this.items = items;
		this.count = count;
		this.currentPage = currentPage;
		this.totalPages = totalPages;
		this.start = start;
		this.size = size;
	}
	private List<T> items;
	private long count;
	private int currentPage;
	private int totalPages;
	private int start;
	private int size;
	private String sortStr;
		
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSortStr() {
		return sortStr;
	}
	public void setSortStr(String sortStr) {
		this.sortStr = sortStr;
	}
	public boolean isFirstPage(){
		return getCurrentPage() == 1;
	}
	public boolean isLastPage(){
		return getCurrentPage() >= getTotalPages();
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
