package com.xwckeji.gw.server.service;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import com.xwckeji.gw.server.model.JobModel;

public interface JobService {

	void saveJob(JobModel jobModel);

	void doJob(JobDataMap jobDataMap);

	void deleteJob(Long id) throws SchedulerException;
}
