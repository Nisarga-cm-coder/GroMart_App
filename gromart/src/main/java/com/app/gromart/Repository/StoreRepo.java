package com.app.gromart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.gromart.Entity.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {

}
