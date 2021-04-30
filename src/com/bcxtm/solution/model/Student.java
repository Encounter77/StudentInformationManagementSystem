package com.bcxtm.solution.model;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName Student.java
 * @Description 学生
 * @createTime 2021年04月30日 14:32:00
 */
public class Student {

	private String stuId;

	private String idNum;

	private String name;

	private String sex;

	private String subjectId;

	private String classId;

	private String grade;

	private String phone;

	private String qq;

	private String address;

	private String teacherName;

	private String parentName;

	private String parentPhone;

	private String email;

	private String remarks;

	public Student() {
	}

	public Student(String stuId, String idNum, String name, String sex, String subjectId, String classId, String grade, String phone, String qq, String address, String teacherName, String parentName, String parentPhone, String email, String remarks) {
		this.stuId = stuId;
		this.idNum = idNum;
		this.name = name;
		this.sex = sex;
		this.subjectId = subjectId;
		this.classId = classId;
		this.grade = grade;
		this.phone = phone;
		this.qq = qq;
		this.address = address;
		this.teacherName = teacherName;
		this.parentName = parentName;
		this.parentPhone = parentPhone;
		this.email = email;
		this.remarks = remarks;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
