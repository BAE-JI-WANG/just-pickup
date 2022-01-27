package com.justpickup.storeservice.domain.itemoption.entity;

import com.justpickup.storeservice.domain.item.entity.Item;
import com.justpickup.storeservice.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "item_option")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemOption extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_option_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    private Long price;

    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // == 연관관계 편의 메소드 == //
    public void setItem(Item item) {
        this.item = item;
        item.getItemOptions().add(this);
    }
}
