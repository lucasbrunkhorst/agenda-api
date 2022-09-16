package com.lucasbrunkhorst.agendaapi.service;


import com.lucasbrunkhorst.agendaapi.domain.entities.Login;
import com.lucasbrunkhorst.agendaapi.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final LoginRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Optional<Login> optUser = repository.findByUserLogin(login);

        if(optUser.isEmpty()) {
            throw new UsernameNotFoundException(" User not found ");
        }

        Login user = optUser.get();
        return new User(user.getUserLogin(), user.getPassword(), new ArrayList<>());

    }

    public List<Login> listAll() {
        return repository.findAll();
    }

    public Login save(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        return repository.save(login);
    }

}
