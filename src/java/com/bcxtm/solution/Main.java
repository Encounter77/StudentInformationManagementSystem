package com.bcxtm.solution;


import java.awt.*;
import java.sql.SQLException;

import com.bcxtm.solution.component.SystemConfig;

public class Main {

	public static void main(String[] args) throws AWTException, SQLException {
		SystemConfig systemConfig = new SystemConfig();
		systemConfig.work();
	}
}
