package com.gioov.gujx.api;

import static com.gioov.nimrod.user.service.UserService.SYSTEM_ADMIN;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.gioov.gujx.entity.StudentScoreEntity;
import com.gioov.gujx.service.StudentScoreService;
import com.gioov.nimrod.common.easyui.Pagination;
import com.gioov.nimrod.common.operationlog.OperationLog;
import com.gioov.nimrod.common.operationlog.OperationLogType;
import com.gioov.nimrod.system.entity.ApiEntity;
import com.gioov.nimrod.system.entity.AttachmentEntity;

@RestController
@RequestMapping(value = "/api/school/studentscore", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentScoreRestController {
	private static final String STUDENT_SCORE = "/API/SCHOOL/STUDENT_SCORE";
	@Autowired
	private StudentScoreService studentScoreService;

	/**
	 * 分页获取所有记录
	 *
	 * @param page
	 *            页
	 * @param rows
	 *            每页显示数量
	 * @return ResponseEntity<Pagination.Result<StudentScoreEntity>>
	 */
	@OperationLog(value = "分页获取所有学生成绩", type = OperationLogType.API)
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/PAGE_ALL')")
	@GetMapping(value = "/page_all")
	public ResponseEntity<Pagination.Result<StudentScoreEntity>> pageAll(@RequestParam Integer page,
			@RequestParam Integer rows) {
		Sort sort = new Sort(Sort.Direction.DESC, "gmt_created");
		return new ResponseEntity<>(studentScoreService.pageAll(page, rows, sort), HttpStatus.OK);
	}

	/**
	 * 指定学生成绩 id ，批量删除学生成绩
	 *
	 * @param idList
	 *            学生成绩 id list
	 * @return ResponseEntity<Integer>
	 */
	@OperationLog(value = "批量删除学生成绩", type = OperationLogType.API)
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/DELETE_ALL')")
	@PostMapping(value = "/delete_all")
	public ResponseEntity<Integer> deleteAll(@RequestParam("id[]") List<Long> idList) {
		return new ResponseEntity<>(studentScoreService.deleteAll(idList), HttpStatus.OK);
	}

	/**
	 * 指定学生成绩 id ，获取学生成绩信息
	 *
	 * @param id
	 *            学生成绩idx
	 * @return ResponseEntity<StudentScoreEntity>
	 */
	@OperationLog(value = "获取学生成绩信息", type = OperationLogType.API)
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/ONE')")
	@GetMapping(value = "/one/{id}")
	public ResponseEntity<StudentScoreEntity> getOne(@PathVariable Long id) {
		return new ResponseEntity<>(studentScoreService.getOne(id), HttpStatus.OK);
	}

	/**
	 * 清空学生成绩
	 *
	 * @return ResponseEntity<Integer>
	 */
	@OperationLog(value = "清空学生成绩", type = OperationLogType.API)
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/CLEAR_ALL')")
	@PostMapping(value = "/clear_all")
	public ResponseEntity<HttpStatus> clearAll() {
		studentScoreService.truncate();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * 新增 学生成绩
	 *
	 * @param id-----
	 *            id
	 * @param student_id-----
	 *            学生id
	 * @param student_name-----
	 *            学生姓名
	 * @param exam_id-----
	 *            考试名称
	 * @param exam_name-----
	 *            考试名称
	 * @param chinese_score-----
	 *            语文成绩
	 * @param math_score-----
	 *            数学成绩
	 * @param english_score-----
	 *            英语成绩
	 * @param politics_score-----
	 *            政治成绩
	 * @param physical_score-----
	 *            物理成绩
	 * @param chemistry_score-----
	 *            化学成绩
	 * @param geography_score-----
	 *            地理成绩
	 * @param biology_score-----
	 *            生物成绩
	 * @param general_score-----
	 *            综合成绩
	 * @param total-----
	 *            总分
	 * @param gmt_created-----
	 *            创建时间
	 * @return ResponseEntity<StudentScoreEntity>
	 */
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/ADD_ONE')")
	@PostMapping(value = "/add_one")
	public ResponseEntity<StudentScoreEntity> addOne(@RequestParam Long student_id, @RequestParam String student_name,
			@RequestParam Long exam_id, @RequestParam String exam_name, @RequestParam Float chinese_score,
			@RequestParam Float math_score, @RequestParam Float english_score, @RequestParam Float politics_score,
			@RequestParam Float physical_score, @RequestParam Float chemistry_score,
			@RequestParam Float geography_score, @RequestParam Float biology_score, @RequestParam Float general_score,
			@RequestParam Float total) throws BaseResponseException {
		StudentScoreEntity studentScoreEntity = new StudentScoreEntity();
		studentScoreEntity.setExam_id(exam_id);
		studentScoreEntity.setExam_name(exam_name);
		studentScoreEntity.setStudent_id(student_id);
		studentScoreEntity.setStudent_name(student_name);
		studentScoreEntity.setChinese_score(chinese_score);
		studentScoreEntity.setMath_score(math_score);
		studentScoreEntity.setEnglish_score(english_score);
		studentScoreEntity.setPhysical_score(physical_score);
		studentScoreEntity.setChemistry_score(chemistry_score);
		studentScoreEntity.setGeography_score(geography_score);
		studentScoreEntity.setPolitics_score(politics_score);
		studentScoreEntity.setBiology_score(biology_score);
		studentScoreEntity.setGeography_score(geography_score);
		studentScoreEntity.setGeneral_score(general_score);
		studentScoreEntity.setTotal(total);
		StudentScoreEntity studentScoreEntity1 = studentScoreService.insertOne(studentScoreEntity);
		return new ResponseEntity<>(studentScoreEntity1, HttpStatus.OK);
	}

	/**
	 * 保存学生成绩
	 *
	 * @param id
	 *            学生成绩 id
	 * @param name
	 *            附件文件名
	 * @param remark
	 *            备注
	 * @return ResponseEntity<StudentScoreEntity>
	 */
	@PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/SAVE_ONE')")
	@PostMapping(value = "/save_one")
	public ResponseEntity<StudentScoreEntity> saveOne(@RequestParam Long id, @RequestParam Long student_id,
			@RequestParam String student_name, @RequestParam Long exam_id, @RequestParam String exam_name,
			@RequestParam Float chinese_score, @RequestParam Float math_score, @RequestParam Float english_score,
			@RequestParam Float politics_score, @RequestParam Float physical_score, @RequestParam Float chemistry_score,
			@RequestParam Float geography_score, @RequestParam Float biology_score, @RequestParam Float general_score,
			@RequestParam Float total) {
		StudentScoreEntity studentScoreEntity = new StudentScoreEntity();
		studentScoreEntity.setId(id);
		studentScoreEntity.setExam_id(exam_id);
		studentScoreEntity.setExam_name(exam_name);
		studentScoreEntity.setStudent_id(student_id);
		studentScoreEntity.setStudent_name(student_name);
		studentScoreEntity.setChinese_score(chinese_score);
		studentScoreEntity.setMath_score(math_score);
		studentScoreEntity.setEnglish_score(english_score);
		studentScoreEntity.setPhysical_score(physical_score);
		studentScoreEntity.setChemistry_score(chemistry_score);
		studentScoreEntity.setGeography_score(geography_score);
		studentScoreEntity.setPolitics_score(politics_score);
		studentScoreEntity.setBiology_score(biology_score);
		studentScoreEntity.setGeography_score(geography_score);
		studentScoreEntity.setGeneral_score(general_score);
		studentScoreEntity.setTotal(total);
		StudentScoreEntity studentScoreEntity1 = studentScoreService.updateOne(studentScoreEntity);
		return new ResponseEntity<>(studentScoreEntity1, HttpStatus.OK);
	}
	
    @PreAuthorize("hasRole('" + SYSTEM_ADMIN + "') OR hasAuthority('" + STUDENT_SCORE + "/ONE')")
    @GetMapping(value = "/export_all")
    public void exportAll(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BaseResponseException {
    	studentScoreService.exportAll(httpServletRequest, httpServletResponse);
    }
}
