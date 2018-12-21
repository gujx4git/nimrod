package com.gioov.nimrod.system.mapper;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.nimrod.system.entity.OperationLogEntity2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author godcheese
 * @date 2018/2/22
 */
@Mapper
@Component("operationLogMapper2")
public interface OperationLogMapper2 extends CrudMapper<OperationLogEntity2, Long> {
}
