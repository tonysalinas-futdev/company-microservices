package com.tony.department_service.shared;

import com.tony.department_service.infraestructure.models.Department;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class DatabaseResetManager implements TestExecutionListener {
    private MongoTemplate mongoTemplate;

    @Override
    public void beforeTestClass(TestContext context){
        mongoTemplate=context.getApplicationContext().getBean(MongoTemplate.class);
    }

    @Override
    public void beforeTestMethod(TestContext context){
        mongoTemplate.dropCollection(Department.class);
    }

    @Override
    public void afterTestClass(TestContext context){
        mongoTemplate.getDb().drop();
    }
}
