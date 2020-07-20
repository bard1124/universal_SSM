package com.bard.universal_ssm.framework.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bard.universal_ssm.dao.SysLogDao;
import com.bard.universal_ssm.framework.annotation.LoginUser;
import com.bard.universal_ssm.framework.annotation.SystemLog;
import com.bard.universal_ssm.framework.enumerate.OperationType;
import com.bard.universal_ssm.framework.exception.TokenException;
import com.bard.universal_ssm.framework.validation.Validation;
import com.bard.universal_ssm.framework.validation.annotation.Validated;
import com.bard.universal_ssm.model.bo.LoginUserBo;
import com.bard.universal_ssm.model.po.SysLogPo;
import com.bard.universal_ssm.model.po.SysTokenPo;
import com.bard.universal_ssm.service.SysTokenService;
import com.bard.universal_ssm.service.SysUserService;

import lombok.Setter;

//申明是个切面
@Aspect
// 申明是个spring管理的bean
@Component
// lombok日志
// @Slf4j
// 标记切面类的处理优先级,i值越小,优先级别越高.
@Order(1)
@ConfigurationProperties(prefix = "controller-aspect")
public class ControllerAspect {

	@Setter
	private List<String> excludeUrls;

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysTokenService sysTokenService;

	@Autowired
	private SysLogDao sysLogDao;

	@Pointcut("execution(public * com.bard.universal_ssm.controller.*.*(..))")
	public void controllerAspect() {
	}

