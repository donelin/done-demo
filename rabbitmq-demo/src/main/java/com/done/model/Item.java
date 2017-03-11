package com.done.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 商品表
 * @author Done Lin
 *
 */
@Data
@Table(name = "tb_item")
public class Item extends BaseModel {
	@Id
	@GeneratedValue(generator="UUID")
	private String id;

	/** 标题名 **/
	private String title;

	/** 卖点 **/
	private String sellPoint;

	/**价格  **/
	private Long price;
    
	/**数量  **/
	private Integer num;
    
	/** 二维码  **/
	private String barcode;
     
	/** 图片 **/
	private String image;

	/** 商品分类id  **/
	private Long cid;
 
	/** 商品状态，1-正常，2-下架，3-删除  **/
	private Integer status;
}
