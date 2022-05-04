package com.locophotoapp.locophotoapp.repository;

import com.locophotoapp.locophotoapp.bean.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface ImageRepository extends JpaRepository<Image, String> {

    @Query("SELECT e FROM Image e WHERE e.city LIKE %?1% AND (e.deleted is null or e.deleted=false)")
    List<Image> findByCityContaining(String city);

    @Query("SELECT e FROM Image e WHERE e.userID =?1 AND (e.deleted is null or e.deleted=false)")
    List<Image> findByUserID(String id);
}