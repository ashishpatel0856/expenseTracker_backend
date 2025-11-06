package com.ashish.MoneyManager.service;

import com.ashish.MoneyManager.dto.ExpenseDto;
import com.ashish.MoneyManager.entity.CategoryEntity;
import com.ashish.MoneyManager.entity.ExpenseEntity;
import com.ashish.MoneyManager.entity.ProfileEntity;
import com.ashish.MoneyManager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final CategoryService categoryService;

    private ExpenseEntity toEntity(ExpenseDto dto , ProfileEntity profile, CategoryEntity category){
        return ExpenseEntity.builder()
                .naame(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .category(category)
                .profile(profile)
                .build();
    }


    private ExpenseDto toDto(ExpenseEntity entity){
        return ExpenseDto.builder()
                .id(entity.getId())
                .name(entity.getNaame())
                .icon(entity.getIcon())
                .categoryId(entity.getCategory() != null ? entity.getCategory().getId() : null)
                .categoryName(entity.getCategory() != null ? entity.getCategory().getName() : null)
                .amount(entity.getAmount())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
