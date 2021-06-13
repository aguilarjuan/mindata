package com.world.meet.w2m.aspect;

import com.world.meet.w2m.utils.FileUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MetricAspect {

	@Autowired
	private FileUtils fileUtils;

	@Around("@annotation(com.world.meet.w2m.annotation.ExecutionTimeMetric)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		String data = joinPoint.toShortString() + " executed in " + executionTime + "ms\n";
		fileUtils.writeLog(data);
		return proceed;
	}
}
