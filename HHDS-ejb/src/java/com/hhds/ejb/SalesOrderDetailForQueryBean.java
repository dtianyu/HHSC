/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hhds.ejb;

import com.hhds.comm.SuperBean;
import com.hhds.entity.ItemTransaction;
import com.hhds.entity.ItemTransactionDetail;
import com.hhds.entity.Itembom;
import com.hhds.entity.SalesOrderDetailForQuery;
import com.hhds.entity.TransactionType;
import com.hhds.entity.VendorItem;
import com.lightshell.comm.BaseLib;
import com.lightshell.comm.SuperEJB;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class SalesOrderDetailForQueryBean extends SuperBean<SalesOrderDetailForQuery> {

    @EJB
    private ItemTransactionBean itemTransactionBean;
    @EJB
    private ItemTransactionDetailBean itemTransactionDetailBean;
    @EJB
    private TransactionTypeBean transactionTypeBean;
    @EJB
    private VendorItemBean vendorItemBean;
    @EJB
    private ItembomBean itembomBean;

    public SalesOrderDetailForQueryBean() {
        super(SalesOrderDetailForQuery.class);
    }

    public List<SalesOrderDetailForQuery> findByNotDelivery(Map<String, Object> filters, Map<String, String> orderBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT e FROM SalesOrderDetailForQuery e WHERE ((e.qty - e.shipqty) > 0)  ");
        sb.append(" AND (e.status<>'MC') ");
        if (filters != null) {
            this.setQueryFilter(sb, filters);
        }
        if ((orderBy != null) && (orderBy.size() > 0)) {
            sb.append(" ORDER BY ");
            for (Map.Entry<String, String> o : orderBy.entrySet()) {
                sb.append(" e.").append(o.getKey()).append(" ").append(o.getValue()).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        //生成SQL
        Query query = getEntityManager().createQuery(sb.toString());
        //参数赋值
        if (filters != null) {
            this.setQueryParam(query, filters);
        }
        return query.getResultList();
    }

    @Override
    public SalesOrderDetailForQuery unverify(SalesOrderDetailForQuery entity) {
        TransactionType transactionType = transactionTypeBean.findByTrtype("SDB");
        if (transactionType == null) {
            throw new RuntimeException("SDB交易类别没有定义");
        }
        List<ItemTransactionDetail> addedDetail = new ArrayList<>();
        HashMap<SuperEJB, List<?>> detailAdded = new HashMap<>();
        detailAdded.put(itemTransactionDetailBean, addedDetail);
        VendorItem vi;

        ItemTransaction it = new ItemTransaction();
        it.setFormdate(BaseLib.getDate());
        it.setTransactionType(transactionType);
        it.setObjtype("salesorder");
        it.setObjno(entity.getSalesOrder().getFormid());
        it.setWarehouse(entity.getWarehouse());
        it.setReason("退货");
        it.setStatus("N");

        int seq = 0;
        if ("V".equals(entity.getItemMaster().getMaketype())) {
            //虚拟件按BOM展开计算库存
            List<Itembom> itembomList = itembomBean.findByPId(entity.getItemMaster().getId());
            for (Itembom bom : itembomList) {
                vi = vendorItemBean.findFirstByItemno(bom.getItemno());
                seq++;
                ItemTransactionDetail itd = new ItemTransactionDetail();
                itd.setSeq(seq);
                itd.setTrtype(transactionType.getTrtype());
                itd.setItemmaster(bom.getItemMaster());
                itd.setItemno(bom.getItemno());
                if (vi != null) {
                    itd.setColorno(entity.getColorno() == null ? vi.getVendoritemcolor() : entity.getColorno());
                    itd.setBrand(entity.getBrand());
                    itd.setBatch(entity.getBatch() == null ? vi.getVendordesignno() : entity.getBatch());
                } else {
                    itd.setColorno(entity.getColorno());
                    itd.setBrand(entity.getBrand());
                    itd.setBatch(entity.getBatch());
                }
                itd.setSn(entity.getSn());
                itd.setQty(entity.getQty().multiply(bom.getQty()));//乘以BOM用量
                itd.setUnit(entity.getUnit());
                itd.setAmts(entity.getAmts());
                itd.setWarehouse(entity.getWarehouse());
                itd.setSrcapi("salesorder");
                itd.setSrcformid(entity.getSalesOrder().getFormid());
                itd.setSrcseq(entity.getSeq());
                addedDetail.add(itd);
            }
        } else {
            vi = vendorItemBean.findFirstByItemno(entity.getItemno());
            seq++;
            ItemTransactionDetail itd = new ItemTransactionDetail();
            itd.setSeq(seq);
            itd.setTrtype(transactionType.getTrtype());
            itd.setItemmaster(entity.getItemMaster());
            itd.setItemno(entity.getItemno());
            if (vi != null) {
                itd.setColorno(entity.getColorno() == null ? vi.getVendoritemcolor() : entity.getColorno());
                itd.setBrand(entity.getBrand());
                itd.setBatch(entity.getBatch() == null ? vi.getVendordesignno() : entity.getBatch());
            } else {
                itd.setColorno(entity.getColorno());
                itd.setBrand(entity.getBrand());
                itd.setBatch(entity.getBatch());
            }
            itd.setSn(entity.getSn());
            itd.setQty(entity.getQty());
            itd.setUnit(entity.getUnit());
            itd.setAmts(entity.getAmts());
            itd.setWarehouse(entity.getWarehouse());
            itd.setSrcapi("salesorder");
            itd.setSrcformid(entity.getSalesOrder().getFormid());
            itd.setSrcseq(entity.getSeq());
            addedDetail.add(itd);
        }

        String formid = itemTransactionBean.getFormId(it.getFormdate());
        it.setFormid(formid);
        addedDetail.forEach((d) -> {
            d.setPid(formid);
        });
        itemTransactionBean.persist(it, detailAdded, null, null);
        it.setStatus("V");
        itemTransactionBean.verify(it);

        entity.setBackqty(entity.getQty());
        entity.setRelapi("itemtransaction");
        entity.setRelformid(formid);
        entity.setRelseq(1);
        SalesOrderDetailForQuery sod = getEntityManager().merge(entity);
        return sod;
    }

}
