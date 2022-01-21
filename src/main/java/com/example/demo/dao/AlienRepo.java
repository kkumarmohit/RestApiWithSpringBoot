package com.example.demo.dao;

import com.example.demo.model.Alien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlienRepo extends JpaRepository<Alien, Integer> //JpaRepo extends CrudRepo
{
    List<Alien> findByTech(String tech); //Use findBy or GetBy, and it should end by the name of the property
    List<Alien> findByAidGreaterThan(int aid);
    @Query("from Alien where tech=?1 order by aname") //syntax of jdql. ?1 gives the argument number
    List<Alien> findByTechSorted(String tech);
}

//SPRING BOOT DATA REST HELPS TO ELIMINATE THE NEED FOR CONTROLLER BELOW ARE SOME EXAMPLES WITH THIS IT WILL COVER MANY
//BASIC CASES. URLS WILL BE GENERATED BASED ON REST

//@RepositoryRestResource(collectionResourceRel="aliens",path="aliens")
//public interface AlienRepo extends JpaRepository<Alien, Integer>