package com.ashish.MoneyManager.service;

import com.ashish.MoneyManager.dto.ExpenseDto;
import com.ashish.MoneyManager.dto.IncomeDto;
import com.ashish.MoneyManager.dto.RecentTransactionDto;
import com.ashish.MoneyManager.entity.ExpenseEntity;
import com.ashish.MoneyManager.entity.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.DoubleStream.concat;

@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;
    private final AppUserDetailsService userDetailsService;

    public Map<String,Object> getDashboardData(){
        ProfileEntity profile = userDetailsService.getCurrentProfile();
        Map<String,Object> returnValue = new LinkedHashMap<>();
        List< ExpenseDto> latestExpenses = expenseService.getLatest5ExpensesForCurrentUser();
        List<IncomeDto> latestIncomes =incomeService.getLast5IncomesForCurrentUser();
        concat(latestIncomes.stream().map(IncomeDto income -> RecentTransactionDto.builder()
                .id(income.getId())
                .profileId(profile.getId())
                .icon(income.getIcon())
                .name(income.getName())
                .amount(income.getAmount())
                .date(income.getDate())
                .createdAt(income.getCreatedAt())
                .updatedAt(income.getUpdatedAt())
                .type("income")
                .build()),
                latestExpenses.stream().
                )
    }
}
