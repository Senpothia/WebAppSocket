package com.michel.tcp.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michel.tcp.Commande;

public interface CommandeRepo  extends JpaRepository<Commande, Integer>{

}
