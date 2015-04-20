package com.spaceapps.util;

import com.spaceapps.core.LeafRepository;
import com.spaceapps.core.UserRepository;
import com.spaceapps.core.facade.AccountFacade;
import com.spaceapps.core.facade.LeafInfoFacade;
import org.springframework.context.ApplicationContext;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class BeanUtils {

    private static final String USER_REPOSITORY = "userRepository";
    private static final String ACCOUNT_FACADE = "accountFacade";
    private static final String LEAF_FACADE = "leafInfoFacade";
    private static final String LEAF_REPOSITORY = "leafInfoRepository";

    public static UserRepository getUserRepository(ApplicationContext context){
        return ((UserRepository)context.getBean(USER_REPOSITORY));
    }

    public static final AccountFacade getAccountFacade(ApplicationContext context){
        return ((AccountFacade)context.getBean(ACCOUNT_FACADE));
    }

    public static final LeafInfoFacade getLeafFacade(ApplicationContext context){
        return ((LeafInfoFacade)context.getBean(LEAF_FACADE));
    }

    public static final LeafRepository getLeafRepository(ApplicationContext context){
        return ((LeafRepository)context.getBean(LEAF_REPOSITORY));
    }
}
