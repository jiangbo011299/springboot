package com.jiangbo.service.impl;

import com.jiangbo.entity.Test;
import com.jiangbo.mapper.TestMapper;
import com.jiangbo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Test getTestById(Integer id) {
        return testMapper.getTestById(id);
    }

    @Override
    public void addTest(Test test) {
        testMapper.addTest(test);
    }
}
