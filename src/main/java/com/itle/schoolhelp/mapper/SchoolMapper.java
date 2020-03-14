package com.itle.schoolhelp.mapper;

import com.itle.schoolhelp.pojo.School;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther Luler
 * @date 2020/2/16
 */
@Mapper
public interface SchoolMapper {

    List<School> findAllSchools();

    School findSchoolById(Integer schoolId);

    int updateSchoolMsg(School school);

    int insertSchool(School school);

    School findSchoolByName(String schoolName);
}
