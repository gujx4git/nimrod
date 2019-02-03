package com.gioov.gujx.api;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.gujx.CurrencyEntity;
import com.gioov.gujx.service.CurrencyService;
import com.gioov.nimrod.common.constant.Api;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.system.entity.ApiEntity;


@RestController
@RequestMapping(value = "/currencyController/api/currency", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CurrencyRestController {

	// private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRestController.class);

	    private static final String CURRENCY = "/API/CURRENCY";
	    
	    @Autowired
	    private CurrencyService currencyService;
	    /**
	     * 分页获取所有币种
	     *
	     * @param page
	     * @param rows
	     * @return ResponseEntity<Pagination.Result  < CurrencyEntity>>
	     */
	    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + CURRENCY + "/PAGE_ALL')")
	    @GetMapping(value = "/page_all")
	    public ResponseEntity<Pagination.Result<CurrencyEntity>> pageAll(@RequestParam Integer page, @RequestParam Integer rows) {
	        Sort sort = new Sort(Sort.Direction.DESC, "id");
	        return new ResponseEntity<>(currencyService.pageAll(page, rows, sort), HttpStatus.OK);
	    }
	    
	    /**
	     * 指定  id ， 获取 currency 信息
	     *
	     * @param id 
	     * @return ResponseEntity<CurrencyEntity>
	     */
	    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + CURRENCY + "/ONE')")
	    @GetMapping(value = "/one/{id}")
	    public ResponseEntity<CurrencyEntity> getOne(@PathVariable Long id) {
	        return new ResponseEntity<>(currencyService.getOne(id), HttpStatus.OK);
	    }
	    
	    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + CURRENCY + "/ADD_ONE')")
	    @PostMapping(value = "/add_one")
	    public ResponseEntity<CurrencyEntity> addOne(@RequestParam String currname, @RequestParam String currcode) 
	    		  throws BaseResponseException {
	        CurrencyEntity currencyEntity = new CurrencyEntity();
	        currencyEntity.setCurrname(currname);
	        currencyEntity.setCurrcode(currcode);
	        
	        CurrencyEntity currencyEntity1 = currencyService.insertOne(currencyEntity);
	        return new ResponseEntity<>(currencyEntity1, HttpStatus.OK);
	    }
	    
	    /**
	     * 指定 Currency id list ，批量删除 Currency
	     *
	     * @param idList Currency id list
	     * @return ResponseEntity<Integer>
	     */
	    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + CURRENCY + "/DELETE_ALL')")
	    @PostMapping(value = "/delete_all")
	    public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
	        return new ResponseEntity<>(currencyService.deleteAll(idList), HttpStatus.OK);
	    }
	    /**
	     * 保存 currency
	     *
	     * @param id     currency id
	     * @param currname    名称
	     * @param currcode    代码
	     * @return ResponseEntity<CurrencyEntity>
	     */
	    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + CURRENCY + "/SAVE_ONE')")
	    @PostMapping(value = "/save_one")
	    public ResponseEntity<CurrencyEntity> saveOne(@RequestParam Long id, @RequestParam String currname, @RequestParam String currcode) {
	        CurrencyEntity currencyEntity = new CurrencyEntity();
	        currencyEntity.setId(id);
	        currencyEntity.setCurrname(currname);
	        currencyEntity.setCurrcode(currcode);
	        CurrencyEntity currencyEntity1 = currencyService.updateOne(currencyEntity);
	        return new ResponseEntity<>(currencyEntity1, HttpStatus.OK);
	    }

}

