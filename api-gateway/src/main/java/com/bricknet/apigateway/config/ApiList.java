package com.bricknet.apigateway.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiList {


//    static String[] admin = new String[] {
//            "/user/profile/addProfile/**",
//            "/user/profile/addListOfProfile/**",
//            "/user/profile/allProfile",
//            "/user/profile/getAllEmail",
//            "/suggestionPost/api/verification/**",
//            "/api/broadcasting/**",
//            "/communityPost/admin/getAllPostNeedToVerified",
//            "/communityPost/admin/postVerification/**",
//            "/user/personal/updatePersonalDetails/**",
//            "/user/personal/addPersonalDetails/**",
//            "/user/personal/addListOfPersonalDetails/**",
//            "/user/personal/allPersonalDetails",
//            "/user/education/updateEducation/**",
//            "/user/education/addListOfEducation/**",
//            "/user/education/addEducation/**",
//            "/user/education/allEducation",
//            "/user/education/deleteEducation/**",
//            "/user/bank/updateBankDetails/**",
//            "/user/bank/addListOfBankDetails/**",
//            "/user/bank/addBankDetails/**",
//            "/user/address/updateAddress/**",
//            "/user/address/addListOfAddress/**",
//            "/user/address/addAddress/**",
//            "/user/address/test",
//            "/user/address/allAddress",
//            "/user/address/deleteAddress/**",
//            "/user/creation/**"
//    };

    static String[] openApi = new String[] {
            "/eureka/**",
            "/auth/**",
            "/communityPost/post/**",
            "/suggestionPost/api/suggestions/**",
            "/send/**",
            "/api/to-do/**",
            "/media/**",
            "/user/profile/passwordUpdate/**",
            "/user/profile/profileFromUserName/**",
            "/user/profile/timeLine/**",
            "/user/profile/getEmailByEmployeeCode/**",
            "/user/profile/fullName",
            "/user/profile/ProfileByEmployeeCode/**",
            "/user/profile/updateProfile/**",
            "/user/personal/PersonalDetailsByEmployeeCode/**",
            "/user/education/EducationByEmployeeCode/**",
            "/user/bank/bankDetailsByEmployeeCode/**",
            "/user/address/AddressByEmployeeCode/**",

            "/user/profile/addProfile/**",
            "/user/profile/addListOfProfile/**",
            "/user/profile/allProfile",
            "/user/profile/getAllEmail",
            "/suggestionPost/api/verification/**",
            "/api/broadcasting/**",
            "/communityPost/admin/getAllPostNeedToVerified",
            "/communityPost/admin/postVerification/**",
            "/user/personal/updatePersonalDetails/**",
            "/user/personal/addPersonalDetails/**",
            "/user/personal/addListOfPersonalDetails/**",
            "/user/personal/allPersonalDetails",
            "/user/education/updateEducation/**",
            "/user/education/addListOfEducation/**",
            "/user/education/addEducation/**",
            "/user/education/allEducation",
            "/user/education/deleteEducation/**",
            "/user/bank/updateBankDetails/**",
            "/user/bank/addListOfBankDetails/**",
            "/user/bank/addBankDetails/**",
            "/user/address/updateAddress/**",
            "/user/address/addListOfAddress/**",
            "/user/address/addAddress/**",
            "/user/address/test",
            "/user/address/allAddress",
            "/user/address/deleteAddress/**",
            "/user/creation/**"
    };

//    public static final ArrayList<String> adminApiList = (ArrayList<String>) Arrays.asList(admin);
//
//    public static final ArrayList<String> openApiList = (ArrayList<String>) Arrays.asList(openApi);

}
