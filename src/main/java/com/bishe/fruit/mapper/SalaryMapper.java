package com.bishe.fruit.mapper;

import com.bishe.fruit.pojo.Salary;

public interface SalaryMapper {

    // 新增工资
    void addSalary(Salary salary);

    // 编辑工资
    void editSalary(Salary salary);
}
