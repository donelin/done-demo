package com.done.springboot.multiDataSource;

import com.alibaba.druid.support.http.WebStatFilter;


/**
 *
 * Created by Done Lin on 2018/4/21.
 * druid过滤器.
 * @author done
 *
 *
 */
//@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",/initParams={@WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
//        }
//)
public class DruidStatFilter extends WebStatFilter {

}
