package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/clientes")
@SecurityRequirement(name = "bearerAuth")
public class ClienteControlador {

        ClienteServicio clienteServicio;
        @PostMapping("/registrar-cliente")
        public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO) throws Exception {
                clienteServicio.registrarCliente(registroClienteDTO);
                return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente")
                );
        }

        @PutMapping("/editar-perfil")
        public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody
                                                                    ActualizacionCuentaDTO actualizarClienteDTO)throws Exception{
                clienteServicio.actualizarCuenta(actualizarClienteDTO);
                return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente actualizado correctamente") );
        }

        @DeleteMapping("/eliminar/{codigo}")
        public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo)throws
                Exception{
                clienteServicio.eliminarCuenta(codigo);
                return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente eliminado correctamente")
                );
        }

        @GetMapping("/obtener/{codigo}")
        public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String
                                                                                    codigo) throws Exception{
                return ResponseEntity.ok().body( new MensajeDTO<>(false,
                        clienteServicio.obtenerCliente(codigo) ) );
        }

        @GetMapping("/listar-todos")
        public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>> listarClientes(int pagina) throws Exception {
                return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.listarClientes(pagina))
                );
        }
}
