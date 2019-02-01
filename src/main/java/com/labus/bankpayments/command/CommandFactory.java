package com.labus.bankpayments.command;

import com.labus.bankpayments.command.account.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static final Map<String, Command> commandsMap = new HashMap<>();

    static {
        commandsMap.put("/default", new DefaultCommand());
        commandsMap.put("/login", new LoginCommand());
        commandsMap.put("/user", new UserCommand());
        commandsMap.put("/admin", new LoginCommand());
        commandsMap.put("/logout", new LogoutCommand());
        commandsMap.put("/transaction", new TransactionCommand());
        commandsMap.put("/createpending", new CreatePendingApCommand());

    }

    public static Command defineCommand(HttpServletRequest req) {
        String actionPath = req.getPathInfo();
        return commandsMap.get(actionPath);
    }
}
