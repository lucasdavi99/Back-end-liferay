package com.liferay.CommunityApp.repositories;

import com.liferay.CommunityApp.models.ReportModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository extends JpaRepository<ReportModel, UUID> {
}
