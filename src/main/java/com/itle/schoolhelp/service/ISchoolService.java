package com.itle.schoolhelp.service;

import com.itle.schoolhelp.pojo.School;

import java.util.List;

/**
 * @auther Luler
 * @date 2020/2/16
 */
public interface ISchoolService {

    List<School> findAllSchools();

    School findSchoolById(Integer schoolId);

    int updateSchoolMsg(School school);

    int insertSchool(School school);

    School findSchoolByName(String schoolName);
}
