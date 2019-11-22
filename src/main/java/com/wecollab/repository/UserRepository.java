package com.wecollab.repository;


import com.wecollab.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /* the implementation will be provided to us in run time*/
    @Query("from User where companyId=?1")
    public List<User> findBycompanyId(int companyId);

}