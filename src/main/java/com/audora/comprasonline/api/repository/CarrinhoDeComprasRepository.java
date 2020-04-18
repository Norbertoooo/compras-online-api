package com.audora.comprasonline.api.repository;

import com.audora.comprasonline.api.model.CarrinhoDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoDeComprasRepository extends JpaRepository<CarrinhoDeCompras, Long> {
    CarrinhoDeCompras findById(long id);
}
