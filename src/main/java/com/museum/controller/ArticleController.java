package com.museum.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.museum.entity.Article;
import com.museum.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
//http://localhost:8085/article/add

	@Autowired
	private ArticleService articleService;

	@PostMapping("/add")
	public ResponseEntity<?> addArticle(@RequestBody Article article) {
		try {
			Article savedArticle = articleService.addArticle(article);
			return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
		} catch (Exception e) {

			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8085/article/all
	@GetMapping("/all")
	public ResponseEntity<?> allArticles() {

		try {
			Collection<Article> articles = articleService.getAllArticles();
			return new ResponseEntity<>(articles, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8085/article/oneArticle/{id}
	@GetMapping("/oneArticle/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		try {
			Article found = articleService.getAttributeById(id);
			return new ResponseEntity<>(found, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8085/article/oneArticle/{id}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {

		try {
			String found = articleService.deleteArticleById(id);
			return new ResponseEntity<>(found, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8085/article/update/4
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateById(@PathVariable Integer id, @RequestBody Article updatedArticle) {

		try {
			Article update = updatedArticle = articleService.updateArticleById(id, updatedArticle);
			return new ResponseEntity<>(update, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
