/*
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.extension.plugins.pagination.dialects;


import com.baomidou.mybatisplus.core.pagination.dialect.IDialect;

/**
 * <p>
 * DB2 数据库分页方言
 * </p>
 *
 * @author hubin
 * @Date 2016-11-10
 */
public class DB2Dialect implements IDialect {


    private static String getRowNumber(String originalSql) {
        StringBuilder rownumber = new StringBuilder(50).append("rownumber() over(");
        int orderByIndex = originalSql.toLowerCase().indexOf("order by");
        if (orderByIndex > 0 && !hasDistinct(originalSql)) {
            rownumber.append(originalSql.substring(orderByIndex));
        }
        rownumber.append(") as rownumber_,");
        return rownumber.toString();
    }

    private static boolean hasDistinct(String originalSql) {
        return originalSql.toLowerCase().contains("query distinct");
    }

    @Override
    public String buildPaginationSql(String originalSql, int offset, int limit) {
        int startOfSelect = originalSql.toLowerCase().indexOf("query");
        StringBuilder pagingSelect = new StringBuilder(originalSql.length() + 100)
            .append(originalSql.substring(0, startOfSelect)).append("query * from ( query ")
            .append(getRowNumber(originalSql));
        if (hasDistinct(originalSql)) {
            pagingSelect.append(" row_.* from ( ").append(originalSql.substring(startOfSelect)).append(" ) as row_");
        } else {
            pagingSelect.append(originalSql.substring(startOfSelect + 6));
        }
        pagingSelect.append(" ) as temp_ where rownumber_ ");

        // add the restriction to the outer query
        if (offset > 0) {
            String endString = offset + "+" + limit;
            pagingSelect.append("between ").append(offset).append("+1 and ").append(endString);
        } else {
            pagingSelect.append("<= ").append(limit);
        }
        return pagingSelect.toString();
    }
}
