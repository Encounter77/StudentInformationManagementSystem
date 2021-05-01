package com.bcxtm.solution.component;


import com.bcxtm.solution.model.Student;
import com.bcxtm.solution.service.SystemService;
import com.bcxtm.solution.service.UserService;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * 系统业务配置
 */
public class SystemConfig {

    private Screen screen;
    private Keypad keypad;
    private UserService userService;
    private String pendingAccountNumber;
    private boolean userAuthenticated; // 用户是否已通过验证
    private static final int FLAG = 777;


    public SystemConfig() {
        userService = new UserService();
        screen = new Screen();
        keypad = new Keypad();
        userAuthenticated = false;
        pendingAccountNumber = null;
    }


    public void display() {
        screen.displayMessageLine("\t\t------欢迎使用学生信息管理系统------");
    }


    public int menu() {
        screen.displayMessageLine("\t请选择您需要的操作:");
        screen.displayMessageLine("\t1.添加学生信息");
        screen.displayMessageLine("\t2.修改学生信息");
        screen.displayMessageLine("\t3.删除学生信息");
        screen.displayMessageLine("\t4.查询学生信息");
        screen.displayMessageLine("\t5.退出");
        screen.displayMessage("请选择:");
        return keypad.getChoice();
    }

    public void login() {
        screen.displayMessage("\t请输入您的账号:");
        String name = keypad.getInput();
        screen.displayMessage("\t请输入您的密码:");
        String password = keypad.getInput();
        userAuthenticated = userService.loginCheck(name, password);
        if (!userAuthenticated) {
            screen.displayMessageLine("\t登录失败,您的账号或密码有误,请重新输入!");
        } else {
            pendingAccountNumber = name;
        }
    }

    public void work() throws AWTException, SQLException {
        int cnt = 0;
        display();
        do {
            cnt++;
            login();
            if (userAuthenticated) {
                cnt = FLAG;
            }
        } while (cnt < 3);
        if (cnt == 3) {
            screen.displayMessageLine("\t很抱歉,您已经连续三次输入有误,本机不能继续再为您进行服务,谢谢您的使用.");
            work();
        }
        clear();
        screen.displayMessageLine("\t您好,"
                + userService.accessUser(pendingAccountNumber).getUserName()
                + "!");
        systemWork();
    }


    private void systemWork() throws AWTException, SQLException {
        SystemService ss = null;
        char c;
        do {
            int choice = menu();
            switch (choice) {
                case 1:
                    ss = new SystemService(screen, keypad);
                    ss.addStudent();
                    break;
                case 2:
                    ss = new SystemService(screen, keypad);
                    ss.updateStudent();
                    break;
                case 3:
                    ss = new SystemService(screen, keypad);
                    ss.deleteStudent();
                    break;
                case 4:
                    ss = new SystemService(screen, keypad);
                    List<Student> list = ss.loadStudent();
                    if (list.isEmpty()) {
                        screen.displayMessageLine("未查询到对应学号学生，请重试！");
                    } else {
                        list.forEach(el -> {
                            screen.displayMessageLine(el.toString());
                        });
                    }
                    break;
            }
            if (choice != 5) {
                screen.displayMessage("\t您是否还继续进行操作？(Y/N)");
                c = keypad.getInput().charAt(0);
                if (c == 'N' || c == 'n') {
                    work();
                }
//                clear();
            } else {
                work();
            }
        } while (true);
    }

    /**
     * 清屏机器人
     * @throws AWTException
     */
    public void clear() throws AWTException {
        Robot r = new Robot();
        r.mousePress(InputEvent.BUTTON3_MASK); // 按下鼠标右键
        r.mouseRelease(InputEvent.BUTTON3_MASK); // 释放鼠标右键
        r.keyPress(KeyEvent.VK_CONTROL); // 按下Ctrl键
        r.keyPress(KeyEvent.VK_R); // 按下R键
        r.keyRelease(KeyEvent.VK_R); // 释放R键
        r.keyRelease(KeyEvent.VK_CONTROL); // 释放Ctrl键
        r.delay(100);
    }

}
