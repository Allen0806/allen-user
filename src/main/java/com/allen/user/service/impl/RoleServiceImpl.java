package com.allen.user.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.tool.result.BaseResult;
import com.allen.tool.string.StringUtil;
import com.allen.user.dao.RoleDAO;
import com.allen.user.data.AuRoleDO;
import com.allen.user.data.AuUserRoleDO;
import com.allen.user.dto.RoleDTO;
import com.allen.user.dto.UserRoleDTO;
import com.allen.user.service.AuthorityService;
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
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

	/**
	 * 角色DAO层接口
	 */
	@Autowired
	private RoleDAO roleDAO;

	/**
	 * 权限管理服务
	 */
	@Autowired
	private AuthorityService authorityService;

	@Override
	public BaseResult<RoleDTO> save(RoleDTO role) {
		BaseResult<RoleDTO> result = checkRoleParam(role);
		if (!result.success()) {
			LOGGER.error("保存角色失败，失败原因：{}，角色信息：{}", result.getMessage(), role.toString());
			result.setData(role);
			return result;
		}
		if (role.getCreateTime() == null) {
			LocalDateTime createTime = LocalDateTime.now();
			role.setCreateTime(createTime);
		}
		AuRoleDO roleDO = toRoleDO(role);
		int count = roleDAO.save(roleDO);
		if (count < 1) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("保存角色失败");
		} else {
			role.setId(roleDO.getId());
		}
		result.setData(role);
		return result;
	}

	@Override
	public BaseResult<RoleDTO> update(RoleDTO role) {
		BaseResult<RoleDTO> result = new BaseResult<>();
		if (role == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色对象为空");
			return result;
		}
		if (role.getId() == null) {
			LOGGER.error("更新角色失败，失败原因：角色ID为空，角色信息：{}", role.toString());
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色ID为空");
			result.setData(role);
			return result;
		}
		AuRoleDO roleDO = toRoleDO(role);
		int count = roleDAO.update(roleDO);
		if (count < 1) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("更新角色失败");
		}
		result.setData(role);
		return result;
	}

	@Override
	public BaseResult<Integer> delete(Long id) {
		if (id == null) {
			BaseResult<Integer> result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色主键ID为空");
			return result;
		}
		BaseResult<Integer> result = authorityService.deleteRoleAuthority(id, null);
		LOGGER.info("删除角色权限数量：{}，角色主键ID：{}", result.getData(), id);
		if (result.success()) {
			int count = roleDAO.delete(id);
			result.setData(count);
		}
		return result;
	}

	@Override
	public BaseResult<Integer> saveUserRole(List<UserRoleDTO> userRoles) {
		if (userRoles == null || userRoles.size() == 0) {
			BaseResult<Integer> result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("用户角色列表为空");
			return result;
		}
		List<AuUserRoleDO> userRoleDOs = new ArrayList<>();
		for (UserRoleDTO userRoleDTO : userRoles) {
			BaseResult<Integer> result = checkUserRoleParam(userRoleDTO);
			if (!result.success()) {
				LOGGER.error("保存用户角色失败，失败原因：{}，用户角色信息：{}", result.getMessage(), userRoleDTO.toString());
				return result;
			}
			if (userRoleDTO.getCreateTime() == null) {
				LocalDateTime createTime = LocalDateTime.now();
				userRoleDTO.setCreateTime(createTime);
			}
			userRoleDOs.add(toUserRoleDO(userRoleDTO));
		}
		int count = roleDAO.saveUserRole(userRoleDOs);
		BaseResult<Integer> result = new BaseResult<>();
		result.setData(count);
		return result;
	}

	@Override
	public BaseResult<Integer> deleteUserRole(Long userId, Long roleId) {
		BaseResult<Integer> result = new BaseResult<>();
		if (userId == null && roleId == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("用户主键ID与角色主键ID同时为空");
			return result;
		}
		int count = roleDAO.deleteUserRole(userId, roleId);
		LOGGER.error("删除用户角色数量：{}，用户主键ID：{}，角色主键ID：{}", count, userId, roleId);
		result.setData(count);
		return result;
	}

	@Override
	public BaseResult<RoleDTO> get(Long id) {
		BaseResult<RoleDTO> result = new BaseResult<>();
		if (id == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色主键ID为空");
			return result;
		}
		AuRoleDO roleDO = roleDAO.get(id);
		result.setData(toRoleDTO(roleDO));
		return result;
	}

	@Override
	public BaseResult<RoleDTO> getByRoleName(String roleName) {
		BaseResult<RoleDTO> result = new BaseResult<>();
		if (StringUtil.isBlank(roleName)) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色名称为空");
			return result;
		}
		AuRoleDO roleDO = roleDAO.getByRoleName(roleName);
		result.setData(toRoleDTO(roleDO));
		return result;
	}

	@Override
	public BaseResult<List<UserRoleDTO>> listUserRole(Long userId, Long roleId) {
		BaseResult<List<UserRoleDTO>> result = new BaseResult<>();
		if (userId == null && roleId == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("用户主键ID与角色主键ID同时为空");
			return result;
		}
		List<AuUserRoleDO> userRoleDOs = roleDAO.listUserRole(userId, roleId);
		if (userRoleDOs == null || userRoleDOs.size() == 0) {
			return result;
		}
		List<UserRoleDTO> userRoles = new ArrayList<>();
		for (AuUserRoleDO userRoleDO : userRoleDOs) {
			userRoles.add(toUserRoleDTO(userRoleDO));
		}
		result.setData(userRoles);
		return result;
	}

	/**
	 * 校验角色参数
	 * 
	 * @param roleDTO   角色信息
	 * @param isCheckId 是否校验主键是否为空
	 * @return 校验结果
	 */
	private BaseResult<RoleDTO> checkRoleParam(RoleDTO roleDTO) {
		BaseResult<RoleDTO> result = new BaseResult<>();
		if (roleDTO == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色对象为空");
			return result;
		}
		if (StringUtil.isBlank(roleDTO.getRoleName())) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色名称为空");
			result.setData(roleDTO);
			return result;
		}
		return result;
	}

	/**
	 * 将角色DTO对象转换为DO对象
	 * 
	 * @param roleDTO
	 * @return
	 */
	private AuRoleDO toRoleDO(RoleDTO roleDTO) {
		if (roleDTO == null) {
			return null;
		}
		AuRoleDO roleDO = new AuRoleDO();
		roleDO.setId(roleDTO.getId());
		roleDO.setRoleName(roleDTO.getRoleName());
		roleDO.setRoleNote(roleDTO.getRoleNote());
		roleDO.setCreateTime(roleDTO.getCreateTime());
		return roleDO;
	}

	/**
	 * 将角色DO对象转换为DTO对象
	 * 
	 * @param roleDO
	 * @return
	 */
	private RoleDTO toRoleDTO(AuRoleDO roleDO) {
		if (roleDO == null) {
			return null;
		}
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setId(roleDO.getId());
		roleDTO.setRoleName(roleDO.getRoleName());
		roleDTO.setRoleNote(roleDO.getRoleNote());
		roleDTO.setCreateTime(roleDO.getCreateTime());
		return roleDTO;
	}

	/**
	 * 校验用户角色参数
	 * 
	 * @param userRoleDTO 用户角色信息
	 * @return 校验结果
	 */
	private BaseResult<Integer> checkUserRoleParam(UserRoleDTO userRoleDTO) {
		BaseResult<Integer> result = new BaseResult<>();
		if (userRoleDTO == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("用户角色对象为空");
			return result;
		}
		if (userRoleDTO.getUserId() == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("用户主键ID为空");
			return result;
		}
		if (userRoleDTO.getRoleId() == null) {
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("角色主键ID为空");
			return result;
		}
		return result;
	}

	/**
	 * 将用户角色DTO对象转换为DO对象
	 * 
	 * @param userRoleDTO
	 * @return
	 */
	private AuUserRoleDO toUserRoleDO(UserRoleDTO userRoleDTO) {
		if (userRoleDTO == null) {
			return null;
		}
		AuUserRoleDO userRoleDO = new AuUserRoleDO();
		userRoleDO.setId(userRoleDTO.getId());
		userRoleDO.setUserId(userRoleDTO.getUserId());
		userRoleDO.setRoleId(userRoleDTO.getRoleId());
		userRoleDO.setCreateTime(userRoleDTO.getCreateTime());
		userRoleDO.setRole(toRoleDO(userRoleDTO.getRole()));
		return userRoleDO;
	}

	/**
	 * 将用户角色DO对象转换为DTO对象
	 * 
	 * @param userRoleDO
	 * @return
	 */
	private UserRoleDTO toUserRoleDTO(AuUserRoleDO userRoleDO) {
		if (userRoleDO == null) {
			return null;
		}
		UserRoleDTO userRoleDTO = new UserRoleDTO();
		userRoleDTO.setId(userRoleDO.getId());
		userRoleDTO.setUserId(userRoleDO.getUserId());
		userRoleDTO.setRoleId(userRoleDO.getRoleId());
		userRoleDTO.setCreateTime(userRoleDO.getCreateTime());
		userRoleDTO.setRole(toRoleDTO(userRoleDO.getRole()));
		return userRoleDTO;
	}

}
