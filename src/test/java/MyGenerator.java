//package com.codebaobao.test;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class MyGenerator {
//
//    public static void main(final String[] args) {
//        final AutoGenerator mpg = new AutoGenerator(); //整合配置 全局配置+数据源配置+策略配置+包名策略配置
//        // 选择 freemarker 引擎，默认 Velocity 需要在配置文件引入依赖
//
//        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
//        // 1全局配置
//        final GlobalConfig gc = new GlobalConfig();
//        gc.setAuthor("codebaobao");
//        gc.setOutputDir("G:\\123\\simple-frontback-template\\src\\main\\java\\");
//        gc.setFileOverride(true);// 是否覆盖同名文件，默认是false
//        gc.setIdType(IdType.AUTO);// 主键策略
//        gc.setActiveRecord(true);// 不需要ActiveRecord特性的请改为false
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap 生成基本的resultmap
//        gc.setBaseColumnList(false);// XML columList 生成基本的sql片段
//        /* 自定义文件命名，注意 %s 会自动填充表实体属性！ */
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setControllerName("%sController");
//        mpg.setGlobalConfig(gc);
//
//        // 2数据源配置
//        final DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setDbType(DbType.MYSQL);
////        dsc.setTypeConvert(new MySqlTypeConvert() {
////            // 自定义数据库表字段类型转换【可选】
////            @Override
////            public DbColumnType processTypeConvert(String fieldType) {
////                System.out.println("转换类型：" + fieldType);
////                // 注意！！processTypeConvert 存在默认类型转换，如果不是你要的效果请自定义返回、非如下直接返回。
////                return super.processTypeConvert(fieldType);
////            }
////        });
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("12345678");
//        dsc.setUrl("jdbc:mysql://localhost:3306/psychologicalAppointment?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
//        mpg.setDataSource(dsc);
//        // 3策略配置globalConfiguration中
//        final StrategyConfig strategy = new StrategyConfig();
//        strategy.setEntityLombokModel(true);//实体类以lombok注解氏生产
//        strategy.setRestControllerStyle(true);//controller以restFule风格
//        // strategy.setCapitalMode(true);// 全局大写命名 ORACLE 注意
////        strategy.setTablePrefix(new String[]{"test_"});// 此处可以修改为您的表前缀
//        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略 此处可以更换为underline_to_camel 下滑线转驼峰
//        strategy.setInclude(new String[]{"doctor_week"}); // 需要生成的表
//        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
//        // 自定义实体父类
//        //strategy.setSuperEntityClass("com.middleware.platform.docserver.model.BaseDomain");
//        // 自定义实体，公共字段
//        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
//        // 自定义 mapper 父类
//        //strategy.setSuperMapperClass("com.middleware.platform.docserver.mapper.BaseCusMapper");
//        // 自定义 service 父类
//        //strategy.setSuperServiceClass("com.middleware.platform.docserver.service.BaseCusService");
//        // 自定义 service 实现类父类
//        //strategy.setSuperServiceImplClass("com.middleware.platform.docserver.service.impl.BaseCusServiceImpl");
//        // 自定义 controller 父类
//        //strategy.setSuperControllerClass("com.middleware.platform.docserver.controller.BaseController");
//        // 【实体】是否生成字段常量（默认 false）
//        // public static final String ID = "test_id";
//        // strategy.setEntityColumnConstant(true);
//        // 【实体】是否为构建者模型（默认 false）
//        // public User setName(String name) {this.name = name; return this;}
//        // strategy.setEntityBuilderModel(true);
//        mpg.setStrategy(strategy);
//
//        // 4包配置 修改包生成的名称
//        //pkConfig.setParent("com.imooc")
//        //                          .setMapper("dao")//dao
//        //                          .setService("service")//servcie
//        //                          .setController("controller")//controller
//        //                          .setEntity("entity")
//        //                           .setXml("resource");//mapper.xml
//        final PackageConfig pc = new PackageConfig();
//        pc.setParent("com.codebaobao")
//                .setController("controller")
//                .setMapper("mapper")
//                .setService("service")
//                .setServiceImpl("service.impl")
//                .setEntity("model")
//                .setXml("resource");
//        // pc.setModuleName("test");
//        mpg.setPackageInfo(pc);
//
//        // 注入自定义配置，可以在 VM 中使用 cfg.abc 【可无】
//        final InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                final Map<String, Object> map = new HashMap<String, Object>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() +
//                        "-mp");
//                this.setMap(map);
//            }
//        };
//
//        // 自定义 xxList.jsp 生成
//        final List<FileOutConfig> focList = new ArrayList<>();
//
//         /*focList.add(new FileOutConfig("/template/list.jsp.vm") {
//             @Override
//             public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                 return "D://workspace/study/springboot_mybatisplus_lombok/src/main/webapp/" + tableInfo.getEntityName() + ".jsp";
//             }
//         });
//         cfg.setFileOutConfigList(focList);
//         mpg.setCfg(cfg);*/
//
//
//        // 调整 xml 生成目录演示
//        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
//            @Override
//            public String outputFile(final TableInfo tableInfo) {
//                return "G:\\123\\simple-frontback-template\\src\\main\\resources\\mapper\\" + tableInfo.getEntityName() + "Mapper" + ".xml";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        // 关闭默认 xml 生成，调整生成 至 根目录
//        final TemplateConfig tc = new TemplateConfig();
//        tc.setXml(null);
//        mpg.setTemplate(tc);
//
//        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
//        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
//        // TemplateConfig tc = new TemplateConfig();
//        // tc.setController("...");
//        // tc.setEntity("...");
//        // tc.setMapper("...");
//        // tc.setXml("...");
//        // tc.setService("...");
//        // tc.setServiceImpl("...");
//        // 如上任何一个模块如果设置 空 OR Null 将不生成该模块。
//        // mpg.setTemplate(tc);
//
//        // 执行生成
//        mpg.execute();
//
//        // 打印注入设置【可无】
//        // System.err.println(mpg.getCfg().getMap().get("abc"));
//    }
//}
