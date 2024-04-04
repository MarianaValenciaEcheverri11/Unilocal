package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( registroClienteDTO.contrasena());

        Cliente cliente = Cliente.builder()
                .nombre(registroClienteDTO.nombre())
                .email(registroClienteDTO.email())
                .contrasena(passwordEncriptada)
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

        if(favoritos == null){
            favoritos = new ArrayList<>();
        }

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
    public ArrayList<String> listarFavoritos(String codigoCliente) throws Exception {
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow(() -> new Exception("El cliente no existe"));

        return cliente.getCodigosFavoritos();
    }

    @Override
    public ArrayList<ItemClienteDTO> listarClientes(int pagina) throws Exception {

        List<Cliente> clientes = clienteRepo.findAllByEstado(EstadoCuenta.ACTIVA);
        ArrayList<ItemClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {

            if(cliente.getEstado() == EstadoCuenta.ACTIVA){
            clientesDTO.add(new ItemClienteDTO(
                    cliente.getCodigo(),
                    cliente.getNickName(),
                    cliente.getNombre(),
                    cliente.getFoto(),
                    cliente.getCiudad()));
            }
        }
        return clientesDTO;
    }

    @Override
    public DetalleClienteDTO obtenerCliente(String codigo) throws Exception {

        Optional<Cliente> clienteRegistrado = clienteRepo.findByCodigo(codigo);

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
                                    cliente.getCiudad());
    }

    @Override
    public ArrayList<CategoriaEstablecimiento> obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception {
        Cliente cliente = clienteRepo.findById(codigoCliente).orElseThrow(() -> new Exception("El cliente no existe"));
        return cliente.getHistoricoCategoriasBuscadas();
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
    public TokenDTO iniciarSesionCliente(InicioSesionDTO inicioSesionDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(inicioSesionDTO.email());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        if( !passwordEncoder.matches(inicioSesionDTO.contrasena(), cliente.getContrasena()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        return new TokenDTO(JWTUtils.generarToken(cliente.getEmail(), map));
    }

    @Override
    public TokenDTO iniciarSesionModerador(InicioSesionDTO inicioSesionDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(inicioSesionDTO.email());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();
        if( !passwordEncoder.matches(inicioSesionDTO.contrasena(), cliente.getContrasena()) ) {
            throw new Exception("La contraseña es incorrecta");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        return new TokenDTO(JWTUtils.generarToken(cliente.getEmail(), map));
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
                .ciudad(actualizacionCuentaDTO.ciudadResidencia())
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