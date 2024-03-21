package co.edu.uniquindio.proyecto.servicios.intefaces;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ImagenesServicio {

    Map subirImagen(MultipartFile imagen) throws Exception;

    Map eliminarImagen(String id) throws Exception;
}
