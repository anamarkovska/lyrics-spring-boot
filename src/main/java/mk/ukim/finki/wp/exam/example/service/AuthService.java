package mk.ukim.finki.wp.exam.example.service;


import mk.ukim.finki.wp.exam.example.model.User;

public interface AuthService {
    User login(String username, String password);
}
