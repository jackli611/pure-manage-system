package com.pure.manage.server.service;

import java.util.List;

import com.pure.manage.server.model.Mail;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
