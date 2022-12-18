package com.project.letterOfHeart.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TreeRepository {

	private final EntityManager em;
}
