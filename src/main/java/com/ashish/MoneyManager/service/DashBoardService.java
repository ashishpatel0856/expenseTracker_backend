package com.ashish.MoneyManager.service;
import com.ashish.MoneyManager.dto.ExpenseDto;
import com.ashish.MoneyManager.dto.IncomeDto;
import com.ashish.MoneyManager.dto.RecentTransactionDto;
import com.ashish.MoneyManager.entity.ProfileEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DashBoardService {
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final AppUserDetailsService userDetailsService;

    public Map<String,Object> getDashboardData(){
        ProfileEntity profile = userDetailsService.getCurrentProfile();
        Map<String,Object> returnValue = new LinkedHashMap<>();
        List< ExpenseDto> latestExpenses = expenseService.getLatest5ExpensesForCurrentUser();
        List<IncomeDto> latestIncomes =incomeService.getLast5IncomesForCurrentUser();


      List<RecentTransactionDto> recentTransactionDto =  Stream.concat(latestIncomes.stream().map(income ->
          RecentTransactionDto.builder()
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
                latestExpenses.stream().map( expense ->
                        RecentTransactionDto.builder()
                                .id(expense.getId())
                                .profileId(expense.getId())
                                .icon(expense.getIcon())
                                .name(expense.getName())
                                .amount(expense.getAmount())
                                .date(expense.getDate())
                                .createdAt(expense.getCreatedAt())
                                .updatedAt(expense.getUpdatedAt())
                                .type("expense")
                                .build()
                        ))
        .sorted((a,b) -> {
            int cmp =b.getDate().compareTo(a.getDate());
            if(cmp==0 && a.getCreatedAt()!=null && b.getCreatedAt()!=null){
                return b.getCreatedAt().compareTo(a.getCreatedAt());
            }
            return cmp;
        })
              .collect(Collectors.toList());

      returnValue.put("totalBalance",incomeService.getTotalIncomeForCurrentUser()
              .subtract(expenseService.getTotalExpensesForCurrentUser()));
      returnValue.put("totalIncomes",incomeService.getTotalIncomeForCurrentUser());
      returnValue.put("totalExpenses",expenseService.getTotalExpensesForCurrentUser());
      returnValue.put("recentfiveExpenses",latestExpenses);
      returnValue.put("recentfiveIncomes",latestIncomes);
      returnValue.put("recentTransactions",recentTransactionDto);
      return returnValue;
    }
}
