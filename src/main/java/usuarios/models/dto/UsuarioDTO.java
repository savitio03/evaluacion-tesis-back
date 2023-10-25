package usuarios.models.dto;

import compartidos.RolUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String segundoNombre;
    private String apellido;
    private String segundoApellido;
    private String codigoCarnet;
    private RolUsuarioEnum rol;
    private String numeroCelular;
    private String correo;
    private String password;
    private LocalDateTime fechaCreacion;
}
