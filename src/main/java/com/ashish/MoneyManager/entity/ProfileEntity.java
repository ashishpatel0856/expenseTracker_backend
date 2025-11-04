package com.ashish.MoneyManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class ProfileEntity{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String fullName;

@Column(unique = true)
private String email;

private String Password;
private String profileImageUrl;

@Column(updatable = false)
@CreationTimestamp
private LocalDateTime createdAt;

@UpdateTimestamp
private LocalDateTime updatedAt;
private Boolean isActive;
private String activationToken;

@PrePersist
public void prePersist(){
    if(this.isActive==null){
        isActive=false;
    }
}

}
