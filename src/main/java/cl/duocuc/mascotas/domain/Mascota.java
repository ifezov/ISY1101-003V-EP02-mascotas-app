package cl.duocuc.mascotas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {
    private Long id;
    private String nombre;
    private String especie;
}
