//package com.will.caleb.business.aspect;
//
//
//import com.will.caleb.business.model.entity.AbstractEntity;
//import com.will.caleb.business.service.EnterpriseService;
//import com.will.caleb.business.utils.Utils;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class BeforeSaveAspect {
//
//    private final EnterpriseService enterpriseService;
//
//    @Before("execution(* org.springframework.data.repository.CrudRepository+.save(..)) || " +
//            "execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..))")
//    public void beforeSave(JoinPoint joinPoint) {
//
//        Object entity = joinPoint.getArgs()[0];
//
//        if (Utils.isNotEmpty(entity)) {
//
//            if (entity instanceof AbstractEntity abstractEntity) {
//                abstractEntity.setEnterprise(enterpriseService.getByContext().getId());
//            }
//        }
//    }
//}
//
//
