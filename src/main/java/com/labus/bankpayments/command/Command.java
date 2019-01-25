package com.labus.bankpayments.command;

import com.labus.bankpayments.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
        String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
