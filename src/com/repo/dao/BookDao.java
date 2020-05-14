package com.repo.dao;

import java.sql.Timestamp;

public class BookDao {
	private Integer bookId;
	private String bookName;
	private String authorName;
	private Timestamp issueDate;
	private Timestamp submissionDate;
	private Timestamp availabilityDate;
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
	public Timestamp getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}
	public Timestamp getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Timestamp submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Timestamp getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(Timestamp availabilityDate) {
		this.availabilityDate = availabilityDate;
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
