package com.pure.manage.server.service;

import com.pure.manage.server.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
