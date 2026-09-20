package com.kolekar.spring_boot_rest;

import com.kolekar.spring_boot_rest.model.JobPost;
import com.kolekar.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@CrossOrigin(origins = "http://localhost:3000/")
public class JobRestController {
    @Autowired
    private JobService service;

    @GetMapping("jobPosts")
//    @ResponseBody
    public List<JobPost> getAlljoobs(){
        return service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost gtJob(@PathVariable("postId") int postId){
       return service.getJob(postId);
    }

    @PostMapping(path = "jobPost", produces = {"applicatiopn/json"})
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJobPost(jobPost);
        return jobPost;

    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        service.deleteJob(postId);
        return "Deleted";
    }

    @GetMapping("load")
    public  String loadData(){

        service.load();
        return "success";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
         return service.search(keyword);
    }
}
