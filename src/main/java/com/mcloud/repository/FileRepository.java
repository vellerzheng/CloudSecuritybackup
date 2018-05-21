package com.mcloud.repository;

import com.mcloud.model.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/4.
 */
@Repository
public interface FileRepository extends JpaRepository<FilesEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update FilesEntity files set files.description=:qDescription, files.status = :qStatus, files.version = :qVersion," +
            "files.createtime=:qCreatetime, files.updatetime=:qUpdatetime, files.userByUserId.id=:qUsersId where files.id=:qId")
    void updateFiles(@Param("qDescription")String description,@Param("qStatus")int status, @Param("qVersion")int version,
                     @Param("qCreatetime")Date createtime, @Param("qUpdatetime")Date updateime, @Param("qUsersId") int usersId, @Param("qId") int id);


    @Query(value = "select  fs from FilesEntity fs where fs.userByUserId.id =:uid")
    List<FilesEntity> findFilesEntityByUserIdEndsWith(@Param("uid") int userId);

    @Query("select fl from FilesEntity fl where fl.fileName =:qFileName")
    List<FilesEntity> findByFileNameEndsWith(@Param("qFileName") String fileName);

    @Query("select fid from FilesEntity fid where  fid.id =:qId")
    FilesEntity findByFilesEntityId(@Param("qId")int id);

    @Query("select max(id) from FilesEntity")
    int findLastIdFormFilesEntity();

}
