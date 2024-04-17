package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/clientes")
public class ClienteControlador {

        private final ClienteServicio clienteServicio;


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

        @PostMapping("/marcar-lugar-favorito")
        public ResponseEntity<MensajeDTO<String>> marcarLugarFavorito(@Valid @RequestBody
                                                                             FavoritoDTO favoritoDTO) throws Exception {
                return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.marcarLugarFavorito(favoritoDTO))
                );
        }

        @DeleteMapping("/eliminar-lugar-favorito")
        public ResponseEntity<MensajeDTO<String>> eliminarLugarFavorito(@Valid @RequestBody
                                                                                FavoritoDTO favoritoDTO)throws  Exception{
                clienteServicio.eliminarLugarFavorito(favoritoDTO);
                return ResponseEntity.ok().body( new MensajeDTO<>(false, "Lugar eliminado correctamente")
                );
        }

        @GetMapping("/listar-lugares-favoritos/{codigo}")
        public ResponseEntity<MensajeDTO<List<String>>> listarFavoritos(@PathVariable String codigo) throws Exception {
                return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.listarFavoritos(codigo))
                );
        }

        @GetMapping("/listar-historico-categorias-buscadas/{codigo}")
        public ResponseEntity<MensajeDTO<List<CategoriaEstablecimiento>>> obtenerHistoricoCategoriasBuscadas(@PathVariable String codigo) throws Exception {
                return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.obtenerHistoricoCategoriasBuscadas(codigo))
                );
        }

        @PostMapping("/registrar-categoria-buscada")
        public ResponseEntity<MensajeDTO<String>> marcarLugarFavorito(@Valid @RequestBody
                                                                             RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO) throws Exception {
                return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.registrarCategoriaBuscadas(registroCategoriaBuscadaDTO))
                );
        }
}
