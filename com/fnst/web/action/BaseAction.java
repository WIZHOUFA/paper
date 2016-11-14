package com.fnst.paper.web.action;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BaseAction<T> extends ActionSupport 
						implements ModelDriven<T>,Preparable,RequestAware,SessionAware,ApplicationAware  {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 8278211067972795102L;
	protected T model;
    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;
	
    @SuppressWarnings("unchecked")
	public BaseAction() {
    	 ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
         Type[] types = type.getActualTypeArguments();
         Class<T> clazz = (Class<T>) types[0];
         try {
             model = clazz.newInstance();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         }
    }
    
    /**
     * 显示第几页
     */
    protected Integer page = 1;
    
    /**
     * 每页显示条目数
     */
    protected Integer rows = 10;
    
    /**
     * 总条目数
     */
    protected long total = 0;
    
    /**
     * 总页数
     */
    protected long totalPage;
    
	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		// TODO Auto-generated method stub
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}


	public void setModel(T model) {
		this.model = model;
	}
	
}
