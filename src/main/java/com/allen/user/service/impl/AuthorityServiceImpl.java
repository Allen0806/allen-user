package com.allen.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allen.tool.result.BaseResult;
import com.allen.tool.result.StatusCode;
import com.allen.tool.string.StringUtil;
import com.allen.user.dao.AuthorityDAO;
import com.allen.user.dto.AuthorityDTO;
import com.allen.user.dto.RoleAuthorityDTO;
import com.allen.user.model.AuAuthorityDO;
import com.allen.user.model.AuRoleAuthorityDO;
import com.allen.user.service.AuthorityService;

/**
 * 权限服务层实现类
 * 
 * @author Allen
 * @date 2020年1月10日
 * @since 1.0.0
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	/**
	 * 权限DAO层接口
	 */
	@Autowired
	private AuthorityDAO authorityDAO;

	@Transactional
	@Override
	public BaseResult<AuthorityDTO> save(AuthorityDTO authority) {
		BaseResult<AuthorityDTO> result = checkAuthorityParam(authority);
		if (!result.isSuccessful()) {
			LOGGER.error("保存权限失败，失败原因：{}，权限信息：{}", result.getMessage(), authority.toString());
			result.setData(authority);
			return result;
		}
		if (authority.getCreateTime() == null) {
			LocalDateTime createTime = LocalDateTime.now();
			authority.setCreateTime(createTime);
		}
		AuAuthorityDO authorityDO = toAuthorityDO(authority);
		int count = authorityDAO.save(authorityDO);
		if (count < 1) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("保存权限失败");
		} else {
			authority.setId(authorityDO.getId());
		}
		result.setData(authority);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<AuthorityDTO> update(AuthorityDTO authority) {
		BaseResult<AuthorityDTO> result = new BaseResult<>();
		if (authority == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限对象为空");
			return result;
		}
		if (authority.getId() == null) {
			LOGGER.error("更新权限失败，失败原因：权限ID为空，权限信息：{}", authority.toString());
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限ID为空");
			result.setData(authority);
			return result;
		}
		AuAuthorityDO authorityDO = toAuthorityDO(authority);
		int count = authorityDAO.update(authorityDO);
		if (count < 1) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("更新权限失败");
		}
		result.setData(authority);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<Integer> delete(Long id) {
		BaseResult<Integer> result = new BaseResult<>();
		if (id == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限主键ID为空");
			return result;
		}
		int count = authorityDAO.deleteRoleAuthority(null, id);
		LOGGER.info("删除角色权限数量：{}，权限主键ID：{}", count, id);
		count = authorityDAO.delete(id);
		result.setData(count);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<Integer> saveRoleAuthority(List<RoleAuthorityDTO> roleAuthoritis) {
		if (roleAuthoritis == null || roleAuthoritis.size() == 0) {
			BaseResult<Integer> result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("角色权限列表为空");
			return result;
		}
		List<AuRoleAuthorityDO> roleAuthorityDOs = new ArrayList<>();
		for (RoleAuthorityDTO roleAuthority : roleAuthoritis) {
			BaseResult<Integer> result = checkRoleAuthorityParam(roleAuthority);
			if (!result.isSuccessful()) {
				LOGGER.error("保存角色权限失败，失败原因：{}，角色权限信息：{}", result.getMessage(), roleAuthority.toString());
				return result;
			}
			if (roleAuthority.getValid() == null) {
				roleAuthority.setValid(Boolean.TRUE);
			}
			if (roleAuthority.getCreateTime() == null) {
				LocalDateTime createTime = LocalDateTime.now();
				roleAuthority.setCreateTime(createTime);
			}
			roleAuthorityDOs.add(toRoleAuthorityDO(roleAuthority));
		}
		int count = authorityDAO.saveRoleAuthority(roleAuthorityDOs);
		BaseResult<Integer> result = new BaseResult<>();
		result.setData(count);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<Integer> deleteRoleAuthority(Long roleId, Long authorityId) {
		BaseResult<Integer> result = new BaseResult<>();
		if (roleId == null && authorityId == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("角色主键ID与权限主键ID同时为空");
			return result;
		}
		int count = authorityDAO.deleteRoleAuthority(roleId, authorityId);
		LOGGER.error("删除角色权限数量：{}，角色主键ID：{}，权限主键ID：{}", count, roleId, authorityId);
		result.setData(count);
		return result;
	}

	@Override
	public BaseResult<AuthorityDTO> get(Long id) {
		BaseResult<AuthorityDTO> result = new BaseResult<>();
		if (id == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限主键ID为空");
			return result;
		}
		AuAuthorityDO authority = authorityDAO.get(id);
		result.setData(toAuthorityDTO(authority));
		return result;
	}

	@Override
	public BaseResult<AuthorityDTO> getByAuthorityName(String authorityName) {
		BaseResult<AuthorityDTO> result = new BaseResult<>();
		if (StringUtil.isBlank(authorityName)) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限名称为空");
			return result;
		}
		AuAuthorityDO authority = authorityDAO.getByAuthorityName(authorityName);
		result.setData(toAuthorityDTO(authority));
		return result;
	}

	@Override
	public BaseResult<List<RoleAuthorityDTO>> listRoleAuthority(Long roleId, Long authorityId) {
		BaseResult<List<RoleAuthorityDTO>> result = new BaseResult<>();
		if (roleId == null && authorityId == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("角色主键ID与权限主键ID同时为空");
			return result;
		}
		List<AuRoleAuthorityDO> roleAuthorityDOs = authorityDAO.listRoleAuthority(roleId, authorityId);
		if (roleAuthorityDOs == null || roleAuthorityDOs.size() == 0) {
			return result;
		}
		List<RoleAuthorityDTO> roleAuthorities = new ArrayList<>();
		for (AuRoleAuthorityDO roleAuthority : roleAuthorityDOs) {
			roleAuthorities.add(toRoleAuthorityDTO(roleAuthority));
		}
		result.setData(roleAuthorities);
		return result;
	}

	/**
	 * 校验权限参数
	 * 
	 * @param authority
	 * @return
	 */
	private BaseResult<AuthorityDTO> checkAuthorityParam(AuthorityDTO authority) {
		BaseResult<AuthorityDTO> result = new BaseResult<>();
		if (authority == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限对象为空");
			return result;
		}
		result.setData(authority);
		if (StringUtil.isBlank(authority.getAuthorityName())) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限名称为空");
			return result;
		}
		return result;
	}

	/**
	 * 将权限DTO对象转换为DO对象
	 * 
	 * @param authority
	 * @return
	 */
	private AuAuthorityDO toAuthorityDO(AuthorityDTO authority) {
		if (authority == null) {
			return null;
		}
		AuAuthorityDO authorityDO = new AuAuthorityDO();
		authorityDO.setId(authority.getId());
		authorityDO.setAuthorityName(authority.getAuthorityName());
		authorityDO.setAuthorityNote(authority.getAuthorityNote());
		authorityDO.setCreateTime(authority.getCreateTime());
		return authorityDO;
	}

	/**
	 * 将权限DTO对象转换为DO对象
	 * 
	 * @param authority
	 * @return
	 */
	private AuthorityDTO toAuthorityDTO(AuAuthorityDO authority) {
		if (authority == null) {
			return null;
		}
		AuthorityDTO authorityDTO = new AuthorityDTO();
		authorityDTO.setId(authority.getId());
		authorityDTO.setAuthorityName(authority.getAuthorityName());
		authorityDTO.setAuthorityNote(authority.getAuthorityNote());
		authorityDTO.setCreateTime(authority.getCreateTime());
		return authorityDTO;
	}

	/**
	 * 校验角色权限参数
	 * 
	 * @param authority
	 * @return
	 */
	private BaseResult<Integer> checkRoleAuthorityParam(RoleAuthorityDTO roleAuthority) {
		BaseResult<Integer> result = new BaseResult<>();
		if (roleAuthority == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("角色权限对象为空");
			return result;
		}
		if (roleAuthority.getRoleId() == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("角色主键Id为空");
			return result;
		}
		if (roleAuthority.getAuthorityId() == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("权限主键Id为空");
			return result;
		}
		return result;
	}

	/**
	 * 将角色权限DTO对象转换为DO对象
	 * 
	 * @param roleAuthority
	 * @return
	 */
	private AuRoleAuthorityDO toRoleAuthorityDO(RoleAuthorityDTO roleAuthority) {
		if (roleAuthority == null) {
			return null;
		}
		AuRoleAuthorityDO roleAuthorityDO = new AuRoleAuthorityDO();
		roleAuthorityDO.setId(roleAuthority.getId());
		roleAuthorityDO.setRoleId(roleAuthority.getRoleId());
		roleAuthorityDO.setAuthorityId(roleAuthority.getAuthorityId());
		roleAuthorityDO.setValid(roleAuthority.getValid());
		roleAuthorityDO.setAuthority(toAuthorityDO(roleAuthority.getAuthority()));
		return roleAuthorityDO;
	}

	/**
	 * 将角色权限DO对象转换为DTO对象
	 * 
	 * @param roleAuthority
	 * @return
	 */
	private RoleAuthorityDTO toRoleAuthorityDTO(AuRoleAuthorityDO roleAuthority) {
		if (roleAuthority == null) {
			return null;
		}
		RoleAuthorityDTO roleAuthorityDTO = new RoleAuthorityDTO();
		roleAuthorityDTO.setId(roleAuthority.getId());
		roleAuthorityDTO.setRoleId(roleAuthority.getRoleId());
		roleAuthorityDTO.setAuthorityId(roleAuthority.getAuthorityId());
		roleAuthorityDTO.setValid(roleAuthority.getValid());
		roleAuthorityDTO.setAuthority(toAuthorityDTO(roleAuthority.getAuthority()));
		return roleAuthorityDTO;
	}
}
