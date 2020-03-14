package com.itle.schoolhelp.mapper;

import com.itle.schoolhelp.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther Luler
 * @date 2020/2/16
 */
@Mapper
public interface AdminMapper {

    Admin findAdminById(String aId);

    int updateAdmin(Admin admin);

    int insertAdmin(Admin admin);
}
