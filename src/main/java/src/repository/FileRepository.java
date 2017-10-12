package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.model.FilesEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by vellerzheng on 2017/10/4.
 */
@Repository
public interface FileRepository extends JpaRepository<FilesEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update FilesEntity files set files.description=:qDescription,files.userByUserId.id=:qUsersId," +
            "files.fileName=:qFileName,files.pubDate=:qPubDate where files.id=:qId")
    void updateFiles(@Param("qDescription")String description,@Param("qUsersId") int usersId,@Param("qFileName")String fileName,
                     @Param("qPubDate")Date pubDate,@Param("qId") int id);

    @Query(value = "select  fs from FilesEntity fs where fs.userByUserId.id =:uid")
    List<FilesEntity> findByFilesEntityEEndsWith(@Param("uid") int userId);
}
