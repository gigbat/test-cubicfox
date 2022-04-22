CREATE TABLE IF NOT EXISTS cubicfox_geo
(
    id                  BIGSERIAL PRIMARY KEY                         NOT NULL,
    geo_lat             NUMERIC(10,6),
    geo_lng             NUMERIC(10,6)
);

CREATE TABLE IF NOT EXISTS cubicfox_company
(
    id                      BIGSERIAL PRIMARY KEY                     NOT NULL,
    company_name            VARCHAR(64),
    company_catch_phrase    VARCHAR(64),
    company_bs              VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS cubicfox_address
(
    id                  BIGSERIAL PRIMARY KEY                         NOT NULL,
    address_street      VARCHAR(64),
    address_suite       VARCHAR(64),
    address_city        VARCHAR(64),
    address_zipcode     VARCHAR(16),
    address_geo_id      BIGINT                                        NOT NULL,
    CONSTRAINT fk_cubicfox_address_geo_id FOREIGN KEY (address_geo_id) REFERENCES cubicfox_geo (id)
);

CREATE TABLE IF NOT EXISTS cubicfox_user
(
    id                  BIGSERIAL PRIMARY KEY                         NOT NULL,
    user_name           VARCHAR(64)                                   NOT NULL,
    user_username       VARCHAR(64)    UNIQUE                         NOT NULL,
    user_email          VARCHAR(64)    UNIQUE                         NOT NULL,
    user_address_id     BIGINT                                        NOT NULL,
    user_phone          VARCHAR(32)                                   NOT NULL,
    user_website        VARCHAR(32)                                   NOT NULL,
    user_company_id     BIGINT                                        NOT NULL,


    CONSTRAINT fk_cubicfox_user_company_id FOREIGN KEY (user_company_id) REFERENCES cubicfox_company (id),
    CONSTRAINT fk_cubicfox_address_geo_id FOREIGN KEY (user_address_id) REFERENCES cubicfox_address (id)
);