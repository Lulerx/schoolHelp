package com.itle.schoolhelp.service;

import com.itle.schoolhelp.pojo.Admin;

/**
 * @auther Luler
 * @date 2020/2/16
 */
public interface IAdminService {


    Admin findAdminById(String account);

    int updateAdmin(Admin admin);

    int insertAdmin(Admin admin);
}
