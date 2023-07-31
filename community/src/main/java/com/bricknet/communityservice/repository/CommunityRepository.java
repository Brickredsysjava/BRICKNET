package com.bricknet.communityservice.repository;

import com.bricknet.communityservice.model.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommunityRepository extends JpaRepository<CommunityPost,Long> {

//    Optional<CommunityPost> findImageByName(String filename);
}
