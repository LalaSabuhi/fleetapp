package com.kindsonthegenius.fleetapp.repositories;
import com.kindsonthegenius.fleetapp.models.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
