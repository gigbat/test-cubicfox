package com.cubicfox.dao.impl;

import com.cubicfox.dao.AbstractGenericPostgresExecutor;
import com.cubicfox.dao.api.CompanyDao;
import com.cubicfox.model.Company;
import com.cubicfox.model.User;
import com.cubicfox.util.ColumnsConverterUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl extends AbstractGenericPostgresExecutor implements CompanyDao {

    @Override
    public Company saveCompany(User user) {
        String columns = ColumnsConverterUtil
                .convertColumnsToString(",", null,
                        COMPANY_BS, COMPANY_CATCH_PHRASE, COMPANY_NAME);
        String values = ColumnsConverterUtil
                .convertColumnsToString(",", ":",
                        COMPANY_BS, COMPANY_CATCH_PHRASE, COMPANY_NAME);

        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(COMPANY_BS, user.getCompany().getBs());
        source.addValue(COMPANY_CATCH_PHRASE, user.getCompany().getCatchPhrase());
        source.addValue(COMPANY_NAME, user.getCompany().getName());

        long id = executeInsert(String.format(INSERT_QUERY, TABLE_NAME, columns, values), source);
        Company company = user.getCompany();
        company.setId(id);
        return company;
    }
}
