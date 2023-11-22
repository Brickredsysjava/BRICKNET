package com.example.SuperAdmin.Repository;

import com.example.SuperAdmin.DTO.AddressDTO;
import com.example.SuperAdmin.DTO.BankDetailsDTO;
import com.example.SuperAdmin.DTO.EducationDTO;
import com.example.SuperAdmin.DTO.PersonalDetailsDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class CustomQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<EducationDTO> getEducationDetailByEmployeeCode(String employee_code) {
        try{
            String query = "select * from education where user_id= " +
                    "(select user_id from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code))";

            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);
            List<EducationDTO> educationDTOList = new ArrayList<>();

            List<Object[]> objects = q.getResultList();
            for (Object o : objects) {
                Object[] row = (Object[]) o;
                EducationDTO educationDTO = new EducationDTO();

                educationDTO.setId((String) row[0]);
                educationDTO.setBoard((String) row[1]);
                educationDTO.setInstituteName((String) row[2]);
                educationDTO.setPassingYear((String) row[3]);
                educationDTO.setPercentage((String) row[4]);
                educationDTO.setPlace((String) row[5]);
                educationDTO.setTypeOfEducation((String) row[6]);

                educationDTOList.add(educationDTO);
            }

            return educationDTOList;
        }
        catch(Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    public PersonalDetailsDTO getPersonalDetailsByEmployeeCode(String employee_code) {
        try{
            String query = "select * from personal_details where id= " +
                    "(select personal_details_id from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code))";

            PersonalDetailsDTO personalDetailsDTO = new PersonalDetailsDTO();


            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);


            Object result = q.getSingleResult();
            Object[] row = (Object[]) result;


            personalDetailsDTO.setId((String) row[0]);
            personalDetailsDTO.setDateOfBirth((String) row[1]);
            personalDetailsDTO.setFatherName((String) row[2]);
            personalDetailsDTO.setInternationalEmployee((String) row[3]);
            personalDetailsDTO.setMaritalStatus((String) row[4]);
            personalDetailsDTO.setNationality((String) row[5]);
            personalDetailsDTO.setPhysicallyChallenged((String) row[6]);
            personalDetailsDTO.setPlaceOfBirth((String) row[7]);
            personalDetailsDTO.setReligion((String) row[8]);

            return personalDetailsDTO;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }


    @Transactional
    public BankDetailsDTO getBankDetailsByEmployeeCode(String employee_code) {
        try{
            String query = "select * from bank_details where id= " +
                    "(select bank_details_id from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code))";

            BankDetailsDTO bankDetailsDTO = new BankDetailsDTO();

            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);

            Object result = q.getSingleResult();
            Object[] row = (Object[]) result;

            bankDetailsDTO.setId((String) row[0]);
            bankDetailsDTO.setAccountNumber((String) row[1]);
            bankDetailsDTO.setBankName((String) row[2]);
            bankDetailsDTO.setBranchName((String) row[3]);
            bankDetailsDTO.setIfscCode((String) row[4]);
            bankDetailsDTO.setPanNumber((String) row[5]);

            return bankDetailsDTO;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    public List<AddressDTO> getAddressByEmployeeCode(String employee_code) {
        try{
            String query = "select * from address where user_id= " +
                    "(Select user_id from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code))";

            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);

            List<AddressDTO> addressDTOList = new ArrayList<>();

            List<Object> objects =  q.getResultList();

            for (Object o : objects) {
                Object[] row = (Object[]) o;
                AddressDTO addressDTO = new AddressDTO();

                addressDTO.setId((String) row[0]);
                addressDTO.setApartment((String) row[1]);
                addressDTO.setCity((String) row[2]);
                addressDTO.setCountry((String) row[3]);
                addressDTO.setDistrict((String) row[4]);
                addressDTO.setPincode((String) row[5]);
                addressDTO.setState((String) row[6]);
                addressDTO.setStreetAddress((String) row[7]);
                addressDTO.setTypeOfAddress((String) row[8]);

                addressDTOList.add(addressDTO);
            }
            return addressDTOList;
        }
        catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    public String getUserByEmployeeCode (String employee_code) {
        String query = "select user_id from user where profile_id = " +
                "(select id from profile where employee_code = :employee_code)";

        Query q = entityManager.createNativeQuery(query);
        q.setParameter("employee_code",employee_code);

        return (String) q.getSingleResult();

    }

    public String getBankDetailIdfromEmployeeCode(String empCode)
    {
        String query = "select bank_details_id from user where " +
                "user_id=(select user_id from user where profile_id=(select id from profile where employee_code= :empCode))";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("empCode", empCode);
        String res = (String) q.getSingleResult();
        return res;
    }

    public String getProfileIDFromEmpCode(String emp_code){
        String query = "select profile_id from user where profile_id" +
                "=(select id from profile where employee_code= :emp_code)";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("emp_code", emp_code);
        String res = (String) q.getSingleResult();
        return res;
    }

    public String getAddressIdFromEmpCode(String empCode, String typeOfAddress){
        String query = "select id from address where " +
                "user_id=(select user_id from user where profile_id=(select id from profile where employee_code= :empCode))" +
                "and type_of_address = :typeOfAddress";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("empCode", empCode);
        q.setParameter("typeOfAddress", typeOfAddress);
        String res = (String) q.getSingleResult();
        return res;
    }
    public String getEducationIdByEmployeeCode(String empCode, String type_of_education){
        String query = "select id from education where user_id = (select user_id from " +
                "user where profile_id = (select profile_id from profile where employee_code = :empCode)) and " +
                "type_Of_Education = :typeOfEducation";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("empCode", empCode);
        q.setParameter("typeOfEducation", type_of_education);
        String res = (String) q.getSingleResult();
        return res;
    }

    public String getPersonalDetailsIdByEmpCode(String empCode){
        String query = "select personal_details_id from user where user_id = (select user_id from " +
                "user where profile_id = (select profile_id from profile where employee_code = :empCode))";
        Query q = entityManager.createNativeQuery(query);
        q.setParameter("empCode", empCode);
        String res = (String) q.getSingleResult();
        return res;
    }

    @Transactional
    public String deleteAddress (String employee_code) {
        try{
            String query = "Select bank_details_id  from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code)";

            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);

            return (String) q.getSingleResult();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return "Data Not Found";
    }

    @Transactional
    public String deleteUser(String employee_code) {
        try{
            String query = "(Select user_id  from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code)";


            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);

            return (String) q.getSingleResult();
        }
        catch (Exception e) {
            e.getMessage();
        }
        return "Data Not Found";
    }

    @Transactional
    public String deleteEducation(String employee_code, String typeOfEducation ) {
        try{
            String query = "select * from education where user_id= " +
                    "(Select user_id  from user where profile_id= " +
                    "(select id from profile where employee_code= :employee_code))";


            Query q = entityManager.createNativeQuery(query);
            q.setParameter("employee_code", employee_code);

            List<EducationDTO> educationDTO = (List<EducationDTO>) q.getResultList();
            for (Object e: educationDTO) {
                Object[] row = (Object[]) e;

                if(typeOfEducation.equals((String)row[6]))
                    return (String)row[0];
            }
            return "Data Not Found";
        }
        catch (Exception e) {
            e.getMessage();
        }
        return "Data Not Found";
    }

    @Transactional
    public List<String> getAllEmails() {
        String query = "select company_email from profile";

        Query q = entityManager.createNativeQuery(query);

        return (List<String>) q.getResultList().stream().map(i-> (String) i).collect(Collectors.toList());
    }
}
