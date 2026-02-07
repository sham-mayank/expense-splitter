package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.GroupUser;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.GroupUserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupUserService {

    private final GroupUserRepository groupUserRepository;

    public GroupUserService(GroupUserRepository groupUserRepository) {
        this.groupUserRepository = groupUserRepository;
    }

    public GroupUser addUserToGroup(User user, Group group) {
        GroupUser groupUser = new GroupUser();
        groupUser.setUser(user);
        groupUser.setGroup(group);
        return groupUserRepository.save(groupUser);
    }

    public List<GroupUser> getUsersByGroup(Group group) {
        return groupUserRepository.findByGroup(group);
    }
}
