package ru.yescoding.app.repository;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.VarArgsSQLFunction;
import org.hibernate.type.PostgresUUIDType;

public class CustomSQLFunctionsRegistrar implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applyBasicType(new PostgresUUIDType(){
           public String getName() {
               return "pg_uuid";
           }
        });
    }
}
