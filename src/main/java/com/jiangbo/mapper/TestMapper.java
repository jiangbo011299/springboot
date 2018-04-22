package com.jiangbo.mapper;

import com.jiangbo.entity.Test;
import org.springframework.stereotype.Component;

@Component
public interface TestMapper {

    Test getTestById(Integer id);

    void addTest(Test test);
}
