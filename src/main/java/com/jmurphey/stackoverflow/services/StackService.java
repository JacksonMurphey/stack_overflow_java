package com.jmurphey.stackoverflow.services;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jmurphey.stackoverflow.models.Answer;
import com.jmurphey.stackoverflow.models.Question;
import com.jmurphey.stackoverflow.models.Tag;
import com.jmurphey.stackoverflow.repositories.AnswerRepo;
import com.jmurphey.stackoverflow.repositories.QuestionRepo;
import com.jmurphey.stackoverflow.repositories.TagRepo;



@Service
public class StackService {
	
	@Autowired
	private QuestionRepo questionRepo;
	
	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private AnswerRepo answerRepo;
	
	
	
	
// --- Create Object Methods ---
//	public Question createQuestion(Question question) {
//		
//		List<Tag> questionTags = new ArrayList<Tag>();
//		for(String subject : question.separateTags()) {
//			
//			Tag addTag = this.tagRepo.findBySubject(subject).orElse(null);
//			
//			if (addTag == null) {
//				addTag = new Tag(subject);
//				this.tagRepo.save(addTag);
//			}
//			
//			if (!questionTags.contains(addTag)) {
//				questionTags.add(addTag);
//			}
//			
//		}
//		
//		question.setTags(questionTags);
//		return this.questionRepo.save(question);
//	}
	
	
	
	public Tag getOneTag(String subject) {
		return this.tagRepo.findBySubject(subject);
	}
	
	public ArrayList<Tag> splitSubjectTags(String tags) {
		
		ArrayList<Tag> tagsForQ = new ArrayList<Tag>();
		
		String[] tagsToSplit = tags.split(", ");
		for(String s : tagsToSplit) {
			
			if(this.tagRepo.existsBySubject(s)) {
				Tag tagToAdd = this.getOneTag(s);
				tagsForQ.add(tagToAdd);
				
			} else {
				Tag newTag = new Tag();
				newTag.setSubject(s);
				this.tagRepo.save(newTag);
				tagsForQ.add(this.getOneTag(s));
			}
		}
		return tagsForQ;
	}
	
	
	public Question createQuestion(Question question) {
		question.setTags(this.splitSubjectTags(question.getSeparateTags()));
		return this.questionRepo.save(question);
	}
	
	
//	public Question createQuestion(Question newQuestion) {
//			
//			// Adding Tag to Database
//			
//			List<Tag> questionTags = new ArrayList<Tag>();
//			for (String subject : newQuestion.splitTags()) {
//				
//				// Check if new incoming tag is in the database
//				Tag tagToBeAdded = this.tagRepo.findBySubject(subject).orElse(null);
//				
//				// If the new tag is not in the database, then add this tag to it
//				if (tagToBeAdded == null) {
//					tagToBeAdded = new Tag(subject);
//					this.tagRepo.save(tagToBeAdded);
//					}
//				
//				// Add tag to questionTags List, so latter the new question could be constructed with its related tags
//				if(!questionTags.contains(tagToBeAdded)) {
//					questionTags.add(tagToBeAdded);
//				}	
//				
//			}
//			
//			// Adding Question to Database
//			
//			// Setting attribute "tags" (Tag List) from the instance newQuestion as questionTags
//			newQuestion.setTags(questionTags);
//			
//			// Adding new question with related tags to database
//			return this.questionRepo.save(newQuestion);
//		
//	}
//	
	public Tag createTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public Answer createAnswer(Answer answer) {
		return answerRepo.save(answer);
	}
	
	
	
// --- Find One Method(s) ---
	public Question getOneQuestion(Long id) {
		return this.questionRepo.findById(id).orElse(null);
	}
	
	public Answer getOneAnswer(Long id) {
		return this.answerRepo.findById(id).orElse(null);
	}
	
	
// --- Find All Method(s) ---
	public List<Question> findQuestions(){
		return questionRepo.findAll();
	}
	
	
	
// --- Add M2M Relationships ---
	public void addTag(Question question, Tag tag) {
		List<Tag> tagList = question.getTags();
		tagList.add(tag);
		questionRepo.save(question);
		
		// Might not need this considering I am splitting and adding the tags in my createQuestion method. 
	}
	
	
	
// --- Delete by Id ---
	public void deleteQuestion(Long id) {
		questionRepo.deleteById(id);
	}
	
	public void deleteAnswer(Long id) {
		answerRepo.deleteById(id);
	}
}
