package com.pure.manage.server.service;

import com.pure.manage.server.model.SysLogs;

/**
 * 日志service
 * 
 * @author lyf
 *
 *         2017年8月19日
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
