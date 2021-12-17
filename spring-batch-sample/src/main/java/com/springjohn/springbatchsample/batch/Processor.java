package com.springjohn.springbatchsample.batch;

import com.springjohn.springbatchsample.model.User;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor implements ItemProcessor<User, User> {

    private static final Map<String, String> DEPT_NAMES = new HashMap<>();

    public Processor() {
        DEPT_NAMES.put("001", "Technology");
        DEPT_NAMES.put("002", "Operations");
        DEPT_NAMES.put("003", "Accounts");
    }

    /**
     * This processor will transform the department code for the name of the department.
     * @param user data coming from the CSV
     * @return {@link User}
     * @throws Exception
     */
    @Override
    public User process(User user) throws Exception {
        String departmentName = DEPT_NAMES.get(user.getDepartment());
        user.setDepartment(departmentName);
        System.out.println(String.format("Converting from [%s] to [%s] ", user.getDepartment(), departmentName));
        return user;
    }
}
