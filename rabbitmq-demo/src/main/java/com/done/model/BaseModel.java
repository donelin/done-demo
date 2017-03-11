package com.done.model;

import java.util.Date;
import lombok.Data;
/**
 * 通用的pojo
 * @author Done Lin
 *
 */
@Data
public class BaseModel {
	/** 创建时间 **/
	private Date created;
	/** 修改时间 **/
    private Date updated;
}
