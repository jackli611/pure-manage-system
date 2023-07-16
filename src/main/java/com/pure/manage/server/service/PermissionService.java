package com.pure.manage.server.service;

import com.pure.manage.server.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
