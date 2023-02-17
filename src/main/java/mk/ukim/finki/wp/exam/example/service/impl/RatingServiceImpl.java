package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Rating;
import mk.ukim.finki.wp.exam.example.repository.RatingRepository;
import mk.ukim.finki.wp.exam.example.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    final private RatingRepository repository;

    public RatingServiceImpl(RatingRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Rating> listAllRatings() {
        return this.repository.findAll();
    }
}
