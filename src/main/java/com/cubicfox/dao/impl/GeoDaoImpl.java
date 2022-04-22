package com.cubicfox.dao.impl;

import com.cubicfox.dao.AbstractGenericPostgresExecutor;
import com.cubicfox.dao.api.GeoDao;
import com.cubicfox.model.Geo;
import com.cubicfox.model.User;
import com.cubicfox.util.ColumnsConverterUtil;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class GeoDaoImpl extends AbstractGenericPostgresExecutor implements GeoDao {
    @Override
    public Geo saveGeo(User user) {
        String columns = ColumnsConverterUtil
                .convertColumnsToString(",", null, GEO_LAT, GEO_LNG);
        String values = ColumnsConverterUtil
                .convertColumnsToString(",", ":", GEO_LAT, GEO_LNG);

        MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue(GEO_LAT, user.getAddress().getGeo().getLat());
        source.addValue(GEO_LNG, user.getAddress().getGeo().getLng());

        long id = executeInsert(String.format(INSERT_QUERY, TABLE_NAME, columns, values), source);
        Geo geo = user.getAddress().getGeo();
        geo.setId(id);
        return geo;
    }
}
