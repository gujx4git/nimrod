package com.gioov.nimrod.common.mail.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.common.mail.entity.MailEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Mapper
@Component("mailMapper")
public interface MailMapper extends CrudMapper<MailEntity, Long> {

}
