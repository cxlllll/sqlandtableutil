package net.sushine;
public enum TableEmun {
    TABLE_1("fmlindustrymst", "1", "fmlindustrymst_imp_exp_bak", "行业数据"),
    TABLE_2("fmprofessionaldefmst", "1", "fmprofessionaldefmst_imp_exp_bak", "专业数据"),
    TABLE_3("fmequmechanismmst", "1", "fmequmechanismmst_imp_exp_bak", "设备通用模型数据"),
    TABLE_4("fmequuniversalmst", "1", "fmequuniversalmst_imp_exp_bak", "设备通用模型定义"),
    TABLE_5("fmequpropertylin", "1", "fmequpropertylin_imp_exp_bak", "设备通用模型属性"),
    TABLE_6("fmequpartslin", "1", "fmequpartslin_imp_exp_bak", "设备通用模型部件"),
    TABLE_7("fmconfigimglin", "1", "fmconfigimglin_imp_exp_bak", "设备通用模型结构图"),
    TABLE_8("fmequparameterlin", "1", "fmequparameterlin_imp_exp_bak", "设备通用模型监测参数"),
    TABLE_9("fm_aiwarn_model", "1", "fm_aiwarn_model_imp_exp_bak", "设备通用模型参数预警模型"),
    TABLE_10("fm_param_list", "1", "fm_param_list_imp_exp_bak", "设备通用模型参数预警模型参数数据"),
    TABLE_11("fmequtroublelin", "1", "fmequtroublelin_imp_exp_bak", "设备通用模型数据模型"),
    TABLE_12("fmequtroublelevelmst", "1", "fmequtroublelevelmst_imp_exp_bak", "设备通用模型数据模型等级"),
    TABLE_13("fmequtargetlin", "1", "fmequtargetlin_imp_exp_bak", "设备通用模型指标"),
    TABLE_14("fmequtargetlevelmst", "1", "fmequtargetlevelmst_imp_exp_bak", "设备通用模型指标等级"),
    TABLE_15("fmequpointslin", "1", "fmequpointslin_imp_exp_bak", "设备通用模型测点"),
    TABLE_16("fmequcollectionlin", "1", "fmequcollectionlin_imp_exp_bak", "设备通用模型测点采集定义"),
    TABLE_17("fmdevvectordefinitionmst", "1", "fmdevvectordefinitionmst_imp_exp_bak", "设备通用模型测点矢量定义"),
    TABLE_18("fmproductionconfigmst", "1", "fmproductionconfigmst_imp_exp_bak", "生产结构定义数据"),
    TABLE_19("fmpropropertylin", "1", "fmpropropertylin_imp_exp_bak", "生产结构定义属性"),
    TABLE_20("fmpropartslin", "1", "fmpropartslin_imp_exp_bak", "生产结构定义部件"),
    TABLE_21("fmproconfigimglin", "1", "fmproconfigimglin_imp_exp_bak", "生产结构定义结构图"),
    TABLE_22("fmproparameterlin", "1", "fmproparameterlin_imp_exp_bak", "生产结构定义监测参数"),
    TABLE_23("fmprotroublelin", "1", "fmprotroublelin_imp_exp_bak", "生产结构定义数据模型"),
    TABLE_24("fmprotroublelevelmst", "1", "fmprotroublelevelmst_imp_exp_bak", "生产结构定义数据模型等级"),
    TABLE_25("fmprotargetlin", "1", "fmprotargetlin_imp_exp_bak", "生产结构定义指标"),
    TABLE_26("fmprotargetlevelmst", "1", "fmprotargetlevelmst_imp_exp_bak", "生产结构定义指标等级"),
    TABLE_27("fmpropointslin", "1", "fmpropointslin_imp_exp_bak", "生产结构定义测点"),
    TABLE_28("fmprocollectionlin", "1", "fmprocollectionlin_imp_exp_bak", "生产结构定义测点采集定义"),
    TABLE_29("fmprovectordefinitionmst", "1", "fmprovectordefinitionmst_imp_exp_bak", "生产结构定义测点矢量定义"),
    TABLE_30("fmtargetproductmst", "1", "fmtargetproductmst_imp_exp_bak", "生产结构定义指标分析类型"),
    TABLE_31("fmtargetmanagerlin", "1", "fmtargetmanagerlin_imp_exp_bak", "生产结构定义指标分析指标"),
    TABLE_32("fmdevevatypmst", "1", "fmdevevatypmst_imp_exp_bak", "设备评价类型"),
    TABLE_33("fmtyplevellin", "1", "fmtyplevellin_imp_exp_bak", "设备评价类型等级"),
    TABLE_34("fmequbigpartsmst", "1", "fmequbigpartsmst_imp_exp_bak", "部件大类"),
    TABLE_35("fmtargetinfomst", "1", "fmtargetinfomst_imp_exp_bak", "指标类型"),
    TABLE_36("fmcommpropertylin", "1", "fmcommpropertylin_imp_exp_bak", "设备通用属性"),
    TABLE_37("fmpartstypemst", "1", "fmpartstypemst_imp_exp_bak", "部件大类部件类型"),
    TABLE_38("fmpartspropertylin", "1", "fmpartspropertylin_imp_exp_bak", "部件大类部件属性"),
    TABLE_39("fmpartsimglin", "1", "fmpartsimglin_imp_exp_bak", "部件大类部件结构图"),
    TABLE_40("fmpartsparameterlin", "1", "fmpartsparameterlin_imp_exp_bak", "部件大类部件监测参数"),
    TABLE_41("fm_componentdef_lin", "1", "fm_componentdef_lin_imp_exp_bak", "部件大类零件"),
    TABLE_42("fmpartstroublelin", "1", "fmpartstroublelin_imp_exp_bak", "部件大类数据模型"),
    TABLE_43("fmpartstroublelevelmst", "1", "fmpartstroublelevelmst_imp_exp_bak", "部件大类数据模型等级"),
    TABLE_44("fmpartstargetlin", "1", "fmpartstargetlin_imp_exp_bak", "部件大类评价指标类型"),
    TABLE_45("fmpartstargetlevelmst", "1", "fmpartstargetlevelmst_imp_exp_bak", "部件大类评价指标类型等级"),
    TABLE_46("rltaskmst", "1", "rltaskmst_imp_exp_bak", "规则绘制图形数据"),
    TABLE_47("rldatasetmst", "1", "rldatasetmst_imp_exp_bak", "规则绘制计算数据"),
    TABLE_48("fmtdmcollectiongpmst", "1", "fmtdmcollectiongpmst_imp_exp_bak", "采集分组"),
    TABLE_49("fmvectorcollectionmst", "1", "fmvectorcollectionmst_imp_exp_bak", "矢量分组"),
    TABLE_50("sys_pgnummst", "3", "sys_pgnummst_imp_exp_bak", "号码规则"),
    TABLE_51("sys_pgprtmst", "3", "sys_pgprtmst_imp_exp_bak", "参照值名称"),
    TABLE_52("sys_pgprtlin", "3", "sys_pgprtlin_imp_exp_bak", "参照值字段"),
    TABLE_53("rlfuncmst", "2", "rlfuncmst_imp_exp_bak", "规则引擎函数"),
    TABLE_54("rlfunctypmst", "2", "rlfunctypmst_imp_exp_bak", "规则引擎函数类型"),
    TABLE_55("fmtargetmanagermst", "1", "fmtargetmanagermst_imp_exp_bak", "指标定义指标管理"),
    TABLE_56("fmprortparammst", "1", "fmprortparammst_imp_exp_bak", "生产结构运行报告参数配置"),
    TABLE_57("fmproconditionmst", "1", "fmproconditionmst_imp_exp_bak", "生产结构工况定义"),
    TABLE_58("fmproconditionversion", "1", "fmproconditionversion_imp_exp_bak", "生产结构工况版本定义"),
    TABLE_59("fmprotroubleconditionlin", "1", "fmprotroubleconditionlin_imp_exp_bak", "生产结构定义数据模型绑定工况"),
    TABLE_60("fmequshaftmst", "1", "fmequshaftmst_imp_exp_bak", "设备通用模型轴"),
    TABLE_61("fmequbearingmst", "1", "fmequbearingmst_imp_exp_bak", "设备通用模型轴承"),
    TABLE_62("fmequgearmst", "1", "fmequgearmst_imp_exp_bak", "设备通用模型齿轮"),
    TABLE_63("fmequgeartypelin", "1", "fmequgeartypelin_imp_exp_bak", "设备通用类型齿轮子表"),
    TABLE_64("fmequstrategymst", "1", "fmequstrategymst_imp_exp_bak", "设备通用类型采集策略"),
    TABLE_65("fmproshaftmst", "1", "fmproshaftmst_imp_exp_bak", "生产结构定义轴"),
    TABLE_66("fmprobearingmst", "1", "fmprobearingmst_imp_exp_bak", "生产结构定义轴承"),
    TABLE_67("fmprogearmst", "1", "fmprogearmst_imp_exp_bak", "生产结构定义齿轮"),
    TABLE_68("fmprogeartypelin", "1", "fmprogeartypelin_imp_exp_bak", "生产结构定义齿轮子表"),
    TABLE_69("fmprostrategymst", "1", "fmprostrategymst_imp_exp_bak", "生产结构定义采集策略"),
    TABLE_70("fm_pro_aiwarn_model", "1", "fm_pro_aiwarn_model_imp_exp_bak", "生产结构定义预警模型"),
    TABLE_71("fm_pro_param_list", "1", "fm_pro_param_list_imp_exp_bak", "生产结构定义预警模型参数数据");

    private String tableNam;
    private String type;
    private String bakNam;
    private String name;

    private TableEmun(String tableNam, String type, String bakNam, String name) {
        this.tableNam = tableNam;
        this.type = type;
        this.bakNam = bakNam;
        this.name = name;
    }

    public String getTableNam() {
        return this.tableNam;
    }

    public void setTableNam(String tableNam) {
        this.tableNam = tableNam;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBakNam() {
        return this.bakNam;
    }

    public void setBakNam(String bakNam) {
        this.bakNam = bakNam;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TableEmun fromCode(String code) {
        TableEmun[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TableEmun alertMsgType = var1[var3];
            if (String.valueOf(alertMsgType.getName()).equals(code.trim())) {
                return alertMsgType;
            }
        }

        return null;
    }

    public static String fromBakNam(String bakNam) {
        TableEmun[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            TableEmun alertMsgType = var1[var3];
            if (alertMsgType.getBakNam().equals(bakNam.trim())) {
                return alertMsgType.getTableNam();
            }
        }

        return null;
    }
}
