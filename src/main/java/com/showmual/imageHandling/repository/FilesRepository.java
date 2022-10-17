package com.showmual.imageHandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.showmual.imageHandling.model.Files;

public interface FilesRepository extends JpaRepository<Files, Integer> {
	
	Files findByFno(int fno);
}
