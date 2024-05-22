package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {

    private final AutenticacionServicio autenticacionServicio;
    private final ClienteServicio clienteServicio;
    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody InicioSesionDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO) throws Exception {
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente")
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<MensajeDTO<String>> actualizarToken(@Valid @RequestBody InicioSesionDTO loginDTO) throws Exception {
        clienteServicio.actualizarToken(loginDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Token actualizado correctamente")
        );
    }
    @PostMapping("/enviar-link-recuperacion-contrasena/{email}")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionContrasena(@PathVariable String email)throws Exception{
        clienteServicio.enviarLinkRecuperacionContrasena(email);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Correo enviado correctamente") );
    }

    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<MensajeDTO<String>> recuperarContrasena(@Valid @RequestBody RecuperacionContrasenaDTO recuperacionContrasenaDTO)throws Exception{
        clienteServicio.recuperarContrasena(recuperacionContrasenaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Contraseña actualizada correctamente") );
    }
}
