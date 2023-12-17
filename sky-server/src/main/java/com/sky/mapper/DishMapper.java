package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);


    //菜品的分页查询
    Page<DishVO> pageQuery(DishPageQueryDTO pageQueryDTO);


    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);


    //根据主键删除菜品

    void deleteById(List<Long> ids);

    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> getByDishId(Long id);


    @AutoFill(value = OperationType.UPDATE)
    void updateDish(Dish dish);
}
