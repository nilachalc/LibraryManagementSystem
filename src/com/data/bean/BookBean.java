package com.data.bean;

import java.util.Date;

public class BookBean {
	private Integer bookId;
	private String bookName;
	private String authorName;
	private Date issueDate;
	private Date submissionDate;
	private Date availabilityDate;
	private Character readytoreIssue;
	private Integer currentUserIssued;
	private Integer genreId;
	
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Date getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(Date availaibilityDate) {
		this.availabilityDate = availaibilityDate;
	}
	public Character getReadytoreIssue() {
		return readytoreIssue;
	}
	public void setReadytoreIssue(Character readytoreIssue) {
		this.readytoreIssue = readytoreIssue;
	}
	public Integer getCurrentUserIssued() {
		return currentUserIssued;
	}
	public void setCurrentUserIssued(Integer currentUserIssued) {
		this.currentUserIssued = currentUserIssued;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
}
