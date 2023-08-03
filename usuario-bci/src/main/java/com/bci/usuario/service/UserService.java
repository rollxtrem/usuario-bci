package com.bci.usuario.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bci.usuario.entity.UserEntity;
import com.bci.usuario.repositoy.UserRepository;
import com.bci.usuario.util.JwtTokenUtil;

@Service
public class UserService {
	@Autowired
	private UserRepository usuarioRepository;

	public List<UserEntity> getAll() {

		return usuarioRepository.findAll();
	}

	public UserEntity getUsuarioEntityById(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public UserEntity save(UserEntity usuario) {
		usuario.setCreated(new Date());
		usuario.setModified(new Date());
		usuario.setIsactive(true);
		UserEntity respuesta = usuarioRepository.save(usuario);
		return respuesta;
	}

	public UserEntity autenticateUser(String user, String password) {

		UserEntity respuesta = usuarioRepository.findUserByEmailAndPassword(user, password);

		if (respuesta == null)
			return respuesta;
		if (!respuesta.isIsactive())
			return respuesta;
		else {
			respuesta.setLast_login(new Date());
			respuesta.setToken(user);
			respuesta.setToken(JwtTokenUtil.generateToken(user));
			//respuesta = usuarioRepository.save(respuesta);
			return respuesta;
		}
	}

}
