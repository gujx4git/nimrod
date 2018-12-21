package com.gioov.nimrod.user.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.user.entity.UserPasswordResetEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Mapper
@Component("userPasswordResetMapper")
public interface UserPasswordResetMapper extends CrudMapper<UserPasswordResetEntity, Long> {

}
