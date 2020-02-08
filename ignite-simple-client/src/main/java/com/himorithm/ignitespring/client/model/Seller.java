package com.himorithm.ignitespring.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seller implements Serializable {
    @QuerySqlField(index = true)
    private Integer id;

    @QuerySqlField
    private String firstName;

    @QuerySqlField
    private String lastName;
}
