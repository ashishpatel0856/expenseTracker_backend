package com.ashish.MoneyManager.service;

import com.ashish.MoneyManager.dto.ExpenseDto;
import com.ashish.MoneyManager.dto.IncomeDto;
import com.ashish.MoneyManager.entity.CategoryEntity;
import com.ashish.MoneyManager.entity.ExpenseEntity;
import com.ashish.MoneyManager.entity.IncomeEntity;
import com.ashish.MoneyManager.entity.ProfileEntity;
import com.ashish.MoneyManager.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final CategoryService categoryService;
    private final IncomeRepository incomeRepository;

    private IncomeEntity toEntity(IncomeDto dto , ProfileEntity profile, CategoryEntity category){
        return IncomeEntity.builder()
                .naame(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .category(category)
                .profile(profile)
                .build();
    }


    private IncomeDto toDto(IncomeEntity entity){
        return IncomeDto.builder()
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
