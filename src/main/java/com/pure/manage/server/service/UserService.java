package com.pure.manage.server.service;

import com.pure.manage.server.dto.UserDto;
import com.pure.manage.server.model.SysUser;

public interface UserService {

	SysUser saveUser(UserDto userDto);

	SysUser updateUser(UserDto userDto);

	SysUser getUser(String username);

	void changePassword(String username, String oldPassword, String newPassword);

}
