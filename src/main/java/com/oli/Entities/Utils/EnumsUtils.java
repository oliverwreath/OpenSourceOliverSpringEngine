package com.oli.Entities.Utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Author: Oliver
 */
@Slf4j
public class EnumsUtils {
    public enum AccountStatus {
        ACTIVE, CLOSED, CANCELLED, BLACK_LISTED,
        NONE
    }

    public enum UserType {
        MEMBER, ADMIN
    }

    public enum Gender {
        MALE, FEMALE
    }

    public enum OrderType {
        ORDER_CATALOG, ORDER_COURSE, ORDER_VIDEO
    }

    public enum Catalog {
        四大_BIG4, 咨询_CONSULTING, 大数据_BIG_DATA, FLAG, 金融_FINANCE, 快消_CPG
    }

    public enum DropDownListEntities {
        COUNTRY, CATALOG
    }

    public enum Authorities {
        ROLE_USER, ROLE_ADMIN
    }

    public enum Countries {
        USA, CANADA, AUSTRALIA, NEW_ZEALAND, CHINA, JAPAN, KOREA, UK, GERMANY, SWITZERLAND, FRANCE, SPAIN, ITALY, OTHER_EUROPEAMERICA, OTHER_ASIAPACIFIC, OTHER
    }
}
