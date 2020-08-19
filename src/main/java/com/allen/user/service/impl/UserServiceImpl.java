package com.allen.user.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.allen.tool.result.BaseResult;
import com.allen.tool.result.StatusCode;
import com.allen.tool.string.StringUtil;
import com.allen.user.constant.GenderEnum;
import com.allen.user.dao.UserDAO;
import com.allen.user.dto.UserDTO;
import com.allen.user.model.AuRoleDO;
import com.allen.user.model.AuUserDO;
import com.allen.user.model.UserRoleDO;
import com.allen.user.service.RoleService;
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

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 用户DAO层接口
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * 角色服务
	 */
	@Autowired
	private RoleService roleService;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public BaseResult<UserDTO> save(UserDTO user) {
		BaseResult<UserDTO> result = checkParam(user);
		if (!result.isSuccessful()) {
			LOGGER.error("保存用户失败，失败原因：{}，用户信息：{}", result.getMessage(), user.toString());
			result.setData(user);
			return result;
		}
		if (user.getCreateTime() == null) {
			LocalDateTime createTime = LocalDateTime.now();
			user.setCreateTime(createTime);
		}
		AuUserDO userDO = toUserDO(user);
		int count = userDAO.save(userDO);
		if (count < 1) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("保存用户失败");
		} else {
			user.setId(userDO.getId());
		}
		result.setData(user);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<UserDTO> update(UserDTO user) {
		BaseResult<UserDTO> result = new BaseResult<>();
		if (user == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户对象为空");
			return result;
		}
		if (user.getId() == null) {
			LOGGER.error("更新用户失败，失败原因：用户主键ID为空，用户信息：{}", user.toString());
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户主键ID为空");
			result.setData(user);
			return result;
		}
		AuUserDO userDO = toUserDO(user);
		int count = userDAO.update(userDO);
		if (count < 1) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("更新用户失败");
		}
		result.setData(user);
		return result;
	}

	@Transactional
	@Override
	public BaseResult<Integer> delete(Long id) {
		if (id == null) {
			BaseResult<Integer> result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户主键ID为空");
			return result;
		}
		BaseResult<Integer> result = roleService.deleteUserRole(id, null);
		LOGGER.info("删除用户角色数量：{}，用户主键ID：{}", result.getData(), id);
		if (result.isSuccessful()) {
			int count = userDAO.delete(id);
			result.setData(count);
		}
		return result;
	}

	@Override
	public BaseResult<UserDTO> get(Long id) {
		BaseResult<UserDTO> result = new BaseResult<>();
		if (id == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户主键ID为空");
			return result;
		}
		AuUserDO userDO = userDAO.get(id);
		result.setData(toUserDTO(userDO));
		return result;
	}

	@Override
	public BaseResult<UserDTO> getByUserName(String userName) {
		BaseResult<UserDTO> result = new BaseResult<>();
		if (StringUtil.isBlank(userName)) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户名称为空");
			return result;
		}
		AuUserDO userDO = userDAO.getByUserName(userName);
		result.setData(toUserDTO(userDO));
		return result;
	}

	@Override
	public BaseResult<UserDTO> getUserWithRole(String userName) {
		if (StringUtil.isBlank(userName)) {
			BaseResult<UserDTO> result = new BaseResult<>();
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户名称为空");
			return result;
		}
		UserRoleDO userRoleDO = userDAO.getUserWithRole(userName);
		return handleUserRoleDO(userRoleDO);
	}

	/**
	 * 校验用户参数是否为空
	 * 
	 * @param user 用户信息
	 * @return 校验结果
	 */
	private BaseResult<UserDTO> checkParam(UserDTO user) {
		BaseResult<UserDTO> result = new BaseResult<>();
		if (user == null) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户对象为空");
			return result;
		}
		if (StringUtil.isBlank(user.getUserName())) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户名称为空");
			return result;
		}
		if (StringUtil.isBlank(user.getUserPassword())) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户密码为空");
			return result;
		}
		if (StringUtil.isBlank(user.getRealName())) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户姓名为空");
			return result;
		}
		if (StringUtil.isBlank(user.getUserGender())) {
			result.setStatusCode(StatusCode.SYSTEM_ERROR.getCode());
			result.setMessage("用户性别为空");
			return result;
		}
		return result;
	}

	/**
	 * 将DTO对象转换为DO对象
	 * 
	 * @param userDTO
	 * @return
	 */
	private AuUserDO toUserDO(UserDTO userDTO) {
		if (userDTO == null) {
			return null;
		}
		AuUserDO userDO = new AuUserDO();
		userDO.setId(userDTO.getId());
		userDO.setUserName(userDTO.getUserName());
		userDO.setUserPassword(userDTO.getUserPassword());
		userDO.setRealName(userDTO.getRealName());
		userDO.setUserAge(userDTO.getUserAge());
		userDO.setUserGender(GenderEnum.instanceOf(userDTO.getUserGender()));
		userDO.setCreateTime(userDTO.getCreateTime());
		userDO.setLastLoginTime(userDTO.getLastLoginTime());
		return userDO;
	}

	/**
	 * 将DO对象转换为DTO对象
	 * 
	 * @param userDO
	 * @return
	 */
	private UserDTO toUserDTO(AuUserDO userDO) {
		if (userDO == null) {
			return null;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userDO.getId());
		userDTO.setUserName(userDO.getUserName());
		userDTO.setUserPassword(userDO.getUserPassword());
		userDTO.setRealName(userDO.getRealName());
		userDTO.setUserAge(userDO.getUserAge());
		userDTO.setUserGender(userDO.getUserGender().value());
		userDTO.setCreateTime(userDO.getCreateTime());
		userDTO.setLastLoginTime(userDO.getLastLoginTime());
		return userDTO;
	}

	/**
	 * 处理用户角色信息
	 * 
	 * @param userRoleDO
	 * @return
	 */
	private BaseResult<UserDTO> handleUserRoleDO(UserRoleDO userRoleDO) {
		BaseResult<UserDTO> result = new BaseResult<>();
		if (userRoleDO == null) {
			return result;
		}
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userRoleDO.getId());
		userDTO.setUserName(userRoleDO.getUserName());
		userDTO.setUserPassword(userRoleDO.getUserPassword());
		userDTO.setRealName(userRoleDO.getRealName());
		userDTO.setUserAge(userRoleDO.getUserAge());
		userDTO.setUserGender(userRoleDO.getUserGender().value());
		userDTO.setCreateTime(userRoleDO.getCreateTime());
		userDTO.setLastLoginTime(userRoleDO.getLastLoginTime());
		List<AuRoleDO> roles = userRoleDO.getRoles();
		if (roles != null) {
			roles.parallelStream().forEach(role -> LOGGER.info("用户[{}]包含的角色信息：{}", userRoleDO.getUserName(), role));
		}
		result.setData(userDTO);
		return result;
	}

}
