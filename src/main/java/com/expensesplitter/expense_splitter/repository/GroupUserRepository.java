package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.GroupUser;
import com.expensesplitter.expense_splitter.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
    List<GroupUser> findByGroup(Group group);
}
