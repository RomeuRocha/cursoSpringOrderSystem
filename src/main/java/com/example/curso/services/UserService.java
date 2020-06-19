package com.example.curso.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.curso.entities.User;
import com.example.curso.repositories.UserRepository;
import com.example.curso.services.exceptions.DataBaseException;
import com.example.curso.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired //injeta dependencia automática
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}

	public User findById(long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User user) {
		return repository.save(user);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
		
	}
	
	public User update(Long id,User obj) {
		try {
			User entity = repository.getOne(id);//prepara objeto monitorado, para após fazer manipulação no banco
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}



