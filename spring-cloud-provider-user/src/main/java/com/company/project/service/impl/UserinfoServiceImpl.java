package com.company.project.service.impl;

import com.company.project.dao.UserinfoMapper;
import com.company.project.model.Userinfo;
import com.company.project.service.UserinfoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/01/18.
 */
@Service
@Transactional
public class UserinfoServiceImpl extends AbstractService<Userinfo> implements UserinfoService {
    @Resource
    private UserinfoMapper userinfoMapper;

}
