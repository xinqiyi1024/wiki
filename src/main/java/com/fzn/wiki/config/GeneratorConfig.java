package com.fzn.wiki.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Scanner;

/**
 * @author FZN
 * @date 2022/04/03 10:18
 * @description
 */
public class GeneratorConfig {
    // 数据库连接配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/wiki?useSSL=true&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
    private static final String JDBC_USER_NAME = "wiki";
    private static final String JDBC_PASSOWRD = "wiki123";

    private static final String OUTPUTDIR = "//src//main//java";

    //作者
    private static final String AUTHOR="fzn";

    //设置父包名
    private static final String PACKAGE_NAME = "com.fzn";
    //设置父包模块名
    private static final String MODULE_NAME = "wiki";
    //设置MVC下各个模块的包名
    private static final String ENTITY_NAME="domain";
    private static final String MAPPER_NAME="mapper";
    private static final String SERVICE_NAME="service";
    private static final String SERVICE_IMPL_NAME="service.impl";
    private static final String CONTROLLER_NAME="controller";
    //设置XML资源文件的目录
    private static final String MAPPER_XML_PATH="\\src\\main\\resources\\mapper";


    //指定数据库表的前缀。指定后，在生成文件时，模板会自动截取掉前缀字符，如表名为sys_user，指定前缀为sys_,生成实体是自动识别生成为user
    //多个前缀可以用逗号隔开，例如 sys_,bs_，根据项目需要配置
    private static final String TABLE_PREFIX = "tb_";

    //设置生成实体时的公共父类，例如 com.baomidou.global.BaseEntity  ，根据项目需要配置
    private static final String superEntityPackageString = null;
    //设置生成Controller时的公共父类，例如 com.baomidou.global.BaseController   ，根据项目需要配置
    private static final String superControllerPackageString = null;


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt!=null && ipt.length()>0) {
                return ipt;
            }
        }
        throw new Exception("请输入正确的" + tip + "！");
    }


    // 生成代码入口main方法
    public static void main(String[] args) throws Exception{
        // 1.数据库配置
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig
                // 驱动连接的URL、数据库连接用户名、数据库连接密码
                .Builder(JDBC_URL, JDBC_USER_NAME, JDBC_PASSOWRD)
                // 关键字处理 ,这里选取了mysql5.7文档中的关键字和保留字（含移除） 说明：官方文档中没有关于sqlserver，oracle数据库的配置
                //.keyWordsHandler(new MySqlKeyWordsHandler())
                // 数据库信息查询类,默认由 dbType 类型决定选择对应数据库内置实现：mysql:MySqlQuery(),sqlserver :SqlServerQuery(),Oracle:OracleQuery()
                .dbQuery(new MySqlQuery())
                // 类型转换,数据库=》JAVA类型  mysql: MySqlTypeConvert() sqlserver:SqlServerTypeConvert() oracle:OracleTypeConvert()
                .typeConvert(new MySqlTypeConvert())
                // 数据库 schema name
                .schema("wiki");

        // 1.1.快速生成器
        FastAutoGenerator fastAutoGenerator = FastAutoGenerator.create(dataSourceConfigBuilder);

        // 2.全局配置
        // 覆盖已生成文件
        // 不打开生成文件目录
        // 指定输出目录,注意使用反斜杠\
        // 设置注释的作者
        // 生成swagger注解
        // 设置注释的日期格式
        // 使用java8新的时间类型
        fastAutoGenerator.globalConfig(globalConfigBuilder -> globalConfigBuilder
                .fileOverride()
                .disableOpenDir()
                .outputDir(System.getProperty("user.dir") + OUTPUTDIR)
                .author(AUTHOR)
                .enableSwagger()
                .commentDate("yyyy-MM-dd")
                .dateType(DateType.TIME_PACK));

        // 3.包配置
        // 设置父包名
        // 设置父包模块名
        // 设置MVC下各个模块的包名
        // 设置XML资源文件的目录
        fastAutoGenerator.packageConfig(packageConfigBuilder -> packageConfigBuilder
                //父包模块名
                .moduleName(MODULE_NAME)
                //父包名
                .parent(PACKAGE_NAME)
                .entity(ENTITY_NAME)
                .mapper(MAPPER_NAME)
                .service(SERVICE_NAME)
                .serviceImpl(SERVICE_IMPL_NAME)
                .controller(CONTROLLER_NAME));

        // 4.模板配置
        // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        AbstractTemplateEngine templateEngine = new FreemarkerTemplateEngine();
        fastAutoGenerator.templateEngine(templateEngine);

        // 6.策略配置
        // 设置需要生成的表名
        // 设置过滤表前缀
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> {
            try {
                strategyConfigBuilder
                        .enableCapitalMode()
                        .enableSkipView()
                        .disableSqlFilter()
                        //表名匹配，按指定的表名生成对应的文件
                        .addInclude(scanner("数据库表名，多个表名用英文逗号分割").split(","))
                        //指定数据库表的前缀。指定后，在生成文件时，模板会自动截取掉前缀字符，如表名为sys_user，指定前缀为sys_,生成实体是自动识别生成为user
                        .addTablePrefix(TABLE_PREFIX);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 6.1.Entity策略配置
        // 生成实体时生成字段的注解，包括@TableId注解等
        // 数据库表和字段映射到实体的命名策略，为下划线转驼峰
        // 全局主键类型为None
        // 实体名称格式化为XXXEntity
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                .entityBuilder()
                //开启生成实体时生成字段注解
                .enableTableFieldAnnotation()
                //数据库表映射到实体的命名策略,NamingStrategy.underline_to_camel 指下划线转驼峰，NamingStrategy.no_change 无改变
                .naming(NamingStrategy.underline_to_camel)
                //开启实体Lombok注解模式
                .enableLombok()
                //开启 Boolean 类型字段移除 is 前缀
                .enableRemoveIsPrefix()
                .columnNaming(NamingStrategy.underline_to_camel)
                //设置Entity父类
                .superClass(superEntityPackageString)
                //.idType(IdType.NONE)
                .formatFileName("%sEntity"));


        // 6.2.Controller策略配置
        // 开启生成@RestController控制器
        fastAutoGenerator
                .strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                        .controllerBuilder()
                        //设置Controller父类
                        .superClass(superControllerPackageString)
                        //开启驼峰转连字符
                        .enableHyphenStyle()
                        //开启生成@RestController 控制器
                        .enableRestStyle());

        // 6.3.Service策略配置
        // 格式化service接口和实现类的文件名称，去掉默认的ServiceName前面的I
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                .serviceBuilder()
                //格式化 Service文件名称
                .formatServiceFileName("%sService")
                .formatServiceImplFileName("%sServiceImpl"));

        // 6.4.Mapper策略配置
        // 格式化 mapper文件名,格式化xml实现类文件名称
        fastAutoGenerator.strategyConfig(strategyConfigBuilder -> strategyConfigBuilder
                .mapperBuilder()
                //开启 @Mapper 注解
                .enableMapperAnnotation()
                .formatMapperFileName("%sMapper")
                .formatXmlFileName("%sMapper"));

        // 7.生成代码
        fastAutoGenerator.execute();
    }

}
