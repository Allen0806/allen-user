package com.allen.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allen.tool.result.BaseResult;
import com.allen.user.model.UserDTO;
import com.allen.user.service.UserService;

/**
 * 用户服务层实现类
 * 
 * @author Allen
 * @date 2020年1月10日
 * @since 1.0.0
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public BaseResult<Integer> save(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> saveBatch(List<UserDTO> users) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> update(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<UserDTO> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<UserDTO> getByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<UserDTO> getUserWithRole(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<UserDTO> getUserWithRole2(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
