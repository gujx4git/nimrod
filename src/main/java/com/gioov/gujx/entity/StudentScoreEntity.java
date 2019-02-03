package com.gioov.gujx.entity;

import java.io.Serializable;
import java.util.Date;

import com.gioov.common.util.DateUtil;
import com.gioov.nimrod.common.exportbyexcel.ExportByExcel;


public class StudentScoreEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8993802542368907886L;
	
	 @ExportByExcel(name = "ID")	 
	private Long id;
	 @ExportByExcel(name = "学生ID")
	private Long student_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public Long getExam_id() {
		return exam_id;
	}
	public void setExam_id(Long exam_id) {
		this.exam_id = exam_id;
	}
	public String getExam_name() {
		return exam_name;
	}
	public void setExam_name(String exam_name) {
		this.exam_name = exam_name;
	}
	public float getChinese_score() {
		return chinese_score;
	}
	public void setChinese_score(float chinese_score) {
		this.chinese_score = chinese_score;
	}
	public double getMath_score() {
		return math_score;
	}
	public void setMath_score(double math_score) {
		this.math_score = math_score;
	}
	public double getEnglish_score() {
		return english_score;
	}
	public void setEnglish_score(double english_score) {
		this.english_score = english_score;
	}
	public double getPolitics_score() {
		return politics_score;
	}
	public void setPolitics_score(double politics_score) {
		this.politics_score = politics_score;
	}
	public double getPhysical_score() {
		return physical_score;
	}
	public void setPhysical_score(double physical_score) {
		this.physical_score = physical_score;
	}
	public double getChemistry_score() {
		return chemistry_score;
	}
	public void setChemistry_score(double chemistry_score) {
		this.chemistry_score = chemistry_score;
	}
	public double getGeography_score() {
		return geography_score;
	}
	public void setGeography_score(double geography_score) {
		this.geography_score = geography_score;
	}
	public double getBiology_score() {
		return biology_score;
	}
	public void setBiology_score(double biology_score) {
		this.biology_score = biology_score;
	}
	public double getGeneral_score() {
		return general_score;
	}
	public void setGeneral_score(double general_score) {
		this.general_score = general_score;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getGmt_created() {
		return gmt_created;
	}
	public void setGmt_created(Date gmt_created) {
		this.gmt_created = gmt_created;
	}
	 @ExportByExcel(name = "学生姓名")
	private String student_name;
	 @ExportByExcel(name = "考试ID")
	private Long exam_id;
	 @ExportByExcel(name = "考试名称")
	private String exam_name;
	 @ExportByExcel(name = "语文")
	private float chinese_score;
	 @ExportByExcel(name = "数学")
	private double math_score;
	 @ExportByExcel(name = "英语")
	private double english_score;
	 @ExportByExcel(name = "政治")
	private double politics_score;
	 @ExportByExcel(name = "物理")
	private double physical_score;
	 @ExportByExcel(name = "化学")
	private double chemistry_score;
	 @ExportByExcel(name = "地理")
	private double geography_score;
	 @ExportByExcel(name = "生物")
	private double biology_score;
	 @ExportByExcel(name = "综合")
	private double general_score;
	 @ExportByExcel(name = "总分")
	private double total;
	 @ExportByExcel(value = "创建时间", pattern = DateUtil.DEFAULT_DATE_FORMAT_PATTERN)
	private Date gmt_created;

}
