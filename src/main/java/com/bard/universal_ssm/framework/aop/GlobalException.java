package com.bard.universal_ssm.framework.aop;

import com.bard.universal_ssm.framework.exception.BusinessException;
import com.bard.universal_ssm.framework.exception.TokenException;
import com.bard.universal_ssm.framework.exception.ValidationException;
import com.bard.universal_ssm.framework.handle.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;

@ControllerAdvice
@RestController
@Slf4j
public class GlobalException {
	
	/**
	 * 拦截捕捉自定义异常
	 * @param myex
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	public ResultVo exceptionHandler(HttpServletResponse res, Throwable exp) {
		ResultVo resultVo = new ResultVo();
		if(exp instanceof UndeclaredThrowableException)
			exp = ((UndeclaredThrowableException) exp).getUndeclaredThrowable();
		if(exp instanceof BusinessException) {
			//业务类异常返回400
			resultVo.setMessage(exp.getMessage());
			res.setStatus(400);
			log.warn(exp.getMessage());
		} else if (exp instanceof TokenException) {
			//token验证失败返回403
			resultVo.setMessage(exp.getMessage());
			res.setStatus(403);
			log.warn(exp.getMessage());
		} else if (exp instanceof ValidationException) {
			resultVo.setMessage(exp.getMessage());
			resultVo.setData(((ValidationException) exp).getMessageList());
			res.setStatus(420);
		} else {
			//内部错误返回500
			resultVo.setMessage("操作失败");
			res.setStatus(500);
			// 打印到控制台
			exp.printStackTrace();
			// 发生异常进行日志记录，写入数据库或者其他处理，此处省略
			log.error("异常信息:" + exp.getMessage());
		}
		return resultVo;
	}
}
