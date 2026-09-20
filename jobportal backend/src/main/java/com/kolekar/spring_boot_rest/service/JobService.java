package com.kolekar.spring_boot_rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kolekar.spring_boot_rest.model.JobPost;
import com.kolekar.spring_boot_rest.repo.JobRepo;


@Service
public class JobService {
	@Autowired
	public JobRepo repo;

	// method to add a jobPost
	public void addJobPost(JobPost jobPost) {
//		 repo.addJobPost(jobPost);
		repo.save(jobPost);
	}

	//method to return all JobPosts
	public List<JobPost> getAllJobs() {
//		return repo.getAllJobs();
		return repo.findAll();

	}


	public JobPost getJob(int postId) {
//		return repo.getJob(postId);
		return repo.findById(postId).orElse(new JobPost());
	}

	public void updateJob(JobPost jobPost) {
//		repo.updateJob(jobPost);
		repo.save(jobPost);
	}

	public void deleteJob(int postId) {
//		repo.deletJob(postId);
		repo.deleteById(postId);
	}

	public void load() {
		List<JobPost> jobs = new ArrayList<>(List.of(
				new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2, List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
				new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3, List.of("HTML", "CSS", "JavaScript", "React")),
				new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4, List.of("Python", "Machine Learning", "Data Analysis")),
				new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5, List.of("Networking", "Cisco", "Routing", "Switching")),
				new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3, List.of("iOS Development", "Android Development", "Mobile App"))
		));

		repo.saveAll(jobs);
	}

	public List<JobPost> search(String keyword) {
		return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}
}
