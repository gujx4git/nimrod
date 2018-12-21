package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.OperationLogEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Mapper
@Component("operationLogMapper")
public interface OperationLogMapper extends CrudMapper<OperationLogEntity, Long> {
}
