package com.skypro.warehouse.socks.repository;

import com.skypro.warehouse.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocksWarehouseRepository extends JpaRepository<SocksEntity, Integer> {
    Optional<SocksEntity> findByColorAndAndCottonPart(String color, Integer cottonPart);

    Integer getQuantityByColorAndLessCotton(String color, Integer cottonPart);
    Integer getQuantityByColorAndEqualsCotton(String color, Integer cottonPart);
    Integer getQuantityByColorAndMoreCotton(String color, Integer cottonPart);
}
