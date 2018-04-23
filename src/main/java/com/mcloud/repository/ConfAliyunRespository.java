package com.mcloud.repository;

import com.mcloud.model.ConfAliyunEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfAliyunRespository extends JpaRepository<ConfAliyunEntity,Integer> {

}
