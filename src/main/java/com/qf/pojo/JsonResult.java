package com.qf.pojo;

public class JsonResult {
	private int code;// 状态码
	private String msg;// 描述信息
	private Object data;// 返回数据

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static JsonResult setOK(Object data) {
		JsonResult jsonUtil=new JsonResult();
		jsonUtil.setCode(200);
		jsonUtil.setMsg("操作成功");
		jsonUtil.setData(data);
		return jsonUtil;
	}
	
	public static JsonResult setError(Object data) {
		JsonResult jsonUtil=new JsonResult();
		jsonUtil.setCode(400);
		jsonUtil.setMsg("操作失败");
		jsonUtil.setData(data);
		return jsonUtil;
	}
}
