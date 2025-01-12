package com.example.controller;

import com.example.mapper.RecommendationMapper;
import com.example.model.Comment;
import com.example.model.Recommendation;
import com.example.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daily-discover/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationMapper recommendationMapper;

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("")
    public List<Recommendation> getAllRecommendations() {
        return recommendationMapper.getAllRecommendations();
    }

    @GetMapping("/{id}")
    public Recommendation getRecommendation(@PathVariable Long id) {
        // Fetch the recommendation and its comments
        Recommendation recommendation = recommendationService.getRecommendationById(id);
        List<Comment> comments = recommendationService.getCommentsByRecommendationId(id);
        recommendation.setComments(comments); // Set comments in the recommendation object
        return recommendation;
    }
}