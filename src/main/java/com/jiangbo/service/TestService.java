package com.jiangbo.service;

import com.jiangbo.entity.Test;

public interface TestService {

    Test getTestById(Integer id);

    void addTest(Test test);
}
