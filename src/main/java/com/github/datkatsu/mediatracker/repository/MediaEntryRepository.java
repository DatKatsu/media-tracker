package com.github.datkatsu.mediatracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.github.datkatsu.mediatracker.model.MediaEntry;

@Repository
public interface MediaEntryRepository extends JpaRepository<MediaEntry, Long>, JpaSpecificationExecutor<MediaEntry> {

}
