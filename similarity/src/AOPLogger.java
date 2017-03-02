

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AOPLogger {

	private static final Logger logger = LoggerFactory.getLogger(AOPLogger.class);

	@Pointcut("execution(* com.ue.maxdata.controller.*.*(..))")
	public void pointcutExpression() {

	}

	// @Before("pointcutExpression()")
	// public void beforeMethod(JoinPoint jP) {
	// logger.info("stats.调用了{}类的{}方法，参数为{}", jP.getSignature().getClass(),
	// jP.getSignature().getName(), jP.getArgs());
	// }
	//
	// @AfterThrowing(pointcut = "pointcutExpression()", throwing = "ex")
	// public void doRecoverActions(JoinPoint jP, Throwable ex) {
	// logger.error("controller异常：{}", ex.getMessage());
	//
	// }

	@Around("pointcutExpression()")
	public Object doAround(ProceedingJoinPoint call) throws Throwable {
		Object result = null;

		Long time1 = System.currentTimeMillis();

		try {
			result = call.proceed();
		} catch (Throwable e) {
			logger.error("{}类的{}方法抛出异常：{},参数{}", call.getTarget().getClass(), call.getSignature().getName(),
					e.getMessage(), call.getArgs());
			throw e;
		}

		Long time2 = System.currentTimeMillis();

		logger.info("stats，{}类的{}方法被调用，参数{}，耗时[{}]", call.getTarget().getClass(), call.getSignature().getName(),
				call.getArgs(), time2 - time1);

		return result;
	}

}
