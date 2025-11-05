package com.ashish.MoneyManager.service;

import com.ashish.MoneyManager.dto.CategoryDto;
import com.ashish.MoneyManager.entity.CategoryEntity;
import com.ashish.MoneyManager.entity.ProfileEntity;
import com.ashish.MoneyManager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProfileService profileService;

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        ProfileEntity profile = profileService.getCurrentProfile;
        if(categoryRepository.existByNameAndProfileId(categoryDto.getName(),profile.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
        }
        CategoryEntity newCategory =toEntity(categoryDto,profile);
        newCategory =categoryRepository.save(newCategory);
       return toDto(newCategory);
    }
    public CategoryEntity toEntity(CategoryDto categoryDto, ProfileEntity profileEntity) {
        return CategoryEntity.builder()
                .name(categoryDto.getName())
                .icon(categoryDto.getIcon())
                .profile(profileEntity)
                .type(categoryDto.getType())
                .build();

    }
    public CategoryDto toDto(CategoryEntity categoryEntity) {
        return CategoryDto.builder()
                .id(categoryEntity.getId())
                .profileId(categoryEntity.getProfile() !=null ? categoryEntity.getProfile().getId() : null)
                .name(categoryEntity.getName())
                .icon(categoryEntity.getIcon())
                .type(categoryEntity.getType())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .build();
    }
}
