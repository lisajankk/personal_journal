package com.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.User;
import com.bookStore.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository uRepo;
	
	public void save(User u) {
		uRepo.save(u);
	}

	public List<User> getAllUsers(){
		return uRepo.findAll();
	}
	
	public User getUserById(int id) {
		return uRepo.findById(id).get();
	}

    public User getUserByEmail(String email) {
        return uRepo.findByEmail(email);
    }
	
	public void deleteById(int id) {
		uRepo.deleteById(id);
	}
}
