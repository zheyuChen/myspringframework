package com.chenzheyu.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.chenzheyu.dao.IndexDaoImportSelector;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {IndexDaoImportSelector.class.getName()};
    }
}
