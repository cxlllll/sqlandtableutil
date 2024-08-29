package net.sushine.demo;


public enum FmTableRelation {
    FM_TABALE_MST("fmeqlevelmst", true, "", ""),
    FM_TABALE_LIN_0("fmcompanymst", false, "fmeqlevelmst", "company_no,company_no"),
    FM_TABALE_LIN_1("fmdevpropertylin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_2("fmdevconfigimglin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_3("fmdevpartslin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_4("fmdevparamlin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_5("fmdevlinkimgmst", false, "fmeqlevelmst", "eqlevel_no,eqlevel_no"),
    FM_TABALE_LIN_6("fmcaldatamst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_7("fmdevpointslin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_8("fmdevcollectionlin", false, "fmdevpointslin", "point_no,point_no"),
    FM_TABALE_LIN_9("fmvectordefinitionmst", false, "fmdevpointslin", "point_no,point_no"),
    FM_TABALE_LIN_10("fmtdmconditionmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_11("fmconditionversion", false, "fmtdmconditionmst", "condition_no,condition_no"),
    FM_TABALE_LIN_12("fmdevtroublelin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_13("fmdevtroublelevelmst", false, "fmdevtroublelin", "lin_no,lin_no"),
    FM_TABALE_LIN_14("fmtroubleconditionlin", false, "fmdevtroublelin", "trouble_no,lin_no"),
    FM_TABALE_LIN_15("fmparamwarnmodelmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_16("fmmodelparamlin", false, "fmparamwarnmodelmst", "warn_model_id,model_no"),
    FM_TABALE_LIN_17("fmmodelsamplelin", false, "fmparamwarnmodelmst", "model_no,model_no"),
    FM_TABALE_LIN_18("fmdevtargetlin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_19("fmdevtargetlevelmst", false, "fmdevtargetlin", "target_no,lin_no"),
    FM_TABALE_LIN_20("fmtdmshaftmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_21("fmtdmbearingmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_22("fmtdmgearmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_23("fmtdmgeartypelin", false, "fmtdmgearmst", "gear_no,gear_no"),
    FM_TABALE_LIN_24("fmvectoralarmlin", false, "fmdevpointslin", "point_no,point_no"),
    FM_TABALE_LIN_25("fmtargetlevelmst", false, "fmeqlevelmst", "level_no,eqlevel_no"),
    FM_TABALE_LIN_26("fmregularreportconfigmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_27("tdmdevvtpropertylin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_28("tdmvttypconfmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    FM_TABALE_LIN_29("fmrtparammst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_1("tdmdevalarmwarnmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_2("tdmfaultsupperssionmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_3("fmwarnrecordlin", false, "fmeqlevelmst", "eqlevel_no,eqlevel_no"),
    ALARM_TABALE_LIN_4("fmparamsupperssionmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_5("fmdevtargetwarnmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_6("fmmodelalarmlin", false, "fmparamwarnmodelmst", "model_no,model_no"),
    ALARM_TABALE_LIN_7("fmtargetwarnmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_8("fmpromesseqlin", false, "fmeqlevelmst", "eqlevel_no,eqlevel_no"),
    ALARM_TABALE_LIN_9("fmpromesssetmst", false, "fmpromesseqlin", "pro_no,pro_no"),
    ALARM_TABALE_LIN_10("fmpromessusrlin", false, "fmpromesssetmst", "pro_no,pro_no"),
    ALARM_TABALE_LIN_11("fmpromesswarntyplin", false, "fmpromesssetmst", "pro_no,pro_no"),
    ALARM_TABALE_LIN_12("fmpromesswarnwaylin", false, "fmpromesssetmst", "pro_no,pro_no"),
    ALARM_TABALE_LIN_13("tdmmesscentermst", false, "fmeqlevelmst", "prkey,eqlevel_no"),
    ALARM_TABALE_LIN_14("fmdevpunctualreporttmpmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_15("fmdevpunctualreportmst", false, "tdmdevalarmwarnmst", "alarm_no,alarm_no"),
    ALARM_TABALE_LIN_16("fmscoremst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_17("fmscorelin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    ALARM_TABALE_LIN_18("fmrunrepmst", false, "fmeqlevelmst", "factory_no,eqlevel_no"),
    DR_TABALE_LIN_1("fmdevregularreportmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    DR_TABALE_LIN_2("fmreportdevrunstatlin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    DR_TABALE_LIN_3("fmreportfaultanalyselin", false, "fmdevregularreportmst", "report_no,report_no"),
    DR_TABALE_LIN_4("fmreportfaultmodellin", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    DR_TABALE_LIN_5("tdmvtreportmst", false, "fmeqlevelmst", "dev_no,eqlevel_no"),
    DR_TABALE_LIN_6("tdmvtreportbearlin", false, "tdmvtreportmst", "report_no,report_no"),
    DR_TABALE_LIN_7("tdmvtreportconclusionlin", false, "tdmvtreportmst", "report_no,report_no"),
    DR_TABALE_LIN_8("tdmvtreportimglin", false, "tdmvtreportmst", "report_no,report_no"),
    DR_TABALE_LIN_9("tdmvtreportprolin", false, "tdmvtreportmst", "report_no,report_no"),
    TDM_TABALE_LIN_1("tdmintegralmst", false, "fmdevcollectionlin", "col_no,col_no"),
    CN_TABALE_LIN_1("fmwkcondidatabackcalcmst", false, "fmeqlevelmst", "eqlevel_no,eqlevel_no"),
    CN_TABALE_LIN_2("fmconditiondatemst", false, "fmwkcondidatabackcalcmst", "calc_no,wkcondicalc_no"),
    CN_TABALE_LIN_3("fmconditiontagdatemst", false, "fmwkcondidatabackcalcmst", "calc_no,wkcondicalc_no"),
    CN_TABALE_LIN_4("fmtroublemanagemetmst", false, "fmdevtroublelin", "trouble_no,lin_no"),
    CN_TABALE_LIN_5("fmtroublemanagemetlin", false, "fmtroublemanagemetlin", "pk_value,lin_no");

    private String tableNam;
    private Boolean isMst;
    private String relationTable;
    private String relationColumn;

    private FmTableRelation() {
    }

    private FmTableRelation(String tableNam, Boolean isMst, String relationTable, String relationColumn) {
        this.tableNam = tableNam;
        this.isMst = isMst;
        this.relationTable = relationTable;
        this.relationColumn = relationColumn;
    }

    public String getTableNam() {
        return this.tableNam;
    }

    public void setTableNam(String tableNam) {
        this.tableNam = tableNam;
    }

    public Boolean getMst() {
        return this.isMst;
    }

    public void setMst(Boolean mst) {
        this.isMst = mst;
    }

    public String getRelationTable() {
        return this.relationTable;
    }

    public void setRelationTable(String relationTable) {
        this.relationTable = relationTable;
    }

    public String getRelationColumn() {
        return this.relationColumn;
    }

    public void setRelationColumn(String relationColumn) {
        this.relationColumn = relationColumn;
    }
}
