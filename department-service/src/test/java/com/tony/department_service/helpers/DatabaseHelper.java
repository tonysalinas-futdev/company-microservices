package com.tony.department_service.helpers;


import com.tony.department_service.infraestructure.models.Department;
import com.tony.department_service.infraestructure.repositories.DepartmentRepository;
import org.awaitility.Durations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.awaitility.Awaitility.*;
import java.util.function.Predicate;

@Service
public class DatabaseHelper {
    @Autowired
    private DepartmentRepository repo;

    public Department findByIdAndPredicate(String id, Predicate<Department> departmentPredicate){
        await()
                .atLeast(Durations.TWO_HUNDRED_MILLISECONDS)
                .atMost(Durations.FIVE_MINUTES)
                .with()
                .pollInterval(Durations.TWO_HUNDRED_MILLISECONDS)
                .ignoreException(Exception.class)
                .until(()->repo.findById(id).isPresent() && departmentPredicate.test(repo.findById(id).get()));

        return  repo.findById(id).orElseThrow();
    }
}
