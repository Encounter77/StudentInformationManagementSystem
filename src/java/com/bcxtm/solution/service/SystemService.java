package com.bcxtm.solution.service;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.bcxtm.solution.component.Keypad;
import com.bcxtm.solution.component.Screen;
import com.bcxtm.solution.model.Student;
import com.bcxtm.solution.model.StudentClass;
import com.bcxtm.solution.model.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * 系统提供的服务
 */
public class SystemService {

    private Screen screen;
    private Keypad keypad;

    private static final Db db = Db.use("mysql"); // 加载 Mysql 数据库

    public SystemService() {

    }

    public SystemService(Screen screen, Keypad keypad) {
        this.screen = screen;
        this.keypad = keypad;
    }

    public void addStudent() throws SQLException {
        Student student = new Student();
        screen.displayMessage("请输入学生学号：");
        student.setStuId(keypad.getInput());
        fillStudentInfo(student);
        if (studentIsExist(student.getStuId())) {
            screen.displayMessageLine("当前对应学号学生已存在，请重试！");
        }
        if (!subjectIsExist(student.getSubjectId())) {
            screen.displayMessageLine("当前对应代号专业不存在，请重试！");
        }
        if (!classIsExist(student.getClassId())) {
            screen.displayMessageLine("当前对应代号班级不存在，请重试！");
        }
        if (!studentIsExist(student.getStuId()) && subjectIsExist(student.getSubjectId()) && classIsExist(student.getClassId())) {
            db.insert(Entity.create("student").parseBean(student, true, true));
            screen.displayMessageLine("对应学号学生添加成功！");
        }
    }

    public void updateStudent() throws SQLException {
        Student student = new Student();
        screen.displayMessage("请输入要修改的学生学号：");
        String stuId = keypad.getInput();
        if (!studentIsExist(stuId)) {
            screen.displayMessageLine("未查询到对应学号学生，请重试！");
        } else {
            student.setStuId(stuId);
            fillStudentInfo(student);
            if (!subjectIsExist(student.getSubjectId())) {
                screen.displayMessageLine("当前对应代号专业不存在，请重试！");
            }
            if (!classIsExist(student.getClassId())) {
                screen.displayMessageLine("当前对应代号班级不存在，请重试！");
            }
            if (subjectIsExist(student.getSubjectId()) && classIsExist(student.getClassId())) {
                db.update(Entity.create("student").parseBean(student, true, true),
                        Entity.create("student").set("stu_id", student.getStuId()));
                screen.displayMessageLine("对应学号学生修改成功！");
            }
        }

    }

    private void fillStudentInfo(Student student) {
        screen.displayMessage("请输入学生身份证号：");
        student.setIdNum(keypad.getInput());
        screen.displayMessage("请输入学生姓名：");
        student.setName(keypad.getInput());
        screen.displayMessage("请输入学生性别：");
        student.setSex(keypad.getInput());
        screen.displayMessage("请输入学生电话：");
        student.setPhone(keypad.getInput());
        screen.displayMessage("请输入学生QQ：");
        student.setQq(keypad.getInput());
        screen.displayMessage("请输入学生家庭住址：");
        student.setAddress(keypad.getInput());
        screen.displayMessage("请输入学生班主任姓名：");
        student.setTeacherName(keypad.getInput());
        screen.displayMessage("请输入学生家长姓名：");
        student.setParentName(keypad.getInput());
        screen.displayMessage("请输入学生家长电话：");
        student.setParentPhone(keypad.getInput());
        screen.displayMessage("请输入学生电子邮箱地址：");
        student.setEmail(keypad.getInput());
        screen.displayMessage("请输入备注：");
        student.setRemarks(keypad.getInput());
        screen.displayMessage("请输入关联专业：");
        student.setSubjectId(keypad.getInput());
        screen.displayMessage("请输入关联班级：");
        student.setClassId(keypad.getInput());
        screen.displayMessage("请输入所在年级：");
        student.setGrade(keypad.getInput());
    }

    public void deleteStudent() throws SQLException {
        screen.displayMessage("请输入要删除的学生学号：");
        String stuId = keypad.getInput();
        if (!studentIsExist(stuId)) {
            screen.displayMessageLine("未查询到对应学号学生，请重试！");
        } else {
            db.del("student", "stu_id", stuId);
            screen.displayMessageLine("对应学号学生删除成功！");
        }
    }

    public List<Student> loadStudent() throws SQLException {
        screen.displayMessage("请输入要查询的学生学号：");
        String stuId = keypad.getInput();
        screen.displayMessage("请输入要查询的学生姓名（模糊）：");
        String name = keypad.getInput();
        return db.find(Entity.create("student")
                .set("stu_id", "like %" + stuId + "%")
                .set("name", "like %" + name + "%"), Student.class);
    }

