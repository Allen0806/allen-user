package com.allen.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.allen.tool.json.JsonUtil;
import com.allen.tool.result.BaseResult;
import com.allen.tool.result.ResultStatus;
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
	@RequestMapping(path = { "/role" }, method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public BaseResult<RoleDTO> save(@RequestBody RoleDTO role) {
		BaseResult<RoleDTO> result = null;
		try {
			result = roleService.save(role);
		} catch (Exception e) {
			LOGGER.error("保存角色信息失败，角色信息[{}]", role.toString(), e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("保存角色信息失败");
		}
		return result;
	}

	/**
	 * 保存角色信息
	 * 
	 * @param role 角色信息
	 * @return 保存结果
	 */
	@RequestMapping(path = "/role/save", method = RequestMethod.POST)
	@ResponseBody
	public BaseResult<RoleDTO> saveRole(HttpServletRequest request) {
		BaseResult<RoleDTO> result = new BaseResult<>();
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {
			reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			LOGGER.error("获取request参数失败", e);
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("获取request参数失败");
		} finally {
			try {
				if (null != reader) {
					reader.close();
				}
			} catch (IOException e) {
				LOGGER.error("获取request参数失败", e);
				result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
				result.setMessage("获取request参数失败");
			}
		}
		if (sb.length() == 0) {
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("获取request参数为空");
		}
		if (!result.isSuccessful()) {
			return result;
		}
		RoleDTO role = null;
		try {
			role = JsonUtil.json2Object(sb.toString(), RoleDTO.class);
			result = roleService.save(role);
		} catch (Exception e) {
			LOGGER.error("保存角色信息失败，角色信息[{}]", role, e);
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
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
	@PutMapping("/role")
	// @ResponseBody
	public ResponseEntity<RoleDTO> update(@RequestBody RoleDTO role) {
		HttpHeaders httpHeaders = new HttpHeaders();
		try {
			BaseResult<RoleDTO> result = roleService.update(role);
			if (result.isSuccessful()) {
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
	@DeleteMapping("/role/{id}")
	@ResponseBody
	public BaseResult<Integer> delete(@PathVariable Long id) {
		BaseResult<Integer> result = null;
		try {
			result = roleService.delete(id);
		} catch (Exception e) {
			LOGGER.error("根据角色ID[{}]删除角色信息失败", id, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("根据角色ID[" + id + "]删除角色信息失败");
		}
		return result;
	}

	/**
	 * 保存用户角色信息
	 * 
	 * @param userRoles 用户角色列表
	 * @return 保存结果
	 */
	@PostMapping("/userRole")
	@ResponseBody
	public BaseResult<Integer> saveUserRole(@RequestBody List<UserRoleDTO> userRoles) {
		BaseResult<Integer> result = null;
		try {
			result = roleService.saveUserRole(userRoles);
		} catch (Exception e) {
			LOGGER.error("保存用户角色信息失败", e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("保存用户角色信息失败");
		}
		return result;
	}

	/**
	 * 根据用户主键Id或角色Id删除用户角色信息，二者必须给其一。
	 * http://localhost:8001/userRole?userId=1&roleId=1
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 删除结果
	 */
	@DeleteMapping("/userRole")
	@ResponseBody
	public BaseResult<Integer> deleteUserRole(Long userId, Long roleId) {
		BaseResult<Integer> result = null;
		try {
			result = roleService.deleteUserRole(userId, roleId);
		} catch (Exception e) {
			LOGGER.error("删除用户角色信息失败，用户ID：{}，角色ID：{}", userId, roleId, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("删除用户角色信息失败");
		}
		return result;
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
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
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
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("根据角色名称[" + roleName + "]获取角色信息失败");
		}
		return result;
	}

	/**
	 * 获取用户角色信息，用户主键ID与角色主键ID二者必须给其一，如果两者都给则是And关系。
	 * 请求方式：http://localhost:8001/userRole?userId=1&roleId=1
	 * 
	 * @param userId 用户主键ID
	 * @param roleId 角色主键ID
	 * @return 用户角色信息列表
	 */
	@GetMapping("/userRole")
	@ResponseBody
	public BaseResult<List<UserRoleDTO>> listUserRole(Long userId, Long roleId) {
		BaseResult<List<UserRoleDTO>> result = null;
		try {
			result = roleService.listUserRole(userId, roleId);
		} catch (Exception e) {
			LOGGER.error("获取用户角色信息失败，用户ID：{}，角色ID：{}", userId, roleId, e);
			result = new BaseResult<>();
			result.setStatusCode(ResultStatus.SYSTEM_ERROR.getCode());
			result.setMessage("获取用户角色信息失败");
		}
		return result;
	}
}
