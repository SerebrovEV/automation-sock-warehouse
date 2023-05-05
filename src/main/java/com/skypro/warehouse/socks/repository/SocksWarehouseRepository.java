package com.skypro.warehouse.socks.repository;

import com.skypro.warehouse.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksWarehouseRepository extends JpaRepository<SocksEntity, Integer> {

    Optional<SocksEntity> findByColorAndAndCottonPart(String color, Integer cottonPart);

    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part < :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndLessCotton(@Param("color") String color,
                                            @Param("cottonPart") Integer cottonPart);
    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part = :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndEqualsCotton(String color, Integer cottonPart);

    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part > :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndMoreCotton(String color, Integer cottonPart);
}
