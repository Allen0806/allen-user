package com.allen.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allen.tool.result.BaseResult;
import com.allen.user.dao.AuthorityDAO;
import com.allen.user.model.AuRoleAuthorityDO;
import com.allen.user.model.AuthorityDTO;
import com.allen.user.service.AuthorityService;

/**
 * 权限服务层实现类
 * 
 * @author Allen
 * @date 2020年1月10日
 * @since 1.0.0
 *
 */
public class AuthorityServiceImpl implements AuthorityService {
	
	/**
	 * 权限DAO层接口
	 */
	@Autowired
	private AuthorityDAO authorityDAO;

	@Override
	public BaseResult<Integer> save(AuthorityDTO authority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> update(AuthorityDTO authority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> delete(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<Integer> saveRoleAuthority(List<AuRoleAuthorityDO> roleAuthoritis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<AuthorityDTO> get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<AuthorityDTO> getByAuthorityName(String authorityName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResult<List<AuthorityDTO>> listAuthority(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
