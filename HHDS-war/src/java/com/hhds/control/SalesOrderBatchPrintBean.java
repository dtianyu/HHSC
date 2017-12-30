/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.control;

import com.hhds.entity.SalesOrder;
import com.lightshell.comm.BaseLib;
import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kevindong
 */
@ManagedBean(name = "salesOrderBatchPrintBean")
@SessionScoped
public class SalesOrderBatchPrintBean extends SalesOrderManagedBean {

    /**
     * Creates a new instance of SalesOrderBatchPrintBean
     */
    public SalesOrderBatchPrintBean() {
    }

    public void batchPrint() throws Exception {
        if (entityList == null || entityList.isEmpty()) {
            showErrorMsg("Error", "没有可打印数据");
            return;
        }
        String reportName, outputName;
        //设置报表名称
        reportName = reportPath + this.currentPrgGrant.getSysprg().getRptdesign();
        //设置导出文件名称
        fileName = BaseLib.formatDate("yyyyMMddhhmmss", getDate()) + ".pdf";
        outputName = reportOutputPath + fileName;
        OutputStream os = new FileOutputStream(outputName);
        PdfCopyFields pdfCopy = new PdfCopyFields(os);
        HashMap<String, Object> params = new HashMap<>();
        ByteArrayOutputStream baos;
        for (SalesOrder e : entityList) {
            //设置报表参数
            baos = new ByteArrayOutputStream();
            params.put("id", e.getId());
            params.put("formid", e.getFormid());
            params.put("JNDIName", this.currentPrgGrant.getSysprg().getRptjndi());
            try {
                if (this.currentPrgGrant != null && this.currentPrgGrant.getSysprg().getRptclazz() != null) {
                    reportClassLoader = Class.forName(this.currentPrgGrant.getSysprg().getRptclazz()).getClassLoader();
                }
                //初始配置
                this.reportInitAndConfig();
                //生成报表
                this.reportRunAndOutput(reportName, params, null, "pdf", baos);
            } catch (Exception ex) {
                throw ex;
            } finally {
                params.clear();
            }
            pdfCopy.addDocument(new PdfReader(baos.toByteArray()));
        }
        pdfCopy.close();
        this.reportViewPath = reportViewContext + fileName;
        this.preview();
    }

    public void batchVerify() {
        if (entityList == null || entityList.isEmpty()) {
            showErrorMsg("Error", "没有可审核数据");
            return;
        }
        try {
            for (SalesOrder e : entityList) {
                currentEntity = e;
                if (!doBeforeVerify()) {
                    showErrorMsg("Error", currentEntity.getFormid() + "审核前检查失败");
                    return;
                }
            }
            for (SalesOrder e : entityList) {
                currentEntity = e;
                e.setStatus("V");
                e.setCfmuser(getUserManagedBean().getCurrentUser().getUsername());
                e.setCfmdateToNow();
                superEJB.verify(e);
                doAfterVerify();
                showInfoMsg("Info", "批量发货操作成功");
            }
        } catch (Exception ex) {
            showErrorMsg("Error", ex.getMessage());
        }
    }

    @Override
    public void setEntityList(List<SalesOrder> entityList) {
        super.setEntityList(entityList);
        if (entityList != null && !entityList.isEmpty()) {
            currentEntity = entityList.get(0);
        }
    }

}
