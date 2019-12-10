package com.ggfied.samplelogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggfied.samplelogin.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
	// Nothing
}
