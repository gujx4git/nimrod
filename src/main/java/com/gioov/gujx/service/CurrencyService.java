package com.gioov.gujx.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.gujx.CurrencyEntity;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiEntity;


public interface CurrencyService  {
	 /**
     * 指定附件 id ，分页获取所有附件
     *
     * @param page 页
     * @param rows 每页显示数量
     * @return Pagination.Result<AttachmentEntity>
     */
    Pagination.Result<CurrencyEntity> pageAll(Integer page, Integer rows, Sort sort);

	CurrencyEntity  getOne(Long id);
	
	CurrencyEntity insertOne(CurrencyEntity currencyEntity) throws BaseResponseException;

	 
    int deleteAll(List<Long> idList);

     public  CurrencyEntity updateOne(CurrencyEntity currencyEntity) ;


   
}
