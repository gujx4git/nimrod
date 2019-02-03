package com.gioov.gujx.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.gujx.CurrencyEntity;
import com.gioov.gujx.mapper.CurrencyMapper;
import com.gioov.gujx.service.CurrencyService;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiEntity;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

	@Autowired
	private CurrencyMapper currencyMapper;

	@Override
	public Pagination.Result<CurrencyEntity> pageAll(Integer page, Integer rows, Sort sort) {
		List<CurrencyEntity> currencyEntityList;
		Pagination.Result<CurrencyEntity> paginationResult = new Pagination().new Result<>();
		currencyEntityList = currencyMapper.pageAll(new Pageable(page, rows, sort));
		if (currencyEntityList != null) {
			paginationResult.setRows(currencyEntityList);
			LOGGER.info("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");
		}
		int count = currencyMapper.countAll();
		paginationResult.setTotal(count);
		return paginationResult;

	}

	@Override
	public CurrencyEntity getOne(Long id) {
		return currencyMapper.getOne(id);
	}

	@Override
	public CurrencyEntity insertOne(CurrencyEntity currencyEntity) throws BaseResponseException {
		// CurrencyEntity currencyEntity1 = new CurrencyEntity();
		// currencyEntity1.setCurrname(currencyEntity.getCurrname());
		// currencyEntity1.setCurrcode(currencyEntity.getCurrcode());

		currencyMapper.insertOne(currencyEntity);
		return currencyEntity;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int deleteAll(List<Long> idList) {
		return currencyMapper.deleteAll(idList);
	}
	
	 @Override
	    @Transactional(rollbackFor = Throwable.class)
	    public CurrencyEntity updateOne(CurrencyEntity currencyEntity) {
	        CurrencyEntity currencyEntity1 = currencyMapper.getOne(currencyEntity.getId());
	        currencyEntity1.setCurrname(currencyEntity.getCurrname());
	        currencyEntity1.setCurrcode(currencyEntity.getCurrcode());
	       
	        currencyMapper.updateOne(currencyEntity1);
	        return currencyEntity1;
	    }
}
