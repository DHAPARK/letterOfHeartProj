package com.project.letterOfHeart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.letterOfHeart.service.TreeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TreeController {

	private final TreeService treeService;

	@GetMapping("/users/tree")
	public String myTree() {
		return "tree";
	}

}
