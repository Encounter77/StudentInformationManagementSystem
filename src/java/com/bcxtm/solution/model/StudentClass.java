package com.bcxtm.solution.model;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName Class.java
 * @Description 班级
 * @createTime 2021年04月30日 14:32:00
 */
public class StudentClass {

	private String classId;

	private String className;

	private String classInfo;

	private String subjectId;

	public StudentClass() {
	}

	public StudentClass(String classId, String className, String classInfo, String subjectId) {
		this.classId = classId;
		this.className = className;
		this.classInfo = classInfo;
		this.subjectId = subjectId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(String classInfo) {
		this.classInfo = classInfo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}


	@Override
	public String toString() {
		return "班级{" +
				"班级代号：'" + classId + '\'' +
				", 班级名称：'" + className + '\'' +
				", 班级信息：'" + classInfo + '\'' +
				'}';
	}
}
