package com.cubicfox.dao.api;

import com.cubicfox.model.Geo;
import com.cubicfox.model.User;

public interface GeoDao {
    String GEO_LAT = "geo_lat";
    String GEO_LNG = "geo_lng";
    String TABLE_NAME = "cubicfox_geo";
    Geo saveGeo(User user);
}
