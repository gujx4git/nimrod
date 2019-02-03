/**
 * 
 */
package com.gioov.gujx.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.gioov.common.mybatis.CrudMapper;
import com.gioov.gujx.CurrencyEntity;

/**
 * @author gujx
 *
 */
@Mapper
@Component("currencyMapper")
public interface CurrencyMapper extends CrudMapper<CurrencyEntity, Long> {

}
