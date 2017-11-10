package com.mcloud.repository;

import com.mcloud.model.UserAdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserAdviceRepository extends JpaRepository<UserAdviceEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UserAdviceEntity meg set meg.name=:qName, meg.email=:qEmail," +
            "meg.mainIdea=:qMainIdea, meg.messageDetail=:qMessageDetail, meg.submitTime=:qSubmitTime where meg.id=:qId")
    public void updateUser(@Param("qName") String name, @Param("qEmail")String email,
                           @Param("qMainIdea")String mainIdea, @Param("qMessageDetail")String Credential,
                           @Param("qSubmitTime")String submitTime, @Param("qId") Integer Id);
}
