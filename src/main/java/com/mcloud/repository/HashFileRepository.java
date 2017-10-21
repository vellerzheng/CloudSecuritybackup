package com.mcloud.repository;

import com.mcloud.model.FilesHashEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vellerzheng on 2017/10/19.
 */
@Repository
public interface HashFileRepository extends JpaRepository<FilesHashEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update FilesHashEntity fhs set fhs.fileHash = :qFileHash, fhs.filesIdByFileId.id =:qFilesId," +
            "fhs.aliyunHash=:qAliyunHash, fhs.neteaseHash =:qNeteaseHash,fhs.qcloudHash=:qQcloudHash," +
            "fhs.qiniuHash =:qQiniuHash,fhs.upyunHash=:qUpyunHash where fhs.id=:qId")
    void updateFiles(@Param("qFileHash")String fileHash,  @Param("qFilesId") int filesId,
                     @Param("qAliyunHash")String aliyunHash,@Param("qNeteaseHash")String neteaseHash,@Param("qQcloudHash") String qcloudHash,
                     @Param("qQiniuHash")String qiniuHash,@Param("qUpyunHash")String upyunHash, @Param("qId") int id);


    @Query("select fs from FilesHashEntity fs where fs.filesIdByFileId.id =:qFileId")
    FilesHashEntity findEntityByFileId(@Param("qFileId")int fileId);

}
