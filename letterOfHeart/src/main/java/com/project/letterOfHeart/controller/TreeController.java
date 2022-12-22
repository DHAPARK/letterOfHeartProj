package com.project.letterOfHeart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.letterOfHeart.domain.Tree;
import com.project.letterOfHeart.service.TreeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TreeController {

	private final TreeService treeService;

	@GetMapping("/{id}/edit")
	public String designTree(Model model) {
		Tree tree = new Tree();
		return "designTree";
	}
}
