package com.bci.usuario.repository;


import com.bci.usuario.entity.UserEntity;
import com.bci.usuario.repositoy.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void findUserByEmailAndPassword() {
        assertThat(userRepository.findUserByEmailAndPassword("juan2@rodriguez.org","hunter2222").getEmail()).contains("juan2@rodriguez.org");
    }

    @BeforeEach
    public void prepareData() {
        UserEntity user = new UserEntity();
        user.setName("Juan Rodriguez");
        user.setEmail("juan2@rodriguez.org");
        user.setPassword("hunter2222");
        user.setCreated(new Date());
        user.setModified(new Date());
        user.setIsactive(true);
        userRepository.save(user);
        
    }
}