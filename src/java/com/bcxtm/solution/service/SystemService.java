package com.bcxtm.solution.service;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.bcxtm.solution.component.Keypad;
import com.bcxtm.solution.component.Screen;
import com.bcxtm.solution.model.Student;

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
            screen.displayMessageLine("当前对应学号学生，请重试！");
        } else {
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
            db.update(Entity.create("student").parseBean(student, true, true),
                    Entity.create("student").set("stu_id", student.getStuId()));
            screen.displayMessageLine("对应学号学生修改成功！");
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

}
