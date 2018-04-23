package com.mcloud.repository;

import com.mcloud.model.ConfNeteaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfNeteaseRespository extends JpaRepository<ConfNeteaseEntity,Integer> {
}
