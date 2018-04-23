package com.mcloud.repository;

import com.mcloud.model.ConfUpyunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfUpyunRespository extends JpaRepository<ConfUpyunEntity,Integer> {

}
