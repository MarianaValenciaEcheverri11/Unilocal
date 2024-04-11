package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.models.enums.EstadoCuenta;
import co.edu.uniquindio.proyecto.models.enums.Rol;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicio;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final EmailServicio emailServicio;
    private final ImagenesServicio imagenesServicio;
    private final JWTUtils jwtUtils;

    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if(existeUsuario(registroClienteDTO.nickname())){
            throw new Exception("El cliente ya está registado");
        }

        if (existeEmail(registroClienteDTO.email())) {
            throw new Exception("El email ya está registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroClienteDTO.contrasena());

        Cliente cliente = Cliente.builder()
                .nombre(registroClienteDTO.nombre())
                .email(registroClienteDTO.email())
                .contrasena(passwordEncriptada)
                .nickName(registroClienteDTO.nickname())
                .estado(EstadoCuenta.ACTIVA)
                .rol(Rol.USUARIO)
                .foto(registroClienteDTO.foto())
                .ciudad(registroClienteDTO.ciudadResidencia())
                .codigo(registroClienteDTO.cedula())
                .build();

        Cliente clienteRegistrado = clienteRepo.save(cliente);
        return clienteRegistrado.getCodigo();
    }

    @Override
    public String marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = clienteRepo.findByCodigo(favoritoDTO.codigoCliente());
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
        Optional<Cliente> clienteRegistrado = clienteRepo.findByCodigo(favoritoDTO.codigoCliente());
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
        Cliente cliente = clienteRepo.findByCodigo(codigoCliente).orElseThrow(() -> new Exception("El cliente no existe"));
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
            throw new Exception("La cuenta está bloqueada");
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
        Optional<Cliente> clienteRegistrado = clienteRepo.findByCodigo(registroCategoriaBuscadaDTO.codigoCliente());
        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        ArrayList<CategoriaEstablecimiento> historicoCategoriasBuscadas = clienteRegistrado.get().getHistoricoCategoriasBuscadas();
        historicoCategoriasBuscadas.add(registroCategoriaBuscadaDTO.categoriaEstablecimiento());
        clienteRegistrado.get().setHistoricoCategoriasBuscadas(historicoCategoriasBuscadas);
        clienteRepo.save(clienteRegistrado.get());
        return registroCategoriaBuscadaDTO.codigoCliente();
    }

    @Override
    public String subirImagen(ImagenDTO imagenDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = clienteRepo.findByCodigo(imagenDTO.codigo());
        if (clienteRegistrado.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        //imagenesServicio.subirImagen());
        return null;
    }

    private boolean existeEmail(String email) {
        return clienteRepo.findByEmail(email).isPresent();
    }

    private boolean existeUsuario(String nickname) {
        return  clienteRepo.findByNickName(nickname).isPresent();
    }

    @Override
    public TokenDTO iniciarSesionCliente(InicioSesionDTO inicioSesionDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmailAndContrasena(inicioSesionDTO.email(), inicioSesionDTO.contrasena());
        if (clienteOptional.isEmpty()) {
            throw new Exception("Credenciales incorrectas");
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
        return new TokenDTO(jwtUtils.generarToken(cliente.getEmail(), map));
    }

    @Override
    public TokenDTO iniciarSesionModerador(InicioSesionDTO inicioSesionDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmailAndContrasena(inicioSesionDTO.email(), inicioSesionDTO.contrasena());
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
        return new TokenDTO(jwtUtils.generarToken(cliente.getEmail(), map));
    }

    @Override
    public String eliminarCuenta(String idUsuario) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findByCodigo(idUsuario);
        if (optionalCliente.isEmpty()) {
            throw new Exception("El código del usuario no existe");
        }
        Cliente cliente = optionalCliente.get();
        cliente.setEstado(EstadoCuenta.BLOQUEADA);
        clienteRepo.save(cliente);
        return null;
    }

    @Override
    public String actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findByCodigo(actualizacionCuentaDTO.codigo());
        if (optionalCliente.isEmpty()) {
            throw new Exception("El código del usuario no existe");
        }
        optionalCliente.get().setEmail(actualizacionCuentaDTO.email());
        optionalCliente.get().setCiudad(actualizacionCuentaDTO.ciudadResidencia());
        optionalCliente.get().setNombre(actualizacionCuentaDTO.nombre());
        optionalCliente.get().setFoto(actualizacionCuentaDTO.foto());
        optionalCliente.get().setCodigo(actualizacionCuentaDTO.codigo());
        clienteRepo.save(optionalCliente.get());
        return optionalCliente.get().getCodigo();
    }

    @Override
    public String enviarLinkRecuperacionContrasena(String email) throws Exception {

        if (!existeEmail(email)) {
            throw new Exception("El email no existe");
        }

        EmailDTO emailDTO = new EmailDTO(
                email,
                "Cambo de contraseña - unilocal",
                "para cambiar la contraseña acceda al siguiente link: http://localhost:8080/cambiarContrasena?email="+email
        );
        emailServicio.enviarEmail(emailDTO);
        return null;
    }

    @Override
    public String recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(recuperacionContrasenaDTO.codigoCliente());
        if (clienteOptional.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        Cliente cliente = clienteOptional.get();
        cliente.setContrasena(recuperacionContrasenaDTO.contrasenaNueva());
        clienteRepo.save(cliente);
        return cliente.getCodigo();
    }
}