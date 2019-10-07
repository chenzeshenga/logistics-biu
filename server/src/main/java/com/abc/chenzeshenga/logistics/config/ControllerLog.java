package com.abc.chenzeshenga.logistics.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

/**
 * @author chenz
 */
@Slf4j @Aspect @Component public class ControllerLog {

    private static final ThreadLocal<Long> START_TIME_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocalStartTime");
    private static final ThreadLocal<String> LOG_PREFIX_THREAD_LOCAL = new NamedThreadLocal<>("ThreadLocalLogPrefix");

    /**
     * <li>Before       : 在方法执行前进行切面</li>
     * <li>execution    : 定义切面表达式</li>
     * <p>public * com.controller..*.*(..))
     * <li>public :匹配所有目标类的public方法，不写则匹配所有访问权限</li>
     * <li>第一个* :方法返回值类型，*代表所有类型 </li>
     * <li>第二个* :包路径的通配符</li>
     * <li>第三个..* :表示impl这个目录下所有的类，包括子目录的类</li>
     * <li>第四个*(..) : *表示所有任意方法名,..表示任意参数</li>
     * </p>
     */
    @Pointcut("execution(public * com.abc.chenzeshenga.logistics.controller..*.*(..))") public void executionMethod() {
        // pointcut with empty container
    }

    @Before("executionMethod()") public void doBefore(JoinPoint joinPoint) {
        START_TIME_THREAD_LOCAL.set(System.currentTimeMillis());
        StringBuilder argsDes = new StringBuilder();
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            argsDes.append("第").append(i + 1).append("个参数为:").append(args[i]).append(" ");
        }
        String logPrefix = className + "." + methodName;
        LOG_PREFIX_THREAD_LOCAL.set(logPrefix);
        log.info(logPrefix + " Begin 入参为:{}", argsDes.toString());
    }

    @AfterReturning(pointcut = "executionMethod()", returning = "rtn") public Object doAfter(Object rtn) {
        long endTime = System.currentTimeMillis();
        long begin = START_TIME_THREAD_LOCAL.get();
        log.info(LOG_PREFIX_THREAD_LOCAL.get() + " End 出参为:{}", rtn, endTime - begin);
        log.info(LOG_PREFIX_THREAD_LOCAL.get() + " 耗时:{}ms", endTime - begin);
        destroyThreadLocal();
        return rtn;
    }

    private static void destroyThreadLocal() {
        START_TIME_THREAD_LOCAL.remove();
        LOG_PREFIX_THREAD_LOCAL.remove();
    }

}
