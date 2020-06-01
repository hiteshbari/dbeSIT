package com.infy.db.dbeSIT.components.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infy.db.dbeSIT.model.entity.NarConfigInfo;

public interface NarConfigInfoRepo extends JpaRepository<NarConfigInfo, Integer>{

	@Query(value = "SELECT COUNT(*)"
			+ " FROM tr_narconfiginfo"
			+ " WHERE narId = :narId", nativeQuery = true)
	public int isNarExist(String narId);
}
