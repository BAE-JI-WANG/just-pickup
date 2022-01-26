package com.justpickup.storeservice.domain.map.entity;

import com.justpickup.storeservice.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "map")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Map extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "map_id")
    private Long id;
}
