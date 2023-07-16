package com.pure.manage.server.controller;

import java.util.List;

import com.pure.manage.server.dao.SysLogsDao;
import com.pure.manage.server.model.SysLogs;
import com.pure.manage.server.page.table.PageTableHandler;
import com.pure.manage.server.page.table.PageTableRequest;
import com.pure.manage.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class SysLogsController {

	@Autowired
	private SysLogsDao sysLogsDao;

	@GetMapping
	@PreAuthorize("hasAuthority('sys:log:query')")
	@ApiOperation(value = "日志列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return sysLogsDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<SysLogs> list(PageTableRequest request) {
				return sysLogsDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

}
