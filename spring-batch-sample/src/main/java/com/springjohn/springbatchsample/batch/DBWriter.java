package com.springjohn.springbatchsample.batch;

import com.springjohn.springbatchsample.model.User;
import com.springjohn.springbatchsample.repository.UserRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<User> {

    private final UserRepository userRepository;

    @Autowired
    public DBWriter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void write(List<? extends User> users) throws Exception {
        for (User user : users) {
            System.out.println("Saving to the database:" + user.toString());
            userRepository.save(user);
        }


    }
}
