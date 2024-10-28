package tk_mybatis.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 该接口不能被扫描到
 * Created by Rainer on 2020/1/9.
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
