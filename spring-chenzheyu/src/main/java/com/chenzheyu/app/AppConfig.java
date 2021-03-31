package com.chenzheyu.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.chenzheyu.imports.MyImportSelector;

@ComponentScan("com.chenzheyu")
@Import(MyImportSelector.class)
public class AppConfig {

}
