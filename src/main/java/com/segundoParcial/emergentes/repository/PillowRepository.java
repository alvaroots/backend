package com.segundoParcial.emergentes.repository;

import com.segundoParcial.emergentes.models.Pillow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PillowRepository extends JpaRepository <Pillow, Long>{
}