	/**
	 * 方法执行
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "controllerAspect()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		HttpServletRequest request = null;
		LoginUserBo loginUserBo = null;
		SysTokenPo sysTokenPo = null;
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		request = attr.getRequest();
		// 拦截的方法参数
		Object[] args = point.getArgs();
		// 拦截的放参数类型
		Signature sig = point.getSignature();
		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}
		msig = (MethodSignature) sig;
		Method method = msig.getMethod();
		Class<?>[] parameterTypes = method.getParameterTypes();

		// 判断接口是否需要验证token
		if(checkToken(request.getServletPath())){
			String token = request.getHeader("Authorization");
			while(true) {
				if(token == null || "".equals(token))
					break;
				sysTokenPo = sysTokenService.getToken(token);
				if(sysTokenPo == null)
					break;
				loginUserBo = sysUserService.getLoginUser(sysTokenPo.getUserid());
//	        	if(loginUserBo.getIsTest() != 1) {
//		        	Calendar calendar = Calendar.getInstance();
//					calendar.setTime(sysTokenPo.getUpdateTime());
//					calendar.add(Calendar.HOUR_OF_DAY, sysTokenPo.getExpiredDate());
//					if(calendar.getTime().before(new Date())) {
//						loginUserBo = null;
//						break;
//					}
//	        	}
				if(!loginUserBo.getToken().getToken().equals(token))
					loginUserBo = null;
				break;
			}
			if(loginUserBo == null) {
				// token验证失败
				throw new TokenException();
			} else {
				sysTokenPo.setUpdateTime(new Date());
				sysTokenService.update(sysTokenPo);
				// 将token验证通过后获取的User对象，返回给变量
				for(int i = 0; i < args.length; i++) {
					if(LoginUserBo.class.isAssignableFrom(parameterTypes[i])) {
						for(Annotation ann: method.getParameterAnnotations()[i]) {
							if(ann instanceof LoginUser)
								args[i] = loginUserBo;
						}
					}
				}
			}
		}

		for(int i = 0; i < args.length; i++) {
			for(Annotation ann: method.getParameterAnnotations()[i]) {
				if(ann instanceof Validated)
					Validation.verify(args[i]);
			}
		}

		// 写入System
		if (method.isAnnotationPresent(SystemLog.class)) {
			SystemLog systemlog = method.getAnnotation(SystemLog.class);
			//方法通知前获取时间
			long start = System.currentTimeMillis();
			SysLogPo log = new SysLogPo();
			log.setCreateTime(new Date());
			if(systemlog.operType() == OperationType.LOGIN)
				log.setLogType(1);
			else
				log.setLogType(2);
			log.setRequestIp(getIp(request));
			if (loginUserBo == null) {
				log.setUsername("");// 用户账号
				log.setLoginName("");// 用户姓名
			} else {
				log.setUsername(loginUserBo.getUserName());
				log.setLoginName(loginUserBo.getLoginNo());
			}
			log.setDescription(null);
			log.setOrgId(null);
			log.setAboutComponent(systemlog.module());
//			log.setXtid(Constant.SYSTEM_KEY);// 系统主键(320500+6位序号)
//			log.setXtmc(Constant.SYSTEM_NAME_CN);// 系统名称
//			if (loginUserBo == null) {
//				log.setYhzh("");// 用户账号
//				log.setYhxm("");// 用户姓名
//			} else {
//				log.setYhzh(loginUserBo.getLoginNo());// 用户账号
//				log.setYhxm(loginUserBo.getUserName());// 用户姓名
//			}
//
//			// 终端地址(IP或移动终端mac)
//			log.setZddz(getIp(request));
//
//			// 获取操作时间
//			String czsj = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date());
//			log.setCzsj(czsj);// 操作时间
//
//			log.setMimc(systemlog.module());// 模块名称
//			log.setCznr(systemlog.methods());// 操作内容
//			// 操作类型(0登录 1查询 2新增 3修改 4删除)
////			switch (systemlog.operType()) {
////			case LOGIN:
////				log.setCzlx("0");
////				break;
////			case SELECT:
////				log.setCzlx("1");
////				break;
////			case INSERT:
////				log.setCzlx("2");
////				break;
////			case UPDATE:
////				log.setCzlx("3");
////				break;
////			case DELETE:
////				log.setCzlx("4");
////				break;
////			}
//			log.setCzlx(String.valueOf(systemlog.operType().ordinal()));
			Object obj = null;
			try {
				obj = process(point, args);
				long end = System.currentTimeMillis();
				// 将计算好的时间保存在实体中
//				log.setCzsj(String.valueOf((end - start)));// 运行时间
//				log.setCzjg("1");// 操作结果
				log.setExceptionDetail("");
				log.setResult("操作成功");
				// 保存进数据库
				sysLogDao.insert(log);
			} catch (Throwable e) {
				long end = System.currentTimeMillis();
				//log.setCzsj(MysqlxDatatypes.Scalar.String.valueOf((end - start)));// 运行时间
//				log.setCzsj(String.valueOf((end - start)));// 运行时间
//				log.setCzjg("0");// 操作结果
				log.setExceptionDetail(e.getMessage());
				log.setResult("操作失败");
				// 保存进数据库
				sysLogDao.insert(log);
				throw e;
			}
			return obj;
		}
		return process(point, args);
	}

	private Object process(ProceedingJoinPoint point, Object[] args) throws Throwable {
		//获取返回对象
		Object obj = point.proceed(args);
//		if(obj instanceof String) {
//			return obj;
//		}
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletResponse response =  attr.getResponse();
//		response.setCharacterEncoding("utf-8");
//		response.setContentType("application/json; charset=utf-8");
//		//将对象转换成message和data的结构体
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("message", "success");
//		map.put("data", obj);
//		ObjectMapper om = new ObjectMapper();
//		//由于js存在Long形精度问题，故将Long转换成String输出
//	    SimpleModule simpleModule = new SimpleModule();
//	    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//	    simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//	    om.registerModule(simpleModule);
//		om.writeValue(response.getOutputStream(), map);
		return obj;
	}

	private boolean checkToken(String url) {
		AntPathMatcher matcher = new AntPathMatcher();
		for(String pattern: excludeUrls)
			if(matcher.match(pattern, url))
				return false;
		return true;
	}

	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request){
		String ip = null;
		if (request.getHeader("x-forwarded-for") == null) {
			ip =  request.getRemoteAddr();
		}else{
			ip = request.getHeader("x-forwarded-for");
		}
		return ip;
	}
}
