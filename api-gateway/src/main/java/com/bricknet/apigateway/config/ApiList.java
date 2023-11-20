package com.bricknet.apigateway.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiList {


    static String[] admin = new String[] {"/suggestionPost/api/verification/**","/communityPost/admin/**"};

    static String[] openApi = new String[] {"/eureka/**","super-admin/swagger-ui/index.html", "/auth/**",
            "/user/**","/communityPost/post/**", "/send/**", "/api/broadcasting/**", "/api/to-do/**", "/media/**", "/suggestionPost/api/suggestions/**"
    };

//    public static final ArrayList<String> adminApiList = (ArrayList<String>) Arrays.asList(admin);
//
//    public static final ArrayList<String> openApiList = (ArrayList<String>) Arrays.asList(openApi);

}
