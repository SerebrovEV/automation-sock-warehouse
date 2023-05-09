package com.skypro.warehouse.socks.repository;

import com.skypro.warehouse.socks.entity.SocksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Репозиторий для {@link SocksEntity}
 */
@Repository
public interface SocksWarehouseRepository extends JpaRepository<SocksEntity, Integer> {
    /**
     * Метод для поиска в базе даных {@link Optional} <{@link SocksEntity}> по цвету и проценту хлопка
     * @param color - цвет;
     * @param cottonPart - процент хлопка в составе;
     * @return {@link Optional} <{@link SocksEntity}>
     */
    Optional<SocksEntity> findByColorAndAndCottonPart(String color, Integer cottonPart);

    /**
     * Метод для получения общего количества {@link SocksEntity} по цвету и проценту хлопка меньше
     * задонного числа в базе данных
     * @param color  цвет;
     * @param cottonPart  процент хлопка в составе;
     * @return {@link Integer} суммарное количество пар носков
     */
    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part < :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndLessCotton(@Param("color") String color,
                                            @Param("cottonPart") Integer cottonPart);

    /**
     * Метод для получения общего количества {@link SocksEntity} по цвету и проценту хлопка равного
     * задонному числу в базе данных
     * @param color цвет;
     * @param cottonPart процент хлопка в составе;
     * @return {@link Integer} суммарное количество пар носков
     */
    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part = :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndEqualsCotton(String color, Integer cottonPart);

    /**
     * Метод для получения общего количества {@link SocksEntity} по цвету и проценту хлопка больше
     * заданного числа в базе данных
     * @param color цвет;
     * @param cottonPart процент хлопка в составе;
     * @return {@link Integer} суммарное количество пар носков
     */
    @Query(value = "select sum(quantity) from socks as s where s.color like :color and s.cotton_part > :cottonPart",
            nativeQuery = true)
    Integer getQuantityByColorAndMoreCotton(String color, Integer cottonPart);
}
