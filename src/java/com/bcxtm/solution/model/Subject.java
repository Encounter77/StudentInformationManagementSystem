package com.bcxtm.solution.model;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName Subject.java
 * @Description 专业
 * @createTime 2021年04月30日 14:32:00
 */
public class Subject {


	private String subjectId;

	private String subjectName;

	private String subjectInfo;

	public Subject() {
	}

	public Subject(String subjectId, String subjectName, String subjectInfo) {
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.subjectInfo = subjectInfo;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(String subjectInfo) {
		this.subjectInfo = subjectInfo;
	}


	@Override
	public String toString() {
		return "专业{" +
				"专业代号：'" + subjectId + '\'' +
				", 专业名称：'" + subjectName + '\'' +
				", 专业信息：'" + subjectInfo + '\'' +
				'}';
	}
}
