package com.xwckeji.gw.server.service;

import com.xwckeji.gw.server.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
