/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhsc.ejb;

import com.hhsc.comm.SuperBean;
import com.hhsc.entity.ItemProcess;
import com.hhsc.entity.ItemResource;
import com.hhsc.entity.ProcessResource;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemProcessBean extends SuperBean<ItemProcess> {

    @EJB
    private ProcessResourceBean processResourceBean;

    @EJB
    private ItemResourceBean itemResourceBean;

    private List<ItemResource> detailList;

    public ItemProcessBean() {
        super(ItemProcess.class);
    }

    public void initResource(ItemProcess entity) {
        List<ProcessResource> processResourceList = processResourceBean.findAll();
        if (processResourceList != null) {
            int seq = 0;
            for (ProcessResource e : processResourceList) {
                seq++;
                ItemResource r = new ItemResource();
                r.setPid(entity.getId());
                r.setItemno(entity.getItemno());
                r.setSeq(seq);
                r.setProcess(e.getProcess());
                r.setProcseq(e.getSeq());
                r.setKind(e.getKind());
                r.setContent(e.getContent());
                r.setValuetype(e.getValuetype());
                r.setBoolvalue(e.getBoolvalue());
                r.setNumvalue(e.getNumvalue());
                r.setStrvalue(e.getStrvalue());
                r.setRemark(e.getRemark());
                itemResourceBean.persist(r);
            }
        }
    }

    @Override
    public void persist(ItemProcess entity) {
        super.persist(entity);
        //initResource(entity);改为手动添加
    }

    @Override
    public void setDetail(Object value) {
        setDetailList(itemResourceBean.findByPId(value));
        if (getDetailList() == null) {
            setDetailList(new ArrayList<>());
        }
    }

    /**
     * @return the detailList
     */
    public List<ItemResource> getDetailList() {
        return detailList;
    }

    /**
     * @param detailList the detailList to set
     */
    public void setDetailList(List<ItemResource> detailList) {
        this.detailList = detailList;
    }

}
