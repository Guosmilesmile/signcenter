package com.edu.gy.course.vo;

/**
 * 课程显示类
 * @author Christy
 *
 */
public class CourseVO {
	private  Integer id;//课程id
	private  String courseName;//课程名
	private  String teacherName;//教师名称
	public CourseVO(Integer id, String courseName, String teacherName) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.teacherName = teacherName;
	}
	public CourseVO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
