package com.allen.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.tool.result.BaseResult;
import com.allen.user.dao.RoleDAO;
import com.allen.user.data.AuUserRoleDO;
import com.allen.user.dto.RoleDTO;
import com.allen.user.service.RoleService;

/**
 * 角色服务层实现类
 * 
 * @author Allen
 * @date 2020年1月10日
 * @since 1.0.0
 *
 */
@Service
public class RoleServiceImpl implements RoleService {
	
	/**
	 * 角色DAO层接口
	 */
	@Autowired
	private RoleDAO roleDAO;

	@Override
	public BaseResult<Integer> save(RoleDTO role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> update(RoleDTO role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> saveUserRole(List<AuUserRoleDO> userRoles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> deleteUserRole(Long userId, Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<RoleDTO> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<RoleDTO> getByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<List<RoleDTO>> listRole(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<RoleDTO> getUserRole(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
