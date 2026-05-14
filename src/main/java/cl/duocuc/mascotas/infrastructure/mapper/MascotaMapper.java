package cl.duocuc.mascotas.infrastructure.mapper;

import cl.duocuc.mascotas.domain.Mascota;
import cl.duocuc.mascotas.infrastructure.entity.MascotaEntity;

public final class MascotaMapper {

    private MascotaMapper() {}

    public static Mascota toDomain(MascotaEntity e) {
        return new Mascota(e.getId(), e.getNombre(), e.getEspecie());
    }

    public static MascotaEntity toEntity(Mascota m) {
        return new MascotaEntity(m.getId(), m.getNombre(), m.getEspecie());
    }
}
