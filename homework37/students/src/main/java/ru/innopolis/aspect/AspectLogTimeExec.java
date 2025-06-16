package ru.innopolis.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;


@Slf4j
//@Aspect
//@Component
public class AspectLogTimeExec {

    public static final String POINTCUT_CREATE_STUDENT = "execution(public * ru.innopolis.ru.innopolis.controller.impl.StudentControllerImpl.createStudent(..))";
    public static final String POINTCUT_CREATE_ONE_STUDENT_MANY_COURSES = "execution(public * ru.innopolis.ru.innopolis.controller.impl.StudentCourseControllerImpl.createOneStudentManyCourses(*))";
    public static final String POINTCUT_FIND_ALL_STUDENTS_ONE_COURSE = "execution(public * ru.innopolis.ru.innopolis.controller.impl.StudentCourseControllerImpl.findAllStudentsOneCourse(..))";

    private void returnLog(ProceedingJoinPoint proceedingJoinPoint, Long startTimeMillis, Long endTimeMillis, Long startTimeNanos, Long endTimeNanos) {
        log.info("Время выполнения метода {} = {} миллисекунд", proceedingJoinPoint.getSignature().getName(), endTimeMillis - startTimeMillis);
        log.info("Время выполнения метода {} = {} наносекунд", proceedingJoinPoint.getSignature().getName(), endTimeNanos - startTimeNanos);
    }


    @Around(POINTCUT_CREATE_STUDENT)
    public Object logCreateStudent(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTimeMillis = System.currentTimeMillis();
        Long startTimeNanos = System.nanoTime();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            Long endTimeMillis = System.currentTimeMillis();
            Long endTimeNanos = System.nanoTime();

            log.info("_____Метод \"Регистрация студента\":_____");
            returnLog(proceedingJoinPoint, startTimeMillis, endTimeMillis, startTimeNanos, endTimeNanos);
        }
    }

    @Around(POINTCUT_CREATE_ONE_STUDENT_MANY_COURSES)
    public Object logCreateOneStudentManyCourses(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTimeMillis = System.currentTimeMillis();
        Long startTimeNanos = System.nanoTime();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            Long endTimeMillis = System.currentTimeMillis();
            Long endTimeNanos = System.nanoTime();

            log.info("_____Метод \"Регистрация определенного студента на несколько курсов\":_____");
            returnLog(proceedingJoinPoint, startTimeMillis, endTimeMillis, startTimeNanos, endTimeNanos);
        }
    }

    @Around(POINTCUT_FIND_ALL_STUDENTS_ONE_COURSE)
    public Object logFindAllStudentsOneCourse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTimeMillis = System.currentTimeMillis();
        Long startTimeNanos = System.nanoTime();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            Long endTimeMillis = System.currentTimeMillis();
            Long endTimeNanos = System.nanoTime();

            log.info("_____Метод \"Получение полного списка студентов для определенного курса\":_____");
            returnLog(proceedingJoinPoint, startTimeMillis, endTimeMillis, startTimeNanos, endTimeNanos);
        }
    }
}
