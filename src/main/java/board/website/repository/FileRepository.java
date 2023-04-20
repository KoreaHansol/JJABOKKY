package board.website.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileRepository {
//    String getFileUrl(@Param())

    void saveFileUrl(@Param("filename") String fileName);
}
