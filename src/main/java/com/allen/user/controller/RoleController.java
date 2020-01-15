package com.allen.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.allen.tool.result.BaseResult;
import com.allen.user.dto.RoleDTO;
import com.allen.user.dto.UserRoleDTO;
import com.allen.user.service.RoleService;

/**
 * 角色控制层，实践各种请求情况
 * 
 * @author Allen
 * @date 2020年1月8日
 * @since 1.0.0
 *
 */
@Controller
public class RoleController {

	/**
	 * 日志对象
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	/**
	 * 角色服务
	 */
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;

	/**
	 * 保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 保存结果
	 */
	@RequestMapping(path = { "/role/save", "/role" }, method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public BaseResult<RoleDTO> save(@RequestBody RoleDTO role) {
		BaseResult<RoleDTO> result = null;
		try {
			result = roleService.save(role);
		} catch (Exception e) {
			LOGGER.error("保存角色信息失败，角色信息[{}]", role.toString(), e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("保存角色信息失败");
		}
		return result;
	}

	/**
	 * 更新角色信息
	 * 
	 * @param role 角色信息
	 * @return 更新结果
	 */
	@PostMapping("/role/update")
	// @ResponseBody
	public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO role) {
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			BaseResult<RoleDTO> result = roleService.update(role);
			if (result.success()) {
				httpHeaders.add("success", "true");
				return new ResponseEntity<>(result.getData(), httpHeaders, HttpStatus.CREATED);
			} else {
				httpHeaders.add("success", "false");
				return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("更新角色信息失败，角色信息[{}]", role.toString(), e);
			httpHeaders.add("success", "false");
			return new ResponseEntity<>(httpHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 根据角色主键ID删除角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 删除结果
	 */
	public BaseResult<Integer> delete(Long id) {
		return null;
	}

	/**
	 * 保存用户角色信息
	 * 
	 * @param userRoles 用户角色列表
	 * @return 保存结果
	 */
	public BaseResult<Integer> saveUserRole(List<UserRoleDTO> userRoles) {
		return null;
	}

	/**
	 * 根据用户主键Id或角色Id删除用户角色信息，二者必须给其一
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 删除结果
	 */
	public BaseResult<Integer> deleteUserRole(Long userId, Long roleId) {
		return null;
	}

	/**
	 * 根据角色主键ID获取角色信息。 1）适用get和post方法。 2）请求格式：http://localhost:8001/role/get?id=1。
	 * 
	 * @param id 角色主键ID
	 * @return 角色信息
	 */
	@RequestMapping("/role/get")
	public ModelAndView getById(Long id) {
		ModelAndView mv = new ModelAndView();
		try {
			BaseResult<RoleDTO> result = roleService.get(id);
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			mv.setView(jsonView);
			mv.addObject("role", result.getData());
		} catch (Exception e) {
			LOGGER.error("根据角色Id获取角色信息失败，角色ID[{}]", id, e);
		}
		return mv;
	}

	/**
	 * 根据角色主键ID获取角色信息
	 * 
	 * @param id 角色主键ID
	 * @return 角色信息
	 */
	@GetMapping("/role/{id}")
	@ResponseBody
	public BaseResult<RoleDTO> get(@PathVariable("id") Long id) {
		BaseResult<RoleDTO> result = null;
		try {
			result = roleService.get(id);
		} catch (Exception e) {
			LOGGER.error("根据角色ID[{}]获取角色信息失败", id, e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("根据角色ID[" + id + "]获取角色信息失败");
		}
		return result;
	}

	/**
	 * 根据角色名称获取角色信息 1）适用get和post方法。
	 * 2）请求方式：http://localhost:8001/role/name?name=departmentManager
	 * 
	 * @param roleName 角色名称
	 * @return 角色信息
	 */
	@RequestMapping("/role/name")
	@ResponseBody
	public BaseResult<RoleDTO> getByRoleName(@RequestParam("name") String roleName) {
		BaseResult<RoleDTO> result = null;
		try {
			result = roleService.getByRoleName(roleName);
		} catch (Exception e) {
			LOGGER.error("根据角色名称[{}]获取角色信息失败", roleName, e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("根据角色名称[" + roleName + "]获取角色信息失败");
		}
		return result;
	}

	/**
	 * 获取用户角色信息，用户主键ID与角色主键ID二者必须给其一，如果两者都给则是And关系
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 用户角色信息列表
	 */
	@GetMapping("/role/{userId}/{roleId}")
	@ResponseBody
	public BaseResult<List<UserRoleDTO>> listUserRole(@PathVariable("userId") Long userId,
			@PathVariable("roleId") Long roleId) {
		BaseResult<List<UserRoleDTO>> result = null;
		try {
			result = roleService.listUserRole(userId, roleId);
		} catch (Exception e) {
			LOGGER.error("获取用户角色信息失败，用户ID：{}，角色ID：{}", userId, roleId, e);
			result = new BaseResult<>();
			result.setStatus(BaseResult.STATUS_SYSTEM_FAILURE);
			result.setMessage("获取用户角色信息失败");
		}
		return result;
	}
}
