package cl.duocuc.mascotas.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duocuc.mascotas.infrastructure.entity.MascotaEntity;

public interface MascotaRepository extends JpaRepository<MascotaEntity, Long> {}
