package com.bci.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.usuario.entity.UserEntity;
import com.bci.usuario.service.UserService;
import com.bci.usuario.vo.ErrorVO;
import com.bci.usuario.vo.LoginVO;


import io.swagger.annotations.ApiOperation;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService usuarioSrv;

	@GetMapping(value = "/all")
	@ApiOperation("Lista todos los usuarios")
	public ResponseEntity<Object> getAll() {
		List<UserEntity> respuesta = usuarioSrv.getAll();
		if (respuesta.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(respuesta);
	}

	@GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
	@ApiOperation("Obtener usuario por ID")
	public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
		UserEntity respuesta = usuarioSrv.getUsuarioEntityById(id);
		if (respuesta == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(respuesta);
	}

	@PostMapping()
	@ApiOperation("Crear nuevo usuario")
	public ResponseEntity<Object> saveUser(@RequestBody UserEntity usuario) {
		try {
			UserEntity respuesta = usuarioSrv.save(usuario);
			if (respuesta == null) {
				return ResponseEntity.badRequest().build();
			}
			return ResponseEntity.ok(respuesta);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(new ErrorVO("El correo ya se encuentra registrado"), HttpStatus.BAD_REQUEST);
		} catch (ConstraintViolationException ex) {
			return new ResponseEntity<>(new ErrorVO(ex.getConstraintViolations().iterator().next().getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorVO("Se presento un error desconocido"), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/autenticate")
	@ApiOperation("Valida y autentica al usuario")
	public ResponseEntity<Object> autenticateUser(@RequestBody LoginVO request) {
		String user = request.getUser();
		String password = request.getPassword();

		UserEntity respuesta = usuarioSrv.autenticateUser(user, password);
		if (respuesta == null) {
			return new ResponseEntity<>(new ErrorVO("Credenciales invalidas"), HttpStatus.UNAUTHORIZED);
		} else if (!respuesta.isIsactive()) {
			return new ResponseEntity<>(new ErrorVO("Usuario inactivo"), HttpStatus.UNAUTHORIZED);
		} else {
			return ResponseEntity.ok(respuesta.getToken());
		}
	}
}
