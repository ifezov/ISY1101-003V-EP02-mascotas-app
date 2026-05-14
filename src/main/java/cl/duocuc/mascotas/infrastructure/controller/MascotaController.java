package cl.duocuc.mascotas.infrastructure.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import cl.duocuc.mascotas.application.MascotaService;
import cl.duocuc.mascotas.domain.Mascota;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/mascotas")
@Tag(name = "Mascotas", description = "Operaciones CRUD y CSV")
public class MascotaController {

    private final MascotaService service;

    public MascotaController(MascotaService service) {
        this.service = service;
    }

    @Operation(summary = "Listar mascotas")
    @GetMapping
    public List<Mascota> listar() {
        return service.listar();
    }

    @Operation(summary = "Crear mascota")
    @PostMapping
    public Mascota crear(@RequestBody Mascota m) {
        return service.crear(m);
    }

    @Operation(summary = "Actualizar mascota")
    @PutMapping("/{id}")
    public Mascota actualizar(@PathVariable Long id, @RequestBody Mascota m) {
        return service.actualizar(id, m);
    }

    @Operation(summary = "Eliminar mascota")
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @Operation(summary = "Exportar mascotas a CSV")
    @GetMapping("/export")
    public String exportar() {
        return service.listar().stream()
                .map(m -> m.getNombre() + "," + m.getEspecie())
                .collect(Collectors.joining("\n"));
    }

    @Operation(summary = "Importar mascotas desde CSV")
    @PostMapping("/import")
    public void importar(@RequestBody String csv) {
        java.util.Arrays.stream(csv.split("\n"))
                .map(line -> line.split(","))
                .filter(parts -> parts.length >= 2)
                .map(parts -> new Mascota(null, parts[0], parts[1]))
                .forEach(service::crear);
    }
}
