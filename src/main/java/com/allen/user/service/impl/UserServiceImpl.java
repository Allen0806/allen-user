package com.allen.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allen.tool.result.BaseResult;
import com.allen.user.constant.GenderEnum;
import com.allen.user.dao.UserDAO;
import com.allen.user.data.AuUserDO;
import com.allen.user.dto.UserDTO;
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
	 * 用户DAO层接口
	 */
	@Autowired
	private UserDAO userDAO;

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

	/**
	 * 将DTO对象转换为DO对象
	 * 
	 * @param userDTO
	 * @return
	 */
	private AuUserDO toUserDO(UserDTO userDTO) {
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

}
