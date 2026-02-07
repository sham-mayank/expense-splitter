package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.GroupUser;
import com.expensesplitter.expense_splitter.entity.User;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import com.expensesplitter.expense_splitter.repository.UserRepository;
import com.expensesplitter.expense_splitter.service.GroupUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group-users")
public class GroupUserController {

    private final GroupUserService groupUserService;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public GroupUserController(GroupUserService groupUserService, UserRepository userRepository, GroupRepository groupRepository) {
        this.groupUserService = groupUserService;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    @PostMapping
    public GroupUser addUserToGroup(@RequestBody GroupUserRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return groupUserService.addUserToGroup(user, group);
    }

    @GetMapping("/group/{groupId}")
    public List<GroupUser> getUsersByGroup(@PathVariable Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return groupUserService.getUsersByGroup(group);
    }

    // DTO for request
    public static class GroupUserRequest {
        private Long userId;
        private Long groupId;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public Long getGroupId() { return groupId; }
        public void setGroupId(Long groupId) { this.groupId = groupId; }
    }
}
