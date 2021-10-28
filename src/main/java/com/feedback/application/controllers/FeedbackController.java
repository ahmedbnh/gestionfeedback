package com.feedback.application.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.feedback.application.repositories.FeedbackRepository;
import com.feedback.application.entities.Feedback;
import com.feedback.application.entities.UserModel;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FeedbackController {
	@Autowired
	private RestTemplate rest;
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	 @Autowired
	    @LoadBalanced
	    private RestTemplate loadBalanced;
	 @Autowired
		private RestTemplateBuilder rtb;
		
	//get all feedbacks
	 @Autowired
		private FeedbackRepository fb_rep;
	 @GetMapping("/feedbacks") 
	 public List<Object> getfeedback() {
	 	Object[] objects = restTemplateBuilder.build().getForObject("http://authrecruit/findallusers", Object[].class);
	 	return Arrays.asList(objects);
	 }
		@Autowired
		
		//get all feedbacks
		@GetMapping("/all-feedbacks")
		public ResponseEntity<?> allFeedbacks() {
			List<Feedback> list_feedbacks = fb_rep.findAll();
			if (list_feedbacks.size() > 0) {
				return new ResponseEntity<List<Feedback>>(list_feedbacks, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("No feedback is found", HttpStatus.NOT_FOUND);
			}
		}
		@PostMapping("/add-feedback/{id_user}")
		public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback, @PathVariable String id_user) {
			try {
			Feedback f = new Feedback();

			f.setFeedback_text_f(feedback.getFeedback_text_f());
			f.setNote_f(feedback.getNote_f());
			Object u = rtb.build().getForObject("http://localhost:8087/get-user/"+id_user, Object.class);

			//UserModel u = user_rep.findById(id_user).get();
			/*if (user_rep.findById(id_user).isPresent()) {
				feedback.setUser_model_f(u);
			} else {
				return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
			}*/
			f.setUser_model_f(u);

			fb_rep.save(f);
			return new ResponseEntity<>("Feedback added successfully", HttpStatus.OK);

			} catch (Exception ex) {
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
			}
			   }
		
		@DeleteMapping("/delete_feedback/{id}")
		public ResponseEntity<?> deleteFeedback(@PathVariable("id") String id){
		try{
			fb_rep.deleteById(id);
		return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
		}catch(Exception e){
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		}
		
}
	
