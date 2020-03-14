package com.itle.schoolhelp.service.impl;

import com.itle.schoolhelp.mapper.AdminMapper;
import com.itle.schoolhelp.pojo.Admin;
import com.itle.schoolhelp.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @auther Luler
 * @date 2020/2/16
 */
@Service
public class AdminServiceImpl implements IAdminService{

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin findAdminById(String account) {
        return adminMapper.findAdminById(account);
    }

    @Override
    public int updateAdmin(Admin admin) {
        return adminMapper.updateAdmin(admin);
    }

    @Override
    public int insertAdmin(Admin admin) {
        return adminMapper.insertAdmin(admin);
    }
}
