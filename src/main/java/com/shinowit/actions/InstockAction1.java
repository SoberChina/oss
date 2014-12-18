package com.shinowit.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.shinowit.dao.BaseDAO;
import com.shinowit.entity.TMeInStockInfo;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Administrator on 2014-11-12.
 */
public class InstockAction1 extends ActionSupport {
    @Resource
    private BaseDAO<TMeInStockInfo> tsdao;
    private List<TMeInStockInfo> tslist;
    private int page;
    private int limit;
    private int rowcount;
    private String name;

    public String list() {

        if ((null != name) && (name.trim().length() > 0)) {
            try {
                byte[] bb = name.getBytes("ISO-8859-1");
                name = new String(bb, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
//            tslist= tsdao.queryForPage("from TMeInStockInfo i where i.handler like \'%"+name+"%\'",page,limit);
//            rowcount=tsdao.queryRecordCount("select count(*) from TMeInStockInfo i where i.handler like \'%"+name+"%\'",page,limit);
//            return SUCCESS;
        }

        rowcount = tsdao.listAll(TMeInStockInfo.class).size();
        if (limit != 0) {
            if ((rowcount % limit == 0) && (rowcount / limit < page)) {
                page = page - 1;

            }
        }
        tslist = tsdao.queryForPage("from TMeInStockInfo", page, limit);
        return SUCCESS;
    }

    public List<TMeInStockInfo> getTslist() {
        return tslist;
    }

    public void setTslist(List<TMeInStockInfo> tslist) {
        this.tslist = tslist;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
