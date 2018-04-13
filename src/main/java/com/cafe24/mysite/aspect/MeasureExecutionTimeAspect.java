package com.cafe24.mysite.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MeasureExecutionTimeAspect {
	@Around("execution(* *..repository.*.*(..))")
	public Object roundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		//before advice code
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();

		//after advice code
		sw.stop();
		long totalTime = sw.getTotalTimeMillis();
		
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String taskName = className + "." + methodName;
		
		System.out.println("[ExecutionTime]"+"["+taskName+"]: "+totalTime);
		return result;
	}
}
