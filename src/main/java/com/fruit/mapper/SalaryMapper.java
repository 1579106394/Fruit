package com.fruit.mapper;

import com.fruit.pojo.Salary;

public interface SalaryMapper {

    // 新增工资
    void addSalary(Salary salary);

    // 编辑工资
    void editSalary(Salary salary);
}
