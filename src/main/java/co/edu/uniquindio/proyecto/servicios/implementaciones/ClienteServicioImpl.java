package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.models.documentos.Cliente;
import co.edu.uniquindio.proyecto.models.documentos.Establecimiento;
import co.edu.uniquindio.proyecto.models.enums.CategoriaEstablecimiento;
import co.edu.uniquindio.proyecto.repository.ClienteRepo;
import co.edu.uniquindio.proyecto.servicios.intefaces.ClienteServicio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicioImpl implements ClienteServicio {

    private final ClienteRepo clienteRepo;

    public ClienteServicioImpl(ClienteRepo clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

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
                .build();

        Cliente clienteRegistrado = clienteRepo.save(cliente);

        return clienteRegistrado.getCodigo();

    }

    @Override
    public String marcarLugarFavorito(FavoritoDTO favoritoDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = obtenerCliente(favoritoDTO.codigoCliente());
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
        Optional<Cliente> clienteRegistrado = obtenerCliente(favoritoDTO.codigoCliente());
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
    public Optional<ArrayList<Cliente>> listarClientes() throws Exception {
        return Optional.of((ArrayList<Cliente>) clienteRepo.findAll());
    }

    @Override
    public Optional<Cliente> obtenerCliente(String codigo) throws Exception {
        return clienteRepo.findByCodigo(codigo);
    }

    @Override
    public Optional<ArrayList<CategoriaEstablecimiento>> obtenerHistoricoCategoriasBuscadas(String codigoCliente) throws Exception {
        return clienteRepo.finByHistoricoCategoriasBuscadas(codigoCliente);
    }

    @Override
    public String registrarCategoriaBuscadas(RegistroCategoriaBuscadaDTO registroCategoriaBuscadaDTO) throws Exception {
        Optional<Cliente> clienteRegistrado = obtenerCliente(registroCategoriaBuscadaDTO.codigoCliente());
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
        Optional<Cliente> cliente = obtenerCliente(idUsuario);
        if (!cliente.isPresent()) {
            throw new Exception("El usuario no existe");
        }
        clienteRepo.delete(cliente.get());
        return null;
    }

    @Override
    public String actualizarCuenta(ActualizacionCuentaDTO actualizacionCuentaDTO) throws Exception {
        if (!clienteRepo.existsById(actualizacionCuentaDTO.codigo())) {
            throw new Exception("El usuario no existe");
        }
        Cliente cliente = Cliente.builder()
                .nombre(actualizacionCuentaDTO.nombre())
                .email(actualizacionCuentaDTO.foto())
                .contrasenia(actualizacionCuentaDTO.ciudadResidencia())
                .codigo(actualizacionCuentaDTO.codigo())
                .build();
        clienteRepo.save(cliente);
        return cliente.getCodigo();
    }

    @Override
    public String enviarLinkRecuperacionContrasena(String email) throws Exception {
        return null;
    }

    @Override
    public String recuperarContrasena(RecuperacionContrasenaDTO recuperacionContrasenaDTO) throws Exception {
        return null;
    }
}