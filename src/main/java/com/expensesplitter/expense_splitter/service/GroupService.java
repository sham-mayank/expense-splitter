package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.entity.GroupUser;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.GroupUserRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final GroupUserRepository groupUserRepository;

    public GroupService(GroupRepository groupRepository,
                        UserRepository userRepository,
                        GroupUserRepository groupUserRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.groupUserRepository = groupUserRepository;
    }

    // Create a new group
    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    // Add user to group
    public GroupUser addUserToGroup(Long groupId, Long userId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(group);
        groupUser.setUser(user);
        return groupUserRepository.save(groupUser);
    }
}
