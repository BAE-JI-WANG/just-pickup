package com.justpickup.storeservice.domain.item.repository;

import com.justpickup.storeservice.domain.category.entity.QCategory;
import com.justpickup.storeservice.domain.item.entity.Item;
import com.justpickup.storeservice.domain.item.entity.QItem;
import com.justpickup.storeservice.domain.store.entity.QStore;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Item> findItem(Long storeId,String word, Pageable pageable){

        //count 가져오기
        Long count =  queryFactory.select(QItem.item.count())
                .from(QItem.item)
                .join(QItem.item.category)
                .leftJoin(QItem.item.store)
                .on(QItem.item.store.id.eq(storeId))
                .where(
                        QItem.item.name.contains(word)
                            .or(QItem.item.category.name.contains(word))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchOne();

        //List 가져오기
        List<Item> itemList = queryFactory.selectFrom(QItem.item)
                .join(QItem.item.category).fetchJoin()
                .leftJoin(QItem.item.store)
                .on(QItem.item.store.id.eq(storeId))
                .where(
                        QItem.item.name.contains(word)
                            .or(QItem.item.category.name.contains(word))
                )
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return PageableExecutionUtils.getPage(itemList,pageable,() -> count);
    }
}