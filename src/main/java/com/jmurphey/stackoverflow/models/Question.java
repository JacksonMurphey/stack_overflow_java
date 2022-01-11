package com.jmurphey.stackoverflow.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.validation.constraints.NotBlank;


import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "questions")
public class Question {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A Question must be posed: Min. 10 characters needed")
	private String aQuestion;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	
	
// --- Joining Tables ---
	
	@OneToMany(mappedBy = "question", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Answer> answers;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "tags_questions",
			joinColumns = @JoinColumn(name = "question_id"),
			inverseJoinColumns = @JoinColumn(name = "tag_id")
			)
	private List<Tag> tags;
	
	
	
	
// --- Transient Field: variable I do not want to save, will use just to split/parse tag objects separated by commas ---
	
	@Transient
//	@Pattern(regexp="^(([a-zA-Z\\s])+$|([a-zA-Z\\s]+,)[a-zA-Z\\s]+){1,2}$", message = "Tags must be separated by commas, Max 3 tags")
	private String separateTags;
	
	
	
		
	
// --- Constructor ---
	
	public Question() {}

	public Question(String aQuestion, List<Tag> tags) {
    	this.aQuestion = aQuestion;
		this.tags = tags;
    }
	
	
// --- Split my Tag Objects separated by Commas -- 
	
//	public String[] splitTags() {
//		return this.separateTags.split("\\s*,\\s*");
//	}
	
	
	
	
// --- Getters / Setters ---
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getaQuestion() {
		return aQuestion;
	}

	public void setaQuestion(String aQuestion) {
		this.aQuestion = aQuestion;
	}
	

	public String getSeparateTags() {
		return separateTags;
	}

	public void setSeparateTags(String separateTags) {
		this.separateTags = separateTags;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@PrePersist
	protected void onCreate() {	
		this.createdAt = new Date(); 
	}
		
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	
}
