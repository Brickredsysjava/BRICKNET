package org.example.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.OrderColumn;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Community")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
@ToString

public class Community implements Comparable<Community>{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2" , strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "post_id", columnDefinition = "VARCHAR(255)")
    private String  post_id;

    private String employee_code;

    private String title;

    private String description;

    private LocalDateTime date_time;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    @OrderColumn
    private List<ContentDetails> contents;


    private Boolean adminVerified;

//    private Boolean adminVerificationStatus;

    private String verificationStatusMessage;

    private List<String> likedEmployee=new ArrayList<>();

    public List<String> getLikedEmployee() {     return likedEmployee != null ? likedEmployee : new ArrayList<>(); }

    @Override
    public int compareTo(Community community) {
        // Compare the dates using the compareTo method of the Date class
        return community.date_time.compareTo(this.date_time);
    }
}
