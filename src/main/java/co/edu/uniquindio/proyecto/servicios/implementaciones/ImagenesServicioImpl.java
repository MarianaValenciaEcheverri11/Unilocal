package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.servicios.intefaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ImagenesServicioImpl implements ImagenesServicio {

    Cloudinary cloudinary;

    public ImagenesServicioImpl() {
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "davd8vz5w");
    }
    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        //TODO: Implementar
        return null;
    }

    @Override
    public Map eliminarImagen(String id) throws Exception {
        //TODO: Implementar
        return null;
    }

    private File convertir(MultipartFile image) throws IOException {
        File file = new File(image.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(image.getBytes());
        fos.close();
        return file;
    }
}
