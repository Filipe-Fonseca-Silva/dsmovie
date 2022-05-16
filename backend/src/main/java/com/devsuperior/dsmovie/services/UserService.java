package com.devsuperior.dsmovie.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsmovie.dto.UserDTO;
import com.devsuperior.dsmovie.entities.User;
import com.devsuperior.dsmovie.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	@Transactional(readOnly = true)
	public List<UserDTO> findAll() {
		List<User> list = repository.findAll();
		List<UserDTO> dto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return dto;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User user = obj.orElse(null);
		return new UserDTO(user);
	}

	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		entity.setId(dto.getId());
		entity.setEmail(dto.getEmail());
		entity = repository.save(entity);
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO dto) throws Exception {
		try {
			Optional<User> obj = repository.findById(id);
			User entity = obj.orElse(null);
			entity.setId(dto.getId());
			entity.setEmail(dto.getEmail());
			entity = repository.save(entity);
			return new UserDTO(entity);
		}
		catch (Exception e) {
			throw new Exception("" + e.getMessage());
		}
	}

	@Transactional
	public void delete(Long id) throws Exception {
		try {
			repository.deleteById(id);
		}
		catch (Exception e) {
			throw new Exception("" + e.getMessage());
		}
	}
}
