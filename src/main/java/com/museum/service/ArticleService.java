package com.museum.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.museum.dao.ArticleRepository;
import com.museum.entity.Article;
import com.museum.exception.ArticleNotFoundException;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public Article addArticle(Article article) {

		Article savedArticle = articleRepository.save(article);
		System.out.println("Added");
		return savedArticle;
	}

	public Collection<Article> getAllArticles() {
		List<Article> allArticle = articleRepository.findAll();
		return allArticle;

	}

	public Article getAttributeById(Integer id) {

		Article articleFound = articleRepository.findById(id)
				.orElseThrow(() -> new ArticleNotFoundException("Article given Id Not Found" + id));

		return articleFound;

	}

	public String deleteArticleById(Integer id) {

//		articleRepository.deleteById(id);
//
//		return "Article of id:" + id + " deleted";
		Article articleFound = articleRepository.findById(id)
				.orElseThrow(() -> new ArticleNotFoundException("Article given Id Not Found" + id));

		articleRepository.deleteById(id);

		return "Article of id:" + id + " deleted";

	}

	public Article updateArticleById(Integer id, Article updatedArticle) {

		Article articleFound = articleRepository.findById(id)
				.orElseThrow(() -> new ArticleNotFoundException("Article given Id Not Found" + id));

		articleFound.setName(updatedArticle.getName());
		articleFound.setCategory(updatedArticle.getCategory());
		articleFound.setCreateDate(updatedArticle.getCreateDate());
		articleFound.setCreateName(updatedArticle.getCreateName());

		Article article = articleRepository.save(articleFound);
		return article;

	}
}
