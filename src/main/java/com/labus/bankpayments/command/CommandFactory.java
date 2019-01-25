package com.labus.bankpayments.command;

import com.labus.bankpayments.command.account.LoginCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put("/login", new LoginCommand());
        commandsMap.put("/user", new LoginCommand());
        commandsMap.put("/admin", new LoginCommand());
        commandsMap.put("/logout", new LoginCommand());
    }

    public static Command defineCommand(HttpServletRequest req) {
        String actionPath = req.getPathInfo();
        System.out.println("--"+actionPath);
        return commandsMap.get(actionPath);
    }
}
