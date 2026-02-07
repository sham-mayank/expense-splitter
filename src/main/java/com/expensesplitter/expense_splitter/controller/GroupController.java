package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.entity.GroupUser;
import com.expensesplitter.expense_splitter.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Create a new group
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group saved = groupService.createGroup(group);
        return ResponseEntity.ok(saved);
    }

    // Add a user to a group
    @PostMapping("/{groupId}/users")
    public ResponseEntity<GroupUser> addUserToGroup(@PathVariable Long groupId,
                                                    @RequestBody UserIdDTO userIdDTO) {
        GroupUser saved = groupService.addUserToGroup(groupId, userIdDTO.getUserId());
        return ResponseEntity.ok(saved);
    }

    // DTO to receive userId in request
    public static class UserIdDTO {
        private Long userId;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
    }
}
