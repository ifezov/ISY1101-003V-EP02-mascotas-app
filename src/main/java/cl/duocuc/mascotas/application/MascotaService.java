package cl.duocuc.mascotas.application;

import java.util.List;
import org.springframework.stereotype.Service;
import cl.duocuc.mascotas.domain.Mascota;
import cl.duocuc.mascotas.infrastructure.mapper.MascotaMapper;
import cl.duocuc.mascotas.infrastructure.repository.MascotaRepository;

@Service
public class MascotaService {

    private final MascotaRepository repo;

    public MascotaService(MascotaRepository repo) {
        this.repo = repo;
    }

    public List<Mascota> listar() {
        return repo.findAll().stream().map(MascotaMapper::toDomain).toList();
    }

    public Mascota crear(Mascota m) {
        return MascotaMapper.toDomain(repo.save(MascotaMapper.toEntity(m)));
    }

    public Mascota actualizar(Long id, Mascota m) {
        m.setId(id);
        return crear(m);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
