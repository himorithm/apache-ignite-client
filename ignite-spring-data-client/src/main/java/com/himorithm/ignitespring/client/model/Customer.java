package com.himorithm.ignitespring.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
    static final long serialVersionUID = 42L;

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField(index = true)
    private String firstName;

    @QuerySqlField
    private String lastName;
}
