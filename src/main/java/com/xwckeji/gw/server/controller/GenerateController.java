package com.xwckeji.gw.server.controller;

import java.util.List;

import com.xwckeji.gw.server.annotation.LogAnnotation;
import com.xwckeji.gw.server.service.GenerateService;
import com.xwckeji.gw.server.dto.BeanField;
import com.xwckeji.gw.server.dto.GenerateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xwckeji.gw.server.dto.GenerateInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 代码生成接口
 * 
 * @author lyf
 *
 */
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generate")
public class GenerateController {

	@Autowired
	private GenerateService generateService;

	@ApiOperation("根据表名显示表信息")
	@GetMapping(params = { "tableName" })
	@PreAuthorize("hasAuthority('generate:edit')")
	public GenerateDetail generateByTableName(String tableName) {
		GenerateDetail detail = new GenerateDetail();
		detail.setBeanName(generateService.upperFirstChar(tableName));
		List<BeanField> fields = generateService.listBeanField(tableName);
		detail.setFields(fields);

		return detail;
	}

	@LogAnnotation
	@ApiOperation("生成代码")
	@PostMapping
	@PreAuthorize("hasAuthority('generate:edit')")
	public void save(@RequestBody GenerateInput input) {
		generateService.saveCode(input);
	}

}
