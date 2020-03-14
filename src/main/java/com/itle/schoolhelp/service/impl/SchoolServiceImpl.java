package com.itle.schoolhelp.service.impl;

import com.itle.schoolhelp.mapper.SchoolMapper;
import com.itle.schoolhelp.pojo.School;
import com.itle.schoolhelp.service.ISchoolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther Luler
 * @date 2020/2/16
 */
@Service
public class SchoolServiceImpl implements ISchoolService {

    @Resource
    private SchoolMapper schoolMapper;

    @Override
    public List<School> findAllSchools() {
        return schoolMapper.findAllSchools();
    }

    @Override
    public School findSchoolById(Integer schoolId) {
        return schoolMapper.findSchoolById(schoolId);
    }

    @Override
    public int updateSchoolMsg(School school) {
        return schoolMapper.updateSchoolMsg(school);
    }

    @Override
    public int insertSchool(School school) {
        return schoolMapper.insertSchool(school);
    }

    @Override
    public School findSchoolByName(String schoolName) {
        return schoolMapper.findSchoolByName(schoolName);
    }
}
