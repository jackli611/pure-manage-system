package com.pure.manage.server.service.impl;

import java.util.List;

import com.pure.manage.server.dao.MailDao;
import com.pure.manage.server.model.Mail;
import com.pure.manage.server.service.MailService;
import com.pure.manage.server.service.SendMailSevice;
import com.pure.manage.server.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MailServiceImpl implements MailService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SendMailSevice sendMailSevice;
	@Autowired
	private MailDao mailDao;

	@Override
	@Transactional
	public void save(Mail mail, List<String> toUser) {
		mail.setUserId(UserUtil.getLoginUser().getId());
		mailDao.save(mail);

		toUser.forEach(u -> {
			int status = 1;
			try {
				sendMailSevice.sendMail(u, mail.getSubject(), mail.getContent());
			} catch (Exception e) {
				log.error("发送邮件失败", e);
				status = 0;
			}

			mailDao.saveToUser(mail.getId(), u, status);
		});

	}

}
