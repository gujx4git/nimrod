/**
 * 
 */
package com.gioov.gujx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.common.mybatis.Pageable;
import com.gioov.gujx.entity.StudentScoreEntity;
import com.gioov.nimrod.system.entity.DictionaryEntity;


/**
 * @author gujx
 *
 */
@Mapper
@Component("StudentScoreMapper")
public interface StudentScoreMapper extends CrudMapper<StudentScoreEntity, Long> {


    	
}
