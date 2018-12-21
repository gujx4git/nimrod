package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.ApiEntity;
import com.gioov.nimrod.system.entity.AttachmentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Mapper
@Component("attachmentMapper")
public interface AttachmentMapper extends CrudMapper<AttachmentEntity, Long> {

    /**
     * 指定 authority ，获取 API
     *
     * @param authority API authority
     * @return ApiEntity
     */
    ApiEntity getOneByAuthority(@Param("authority") String authority);

    AttachmentEntity getOneByGuid(@Param("guid") String guid);
}
