package com.allen.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allen.tool.result.BaseResult;
import com.allen.tool.result.ResultStatus;
import com.allen.user.dto.AuthorityDTO;
import com.allen.user.dto.RoleAuthorityDTO;
import com.allen.user.service.AuthorityService;

/**
 * 权限控制层
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@RestController
public class AuthorityController {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);

	/**
	 * 权限服务
	 */
	@Autowired
	private AuthorityService authorityService;

	/**
	 * 保存权限信息
	 * 
	 * @param authority 权限信息
	 * @return 保存结果
	 */
	@PostMapping("/authority")
	public BaseResult<AuthorityDTO> save(@RequestBody AuthorityDTO authority) {
		BaseResult<AuthorityDTO> result = null;
		try {
			result = authorityService.save(authority);
		} catch (Exception e) {
			LOGGER.error("保存权限信息失败，权限信息[{}]", authority.toString(), e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("保存权限信息失败");
		}
		return result;
	}

	/**
	 * 更新权限信息
	 * 
	 * @param authority 权限信息
	 * @return 更新结果
	 */
	@PutMapping("/authority")
	public BaseResult<AuthorityDTO> update(@RequestBody AuthorityDTO authority) {
		BaseResult<AuthorityDTO> result = null;
		try {
			result = authorityService.update(authority);
		} catch (Exception e) {
			LOGGER.error("更新权限信息失败，权限信息[{}]", authority.toString(), e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("更新权限信息失败");
		}
		return result;
	}

	/**
	 * 根据权限主键ID删除权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 删除结果，如果成功则返回删除的行数
	 */
	@DeleteMapping("/authority/{id}")
	public BaseResult<Integer> delete(@PathVariable Long id) {
		BaseResult<Integer> result = null;
		try {
			result = authorityService.delete(id);
		} catch (Exception e) {
			LOGGER.error("根据权限ID[{}]删除用户信息失败", id, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("根据权限ID[" + id + "]删除权限信息失败");
		}
		return result;
	}

	/**
	 * 保存角色权限信息
	 * 
	 * @param roleAuthoritis 角色权限列表
	 * @return 保存结果，如果成功返回保存的行数
	 */
	@PostMapping("/roleAuthority")
	public BaseResult<Integer> saveRoleAuthority(@RequestBody List<RoleAuthorityDTO> roleAuthoritis) {
		BaseResult<Integer> result = null;
		try {
			result = authorityService.saveRoleAuthority(roleAuthoritis);
		} catch (Exception e) {
			LOGGER.error("保存角色权限信息失败", e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("保存角色权限信息失败");
		}
		return result;
	}

	/**
	 * 删除角色权限信息，权限主键ID与角色主键ID二者必给其一，如果都给，则为And关系
	 * 
	 * @param roleAuthority 角色权限对象，封装入参
	 * @return 删除的行数
	 */
	@DeleteMapping("/roleAuthority")
	public BaseResult<Integer> deleteRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthority) {
		BaseResult<Integer> result = new BaseResult<>();
		if (roleAuthority == null) {
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("参数为空");
		}
		Long roleId = roleAuthority.getRoleId();
		Long authorityId = roleAuthority.getAuthorityId();
		try {
			result = authorityService.deleteRoleAuthority(roleId, authorityId);
		} catch (Exception e) {
			LOGGER.error("删除角色权限信息失败，角色ID：{}，权限ID：{}", roleId, authorityId, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("删除角色权限信息失败");
		}
		return result;
	}

	/**
	 * 根据权限主键ID获取权限信息
	 * 
	 * @param id 权限主键ID
	 * @return 权限信息
	 */
	@GetMapping("/authority/{id}")
	public BaseResult<AuthorityDTO> get(@PathVariable Long id) {
		BaseResult<AuthorityDTO> result = null;
		try {
			result = authorityService.get(id);
		} catch (Exception e) {
			LOGGER.error("根据权限ID[{}]获取权限信息失败", id, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("根据权限ID[" + id + "]获取权限信息失败");
		}
		return result;
	}

	/**
	 * 根据权限名称获取权限信息
	 * 
	 * @param authorityName 权限名称
	 * @return 权限信息
	 */
	@GetMapping("/authority/name/{authorityName}")
	public BaseResult<AuthorityDTO> getByAuthorityName(@PathVariable String authorityName) {
		BaseResult<AuthorityDTO> result = null;
		try {
			result = authorityService.getByAuthorityName(authorityName);
		} catch (Exception e) {
			LOGGER.error("根据权限名称[{}]获取权限信息失败", authorityName, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("根据权限名称[" + authorityName + "]获取权限信息失败");
		}
		return result;
	}

	/**
	 * 获取权限列表，角色主键ID及权限主键ID二者必给其一，两者都给定时为And关系
	 * 
	 * @param roleAuthority 角色权限对象，封装入参
	 * @return 权限列表
	 */
	@GetMapping("/roleAuthority")
	public BaseResult<List<RoleAuthorityDTO>> listRoleAuthority(@RequestBody RoleAuthorityDTO roleAuthority) {
		BaseResult<List<RoleAuthorityDTO>> result = new BaseResult<>();
		if (roleAuthority == null) {
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("参数为空");
		}
		Long roleId = roleAuthority.getRoleId();
		Long authorityId = roleAuthority.getAuthorityId();
		try {
			result = authorityService.listRoleAuthority(roleId, authorityId);
		} catch (Exception e) {
			LOGGER.error("获取角色权限信息失败，角色ID：{}，权限ID：{}", roleId, authorityId, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("获取角色权限信息失败");
		}
		return result;
	}
}
