package com.example.demo.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bug;
import com.example.demo.repository.BugRepository;

@Service
public class BugService {
	
	@Autowired
	private BugRepository bugRepository;
	
	public void addBug(@Valid Bug bug) {
		bugRepository.save(bug);
	}

}