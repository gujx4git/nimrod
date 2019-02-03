package com.gioov.gujx.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.gioov.common.mybatis.Sort;
import com.gioov.common.web.exception.BaseResponseException;
import com.gioov.gujx.entity.StudentScoreEntity;
import com.gioov.nimrod.common.easyui.Pagination;

public interface StudentScoreService {

	public int countAll();

	public int deleteAll(List<Long> idList);

	public int deleteOne(Long id);

	public int deleteOne(StudentScoreEntity studentScoreEntity);

	public StudentScoreEntity getOne(Long id);

	public StudentScoreEntity insertOne(StudentScoreEntity studentScoreEntity) throws BaseResponseException;;

	List<StudentScoreEntity> listAll();

	Pagination.Result<StudentScoreEntity> pageAll(Integer page, Integer rows, Sort sort);

	void truncate();

	public StudentScoreEntity updateOne(StudentScoreEntity studentScoreEntity);
	
	// 导出学生成绩清单
	void exportAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException;

	// 导入学生成绩清单到数据表中
//	void importAllByStudentScoreId(MultipartFile multipartFile, Long categoryId) throws BaseResponseException;

}
