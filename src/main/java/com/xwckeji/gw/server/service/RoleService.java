package com.xwckeji.gw.server.service;

import com.xwckeji.gw.server.dto.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