    public boolean studentIsExist(String stuId) throws SQLException {
        return !db.find(Entity.create("student").set("stu_id", stuId)).isEmpty();
    }

    public void addSubject() throws SQLException {
        Subject subject = new Subject();
        screen.displayMessage("请输入专业代号：");
        subject.setSubjectId(keypad.getInput());
        screen.displayMessage("请输入专业名称：");
        subject.setSubjectName(keypad.getInput());
        screen.displayMessage("请输入专业信息：");
        subject.setSubjectInfo(keypad.getInput());
        if (subjectIsExist(subject.getSubjectId())) {
            screen.displayMessageLine("当前对应代号专业已存在，请重试！");
        } else {
            db.insert(Entity.create("subject").parseBean(subject, true, true));
            screen.displayMessageLine("对应代号专业添加成功！");
        }

    }

    public void deleteSubject() throws SQLException {
        screen.displayMessage("请输入要删除的专业代号：");
        String subjectId = keypad.getInput();
        if (!subjectIsExist(subjectId)) {
            screen.displayMessageLine("未查询到对应代号专业，请重试！");
        } else {
            if (subjectIsHaveClasses(subjectId)) {
                screen.displayMessageLine("当前专业包含班级，无法进行删除！");
            } else {
                db.del("subject", "subject_id", subjectId);
                screen.displayMessageLine("对应代号专业删除成功！");
            }
        }
    }

    public List<Subject> loadSubject() throws SQLException {
        screen.displayMessage("请输入要查询的专业代号：");
        String subjectId = keypad.getInput();
        screen.displayMessage("请输入要查询的专业名称（模糊）：");
        String subjectName = keypad.getInput();
        return db.find(Entity.create("subject")
                .set("subject_id", "like %" + subjectId + "%")
                .set("subject_name", "like %" + subjectName + "%"), Subject.class);
    }


    public boolean subjectIsHaveClasses(String subjectId) throws SQLException {
        return !db.find(Entity.create("class").set("subject_id", subjectId)).isEmpty();
    }

    public boolean subjectIsExist(String subjectId) throws SQLException {
        return !db.find(Entity.create("subject").set("subject_id", subjectId)).isEmpty();
    }

    public void addClass() throws SQLException {
        StudentClass studentClass = new StudentClass();
        screen.displayMessage("请输入班级代号：");
        studentClass.setClassId(keypad.getInput());
        screen.displayMessage("请输入班级名称：");
        studentClass.setClassName(keypad.getInput());
        screen.displayMessage("请输入班级信息：");
        studentClass.setClassInfo(keypad.getInput());
        screen.displayMessage("请输入关联专业代号：");
        studentClass.setSubjectId(keypad.getInput());
        if (!subjectIsExist(studentClass.getSubjectId())) {
            screen.displayMessageLine("当前对应代号专业不存在，请重试！");
        }
        if (classIsExist(studentClass.getClassId())) {
            screen.displayMessageLine("当前对应代号班级已存在，请重试！");
        }
        if (subjectIsExist(studentClass.getSubjectId()) && !classIsExist(studentClass.getClassId())) {
            db.insert(Entity.create("class").parseBean(studentClass, true, true));
            screen.displayMessageLine("对应代号班级添加成功！");
        }
    }

    public void deleteClass() throws SQLException {
        screen.displayMessage("请输入要删除的班级代号：");
        String classId = keypad.getInput();
        if (!classIsExist(classId)) {
            screen.displayMessageLine("未查询到对应代号班级，请重试！");
        } else {
            if (classIsHaveStudents(classId)) {
                screen.displayMessageLine("当前班级包含学生，无法进行删除！");
            } else {
                db.del("class", "class_id", classId);
                screen.displayMessageLine("对应代号班级删除成功！");
            }
        }
    }

    public List<StudentClass> loadClass() throws SQLException {
        screen.displayMessage("请输入要查询的班级代号：");
        String classId = keypad.getInput();
        screen.displayMessage("请输入要查询的班级名称（模糊）：");
        String className = keypad.getInput();
        return db.find(Entity.create("class")
                .set("class_id", "like %" + classId + "%")
                .set("class_name", "like %" + className + "%"), StudentClass.class);
    }

    public boolean classIsExist(String classId) throws SQLException {
        return !db.find(Entity.create("class").set("class_id", classId)).isEmpty();
    }

    public boolean classIsHaveStudents(String classId) throws SQLException {
        return !db.find(Entity.create("student").set("class_id", classId)).isEmpty();
    }
}
