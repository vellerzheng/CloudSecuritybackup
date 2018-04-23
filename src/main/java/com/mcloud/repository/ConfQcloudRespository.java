package com.mcloud.repository;

import com.mcloud.model.ConfQcloudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfQcloudRespository extends JpaRepository<ConfQcloudEntity,Integer> {

}
