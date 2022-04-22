package com.cubicfox.dao.api;

import com.cubicfox.model.Company;
import com.cubicfox.model.User;

public interface CompanyDao {
    String COMPANY_BS = "company_bs";
    String COMPANY_CATCH_PHRASE = "company_catch_phrase";
    String COMPANY_NAME = "company_name";
    String TABLE_NAME = "cubicfox_company";
    Company saveCompany(User user);
}
