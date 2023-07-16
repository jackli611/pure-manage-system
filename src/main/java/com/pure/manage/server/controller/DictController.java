package com.pure.manage.server.controller;

import java.util.List;

import com.pure.manage.server.dao.DictDao;
import com.pure.manage.server.model.Dict;
import com.pure.manage.server.page.table.PageTableHandler;
import com.pure.manage.server.page.table.PageTableRequest;
import com.pure.manage.server.page.table.PageTableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dicts")
public class DictController {

	@Autowired
	private DictDao dictDao;

	@PreAuthorize("hasAuthority('dict:add')")
	@PostMapping
	@ApiOperation(value = "保存")
	public Dict save(@RequestBody Dict dict) {
		Dict d = dictDao.getByTypeAndK(dict.getType(), dict.getK());
		if (d != null) {
			throw new IllegalArgumentException("类型和key已存在");
		}
		dictDao.save(dict);

		return dict;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取")
	public Dict get(@PathVariable Long id) {
		return dictDao.getById(id);
	}

	@PreAuthorize("hasAuthority('dict:add')")
	@PutMapping
	@ApiOperation(value = "修改")
	public Dict update(@RequestBody Dict dict) {
		dictDao.update(dict);

		return dict;
	}

	@PreAuthorize("hasAuthority('dict:query')")
	@GetMapping(params = { "start", "length" })
	@ApiOperation(value = "列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return dictDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<Dict> list(PageTableRequest request) {
				return dictDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@PreAuthorize("hasAuthority('dict:del')")
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除")
	public void delete(@PathVariable Long id) {
		dictDao.delete(id);
	}

	@GetMapping(params = "type")
	public List<Dict> listByType(String type) {
		return dictDao.listByType(type);
	}
}
