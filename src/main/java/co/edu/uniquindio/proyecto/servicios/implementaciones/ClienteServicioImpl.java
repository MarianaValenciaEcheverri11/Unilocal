package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;


    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if(existeUsuario(registroClienteDTO.nickname())){
            throw new Exception("El cliente ya está registado");
        }

        if (existeEmail(registroClienteDTO.email())) {
            throw new Exception("El email ya está registrado");
        }

        Cliente cliente = Cliente.builder()
                .nombre(registroClienteDTO.nombre())
                .email(registroClienteDTO.email())
                .contrasenia(registroClienteDTO.contrasena())
                .nickName(registroClienteDTO.nickname())
                .estado(EstadoCuenta.ACTIVA)
                .rol(Rol.USUARIO)
                .build();

        Cliente clienteRegistrado = clienteRepo.save(cliente);

        return clienteRegistrado.getCodigo();

    }

    @Override
    public String marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = clienteRepo.findById(favoritoDTO.codigoCliente());
        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        ArrayList<String> favoritos = clienteRegistrado.get().getCodigosFavoritos();
        favoritos.add(favoritoDTO.codigoPublicacion());
        clienteRegistrado.get().setCodigosFavoritos(favoritos);
        clienteRepo.save(clienteRegistrado.get());
        return favoritoDTO.codigoPublicacion();
    }

    @Override
    public String eliminarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = clienteRepo.findById(favoritoDTO.codigoCliente());
        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        ArrayList<String> favoritos = clienteRegistrado.get().getCodigosFavoritos();
        favoritos.remove(favoritoDTO.codigoPublicacion());
        clienteRegistrado.get().setCodigosFavoritos(favoritos);
        clienteRepo.save(clienteRegistrado.get());
        return favoritoDTO.codigoPublicacion();
    }

    @Override
    public Optional<ArrayList<String>> listarFavoritos(String codigoCliente) throws Exception {
        return clienteRepo.findByCodigosFavoritos(codigoCliente);
    }

    @Override
    public ArrayList<ItemClienteDTO> listarClientes(int pagina) throws Exception {

        Optional<ArrayList<Cliente>> clientes = clienteRepo.findAllByEstado(EstadoCuenta.ACTIVA);

        ArrayList<ItemClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes.get()) {

            if(cliente.getEstado() == EstadoCuenta.ACTIVA){
            clientesDTO.add(new ItemClienteDTO(
                    cliente.getCodigo(),
                    cliente.getNickName(),
                    cliente.getNombre(),
                    cliente.getFoto(),
                    cliente.getCodigoCiudad()));
            }
        }
        return clientesDTO;
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String codigo) throws Exception {

        Optional<Cliente> clienteRegistrado = clienteRepo.findById(codigo);

        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        Cliente cliente = clienteRegistrado.get();

        if (cliente.getEstado() == EstadoCuenta.BLOQUEADA) {
            throw new Exception("El cliente no existe");
        }

        return new DetalleClienteDTO(cliente.getCodigo(),
                                    cliente.getNombre(),
                                    cliente.getFoto(),
                                    cliente.getNickName(),
                                    cliente.getEmail(),
                                    cliente.getCodigoCiudad());
    }

    @Override
    public Optional<ArrayList<CategoriaEstablecimiento>> obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception {
        return clienteRepo.finByHistoricoCategoriasBuscadas(codigoCliente);
    }

    @Override
    public String registrarCategoriaBuscadas(RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = clienteRepo.findById(registroCategoriaBuscadaDTO.codigoCliente());
        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        ArrayList<CategoriaEstablecimiento> historicoCategoriasBuscadas = clienteRegistrado.get().getHistoricoCategoriasBuscadas();
        historicoCategoriasBuscadas.add(registroCategoriaBuscadaDTO.categoriaEstablecimiento());
        clienteRegistrado.get().setHistoricoCategoriasBuscadas(historicoCategoriasBuscadas);
        clienteRepo.save(clienteRegistrado.get());
        return registroCategoriaBuscadaDTO.codigoCliente();
    }

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeUsuario(String nickname) {
        return  clienteRepo.findByNickName(nickname).isPresent();
    }

    @Override
    public String iniciarSesion(InicioSesionDTO inicioSesionDTO) throws Exception {
        if(!existeEmail(inicioSesionDTO.email())){
            throw new Exception("El usuario no existe");
        }
        Optional<Cliente> cliente = clienteRepo.findByEmailAndContrasena(inicioSesionDTO.email(), inicioSesionDTO.contrasena());
        return cliente.get().getCodigo();
    }

    @Override
    public String eliminarCuenta(String idUsuario) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idUsuario);
        if (optionalCliente.isEmpty()) {
            throw new Exception("El id del usuario no existe");
        }
        Cliente cliente = optionalCliente.get();
        cliente.setEstado(EstadoCuenta.BLOQUEADA);
        clienteRepo.save(cliente);
        return null;
    }

    @Override
    public String actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception {

        if (!clienteRepo.existsById(actualizacionCuentaDTO.codigo())) {
            throw new Exception("El id del usuario no existe");
        }

        Cliente cliente = Cliente.builder()
                .nombre(actualizacionCuentaDTO.nombre())
                .email(actualizacionCuentaDTO.foto())
                .foto(actualizacionCuentaDTO.foto())
                .codigoCiudad(actualizacionCuentaDTO.ciudadResidencia())
                .build();

        clienteRepo.save(cliente);
        return cliente.getCodigo();
    }

    @Override
    public String enviarLinkRecuperacionContrasena(String email) throws Exception {

        if (!existeEmail(email)) {
            throw new Exception("El email no existe");
        }
        //TODO: Implementar el envio de correo
        //correoServicio.enviarCorreo("cambio de contraseña - unilocal",
        // emial,
        // "para cambiar la contraseña acceda al siguiente link: http://localhost:8080/cambiarContrasena?email="+email);
        /*
        * los parametros que se envian deben ser los de recuperacioncontraseñaDTO*/

        return null;
    }

    @Override
    public String recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception {

        Optional<Cliente> clienteOptional = clienteRepo.findById(recuperacionContrasenaDTO.codigoCliente());

        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Cliente cliente = clienteOptional.get();
        cliente.setContrasena(recuperacionContrasenaDTO.contrasenaNueva());
        clienteRepo.save(cliente);
        return cliente.getCodigo();
    }
}