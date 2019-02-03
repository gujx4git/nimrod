package com.gioov.gujx.service.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gioov.common.mybatis.Pageable;
import com.gioov.common.mybatis.Sort;
import com.gioov.common.office.ExcelUtil;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.gujx.entity.StudentScoreEntity;
import com.gioov.gujx.mapper.StudentScoreMapper;
import com.gioov.gujx.service.StudentScoreService;
import com.gioov.nimrod.common.Common;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.easyui.Pagination.Result;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcel;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcelUtil;
import com.gioov.nimrod.system.entity.DictionaryEntity;
import com.gioov.nimrod.user.service.UserService;

@Service
public class StudentScoreServiceImpl implements StudentScoreService {
	@Override
	public List<StudentScoreEntity> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(StudentScoreServiceImpl.class);

	@Autowired
	private StudentScoreMapper studentScoreMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private Common common;

	@Override
	public int countAll() {
		int count = studentScoreMapper.countAll();
		return count;
	}

	@Override
	public int deleteAll(List<Long> idList) {
		return studentScoreMapper.deleteAll(idList);
	}

	@Override
	public int deleteOne(Long id) {
		return studentScoreMapper.deleteOne(id);
	}

	@Override
	public int deleteOne(StudentScoreEntity studentScoreEntity) {
		return studentScoreMapper.deleteOne(studentScoreEntity);
	}

	@Override
	public StudentScoreEntity getOne(Long id) {
		return studentScoreMapper.getOne(id);
	}

	@Override
	public StudentScoreEntity insertOne(StudentScoreEntity studentScoreEntity) throws BaseResponseException {
		StudentScoreEntity studentScoreEntity1 = new StudentScoreEntity();
		studentScoreEntity1.setStudent_id(studentScoreEntity.getStudent_id());
		studentScoreEntity1.setStudent_name(studentScoreEntity.getStudent_name());
		studentScoreEntity1.setExam_id(studentScoreEntity.getExam_id());
		studentScoreEntity1.setExam_name(studentScoreEntity.getExam_name());
		studentScoreEntity1.setChinese_score(studentScoreEntity.getChinese_score());
		studentScoreEntity1.setMath_score(studentScoreEntity1.getMath_score());
		studentScoreEntity1.setEnglish_score(studentScoreEntity.getEnglish_score());
		studentScoreEntity1.setPhysical_score(studentScoreEntity.getPhysical_score());
		studentScoreEntity1.setChemistry_score(studentScoreEntity.getChemistry_score());
		studentScoreEntity1.setBiology_score(studentScoreEntity.getBiology_score());
		studentScoreEntity1.setGeography_score(studentScoreEntity.getGeography_score());
		studentScoreEntity1.setGeneral_score(studentScoreEntity.getGeneral_score());
		studentScoreEntity1.setTotal(studentScoreEntity.getTotal());
		studentScoreEntity1.setGmt_created(new Date());
		studentScoreMapper.insertOne(studentScoreEntity1);
		return studentScoreEntity1;
	}

	@Override
	public Result<StudentScoreEntity> pageAll(Integer page, Integer rows, Sort sort) {
		List<StudentScoreEntity> studentScoreEntityList;
		Pagination.Result<StudentScoreEntity> paginationResult = new Pagination().new Result<>();
		studentScoreEntityList = studentScoreMapper.pageAll(new Pageable<StudentScoreEntity>(page, rows, sort));
		if (studentScoreEntityList != null) {
			paginationResult.setRows(studentScoreEntityList);
		}
		int count = studentScoreMapper.countAll();
		paginationResult.setTotal(count);
		return paginationResult;
	}

	@Override
	public void truncate() {
		studentScoreMapper.truncate();
	}

	@Override
	public StudentScoreEntity updateOne(StudentScoreEntity studentScoreEntity) {
		studentScoreEntity.setGmt_created(new Date());
		int no1 = studentScoreMapper.updateOne(studentScoreEntity);
		if (no1 == 1)
			return studentScoreEntity;
		else
			return null;

	}

	@Override
	public void exportAll(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseResponseException {
		 List<StudentScoreEntity> studentScoreEntityList = new ArrayList<>();
	     studentScoreEntityList.addAll(studentScoreMapper.listAll());
	     String filename = "学生成绩_" + com.gioov.common.util.DateUtil.getNow("yyyyMMddHHmmss") + ".xls";
	        try (Workbook workbook = new HSSFWorkbook()) {
	            Sheet sheet = workbook.createSheet();
	            Row row = sheet.createRow(0);
	            int fieldIndex = 0;
	            Field[] fields = StudentScoreEntity.class.getDeclaredFields();
	            for (Field field : fields) {
	                ExportByExcel annotation = field.getAnnotation(ExportByExcel.class);
	                if (annotation != null) {
	                    String name = annotation.name();
	                    if ("".equals(name)) {
	                        name = annotation.value();
	                    }
	                    Cell cell1 = row.createCell(fieldIndex);
	                    cell1.setCellValue(name);
	                    fieldIndex++;
	                }
	            }
	            int rowIndex = 1;
	            for (StudentScoreEntity studentScoreEntity : studentScoreEntityList) {
	                row = sheet.createRow(rowIndex);
	                ExportByExcelUtil.exportFieldValue(row, studentScoreEntity);
	                rowIndex++;
	            }
	            ExcelUtil.read2003AndDownloadExportExcel(httpServletRequest, httpServletResponse, workbook, filename);
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new BaseResponseException("导出失败");
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        }	     
		
	}



}
