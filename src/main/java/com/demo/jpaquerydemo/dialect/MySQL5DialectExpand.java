package com.demo.jpaquerydemo.dialect;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * @author wj
 * @date 2020/10/16 16:56
 */
public class MySQL5DialectExpand extends MySQL5Dialect {

    public MySQL5DialectExpand() {
        super();
        registerFunction("date_format", new SQLFunctionTemplate(StandardBasicTypes.STRING, "DATE_FORMAT(?1,?2)"));
    }
}
