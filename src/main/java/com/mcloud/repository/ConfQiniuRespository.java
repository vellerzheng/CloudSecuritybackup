package com.mcloud.repository;

import com.mcloud.model.ConfQiniuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfQiniuRespository extends JpaRepository<ConfQiniuEntity,Integer> {
}
