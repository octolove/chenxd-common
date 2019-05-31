package com.cxd.cool.repository;

import com.cxd.cool.entity.UserinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserinfoEntity, Integer> {

    public List<UserinfoEntity> findByUsernameAndPasswd(String username, String passwd);
}
