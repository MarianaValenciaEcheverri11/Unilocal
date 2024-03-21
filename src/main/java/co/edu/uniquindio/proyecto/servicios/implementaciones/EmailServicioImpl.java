package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.intefaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {
    @Override
    public void enviarEmail(EmailDTO emailDto) {

    }
}
