package com.hhsc.rpt;

import javax.naming.InitialContext;
import com.lightshell.comm.BaseEntityWithOperate;
import com.lightshell.comm.SuperEJB;
import javax.ejb.EJB;

public abstract class ConnectEJB<T extends SuperEJB, V extends BaseEntityWithOperate> {

    @EJB
    protected T superEJB;

    public ConnectEJB() {

    }

    public void setEJB(String JNDIName) throws Exception {
        if (superEJB == null) {
            InitialContext c = new InitialContext();
            Object objref = c.lookup(JNDIName);
            superEJB = (T) objref;
        }
    }

    public abstract V getData(int value) throws Exception;

}
