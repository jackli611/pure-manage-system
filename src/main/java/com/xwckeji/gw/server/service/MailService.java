package com.xwckeji.gw.server.service;

import java.util.List;

import com.xwckeji.gw.server.model.Mail;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
