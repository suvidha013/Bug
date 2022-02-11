package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Bug;
import com.example.demo.repository.BugRepository;
import com.example.demo.service.BugService;

@RestController
public class BugController {
	
	@Autowired
	private BugService bugService;
	
	@Autowired
	private BugRepository bugRepository;
	
	@PostMapping("/bug")
	@ResponseStatus(code = HttpStatus.CREATED)
	void addBug(@RequestBody @Valid Bug bug) {
		bugService.addBug(bug);
	}
	@GetMapping("/bug")
	public List<Bug> getAllBugs() {
		return bugRepository.findAll();
	}
	@GetMapping("/bug/{id}")
	public Bug getBugbyId(@PathVariable(value = "id") int id) {
		return bugRepository.findById(id);
	}
	@PutMapping("/bug/{id}")
	public ResponseEntity<Object> updateBug(@RequestBody Bug bug, @PathVariable int id) {
		Optional<Bug> bugRepo = Optional.ofNullable(bugRepository.findById(id));
		if(!bugRepo.isPresent())
			return ResponseEntity
					.notFound()
					.build();
		bug.setId(id);
		bugRepository.save(bug);
		
		return ResponseEntity
				.noContent()
				.build();
	}

}
