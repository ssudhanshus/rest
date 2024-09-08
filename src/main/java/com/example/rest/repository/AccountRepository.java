package com.example.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.entity.Account;

public interface AccountRepository extends JpaRepository <Account, Long>{

}
