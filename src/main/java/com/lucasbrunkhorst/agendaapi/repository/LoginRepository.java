package com.lucasbrunkhorst.agendaapi.repository;

import com.lucasbrunkhorst.agendaapi.domain.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    Optional<Login> findByUserLogin(String login);

}
