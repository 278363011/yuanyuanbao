package com.codebaobao.controller.admin;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codebaobao.exception.UserNotFoundException;
import com.codebaobao.exception.iIllegalStateException;
import com.codebaobao.model.Doctor;
import com.codebaobao.model.Roles;
import com.codebaobao.modeldo.LoginVo;
import com.codebaobao.result.CodeMsg;
import com.codebaobao.result.Result;
import com.codebaobao.service.DoctorService;
import com.codebaobao.service.RolesService;
import com.codebaobao.utils.JwtTokenUtil;
import com.codebaobao.utils.PwdUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author codebaobao
 * @since 2020-11-22
 */
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
	//本地role的缓存
	public static Map<Integer, String> roelMap = new HashMap<>();
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	DoctorService doctorService;
	@Autowired
	RolesService roleService;


	@RequestMapping("/login")
	public Result<String> adminLogin(@Valid @RequestBody final LoginVo loginVo) {
		final Doctor doctorInfo = this.doctorService.getOne(new QueryWrapper<Doctor>()
				.eq("account_name", loginVo.getUserName()));

		if (Objects.isNull(doctorInfo)) {
			return Result.error(CodeMsg.create(500, "账号或密码错误"));
		}
		if (roelMap.isEmpty()) {
			final List<Roles> list = this.roleService.list();
			if (Objects.isNull(list) || list.isEmpty()) {
				throw new iIllegalStateException("数据库角色信息");
			}
			roelMap = list.stream().collect(Collectors.toMap(Roles::getId, Roles::getRoleName));
		}
		if (PwdUtils.comparePassword(doctorInfo.getPwd(), loginVo.getPassWord(), doctorInfo.getSalt())) {
			final Map<String, Object> claims = new HashMap<>();
			claims.put("username", doctorInfo.getAccountName());
			claims.put("role", roelMap.get(doctorInfo.getRoleId()));
			return Result.success(this.jwtTokenUtil.generateToken(claims));
		} else {
			return Result.error(CodeMsg.create(500, "密码错误"));
		}
	}
	@RequestMapping("/authentication")
	public Result<Boolean> authentication(@Valid @RequestBody final LoginVo loginVo) {
		String token =loginVo.getVerification_code();
		final String authToken = token.substring("Bearer ".length());
		if(!this.jwtTokenUtil.isTokenExpired(token)){
			Claims claimsFromToken = this.jwtTokenUtil.getClaimsFromToken(authToken);
			if(claimsFromToken==null){
				return Result.success(false);
			}
			String roleName =(String) claimsFromToken.get("role");

			if(StringUtils.equalsIgnoreCase(roleName, "doctor") || StringUtils.equalsIgnoreCase(roleName, "admin") ){
				return Result.success(true);
			}else{
				return Result.success(false);
			}
		}else{
			return Result.success(false);
		}
	}


	@RequestMapping("/saveDoctorActor")
	public Result<Object> uploadAvtor(@Valid @RequestBody final LoginVo loginVo) {
		String token =loginVo.getVerification_code();
		final String authToken = token.substring("Bearer ".length());
		if(this.jwtTokenUtil.isTokenExpired(token)){
			Claims claimsFromToken = this.jwtTokenUtil.getClaimsFromToken(authToken);
			String username =(String) claimsFromToken.get("username");
			if(StringUtils.isBlank(username)){
				throw new IllegalStateException("用户名为空");
			}
			Doctor accountName = doctorService.getOne(new QueryWrapper<Doctor>().eq("account_name", username));
			if(Objects.isNull(accountName)){
				return Result.error(CodeMsg.create(500,"当前用户不存在"));
			}

			accountName.setImgsUrl(loginVo.getImgsUrl());
			doctorService.updateById(accountName);

			return Result.success("上传成功");
		}else{
			return Result.success(false);
		}
	}






}